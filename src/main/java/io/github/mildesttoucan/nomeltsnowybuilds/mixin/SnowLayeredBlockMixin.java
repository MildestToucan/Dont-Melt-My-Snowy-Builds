package io.github.mildesttoucan.nomeltsnowybuilds.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.github.mildesttoucan.nomeltsnowybuilds.DontMeltMySnowyBuilds;
import net.minecraft.world.level.block.SnowLayerBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SnowLayerBlock.class)
@SuppressWarnings("unused") // Class shows up as unused when abstract due to MCDev bug.
abstract class SnowLayeredBlockMixin {

    @Definition(id = "getBrightness", method = "Lnet/minecraft/server/level/ServerLevel;getBrightness(Lnet/minecraft/world/level/LightLayer;Lnet/minecraft/core/BlockPos;)I")
    @Expression("?.getBrightness(?, ?) > ?")
    @ModifyExpressionValue(method = "randomTick", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean shouldSnowMelt(boolean original) {
        return original && DontMeltMySnowyBuilds.CONFIG.shouldSnowMelt;
    }

    @Definition(id = "getBrightness", method = "Lnet/minecraft/server/level/ServerLevel;getBrightness(Lnet/minecraft/world/level/LightLayer;Lnet/minecraft/core/BlockPos;)I")
    @Expression("?.getBrightness(?, ?) > @(?)")
    @ModifyExpressionValue(method = "randomTick", at = @At("MIXINEXTRAS:EXPRESSION"))
    private int changeRequiredLight(int original) {
        // Reference the original value to best avoid snuffing out other Mixins targeting the Vanilla value.
        if (original == DontMeltMySnowyBuilds.CONFIG.snowMeltingLightLevel) return original;
        return DontMeltMySnowyBuilds.CONFIG.snowMeltingLightLevel;
    }
}
