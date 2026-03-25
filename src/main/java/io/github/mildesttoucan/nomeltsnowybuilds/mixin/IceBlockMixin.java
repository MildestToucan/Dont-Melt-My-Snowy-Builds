package io.github.mildesttoucan.nomeltsnowybuilds.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.github.mildesttoucan.nomeltsnowybuilds.DontMeltMySnowyBuilds;
import net.minecraft.world.level.block.IceBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(IceBlock.class)
abstract class IceBlockMixin {

    @Definition(id = "getBrightness", method = "Lnet/minecraft/server/level/ServerLevel;getBrightness(Lnet/minecraft/world/level/LightLayer;Lnet/minecraft/core/BlockPos;)I")
    @Definition(id = "BLOCK", field = "Lnet/minecraft/world/level/LightLayer;BLOCK:Lnet/minecraft/world/level/LightLayer;")
    @Definition(id = "getLightDampening", method = "Lnet/minecraft/world/level/block/state/BlockState;getLightDampening()I")
    @Expression("?.getBrightness(BLOCK, ?) > 11 - ?.getLightDampening()")
    @ModifyExpressionValue(method = "randomTick", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean shouldIceBlockMelt(boolean original) {
        return original && DontMeltMySnowyBuilds.CONFIG.shouldIceMelt;
    }

    @Definition(id = "getBrightness", method = "Lnet/minecraft/server/level/ServerLevel;getBrightness(Lnet/minecraft/world/level/LightLayer;Lnet/minecraft/core/BlockPos;)I")
    @Definition(id = "getLightDampening", method = "Lnet/minecraft/world/level/block/state/BlockState;getLightDampening()I")
    @Expression("?.getBrightness(?, ?) > @(11) - ?.getLightDampening()")
    @ModifyExpressionValue(method = "randomTick", at = @At("MIXINEXTRAS:EXPRESSION"))
    private int setIceBlockMeltingLevel(int original) {
        if (original == DontMeltMySnowyBuilds.CONFIG.iceMeltingLightLevel) return original;
        return DontMeltMySnowyBuilds.CONFIG.iceMeltingLightLevel;
    }
}
