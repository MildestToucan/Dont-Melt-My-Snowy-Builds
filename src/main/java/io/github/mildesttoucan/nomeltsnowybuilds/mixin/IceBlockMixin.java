package io.github.mildesttoucan.nomeltsnowybuilds.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.github.mildesttoucan.nomeltsnowybuilds.DontMeltMySnowyBuilds;
import net.minecraft.world.level.block.IceBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(IceBlock.class)
@SuppressWarnings("unused")
abstract class IceBlockMixin {

    @Definition(id = "getBrightness", method = "Lnet/minecraft/server/level/ServerLevel;getBrightness(Lnet/minecraft/world/level/LightLayer;Lnet/minecraft/core/BlockPos;)I")
    @Expression("?.getBrightness(?, ?) > ? - ?")
    @ModifyExpressionValue(method = "randomTick", at = @At("MIXINEXTRAS:EXPRESSION"))
    private boolean shouldIceBlockMelt(boolean original) {
        return original && DontMeltMySnowyBuilds.CONFIG.shouldIceMelt;
    }

    @Definition(id = "getBrightness", method = "Lnet/minecraft/server/level/ServerLevel;getBrightness(Lnet/minecraft/world/level/LightLayer;Lnet/minecraft/core/BlockPos;)I")
    @Definition(id = "getLightBlock", method = "Lnet/minecraft/world/level/block/state/BlockState;getLightBlock()I")
    @Expression("?.getBrightness(?, ?) > @(?) - ?.getLightBlock()")
    @ModifyExpressionValue(method = "randomTick", at = @At("MIXINEXTRAS:EXPRESSION"))
    private int setIceBlockMeltingLevel(int original) {
        if (original == DontMeltMySnowyBuilds.CONFIG.iceMeltingLightLevel) return original;
        return DontMeltMySnowyBuilds.CONFIG.iceMeltingLightLevel;
    }
}
