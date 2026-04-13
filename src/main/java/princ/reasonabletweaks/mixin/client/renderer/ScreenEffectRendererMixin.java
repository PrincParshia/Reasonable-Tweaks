package princ.reasonabletweaks.mixin.client.renderer;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.client.resources.model.sprite.SpriteId;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import princ.reasonabletweaks.EntitySoulFireAccessor;
import princ.reasonabletweaks.ReasonableTweaksConstants;

@Mixin(ScreenEffectRenderer.class)
public class ScreenEffectRendererMixin {

    @Shadow
    @Final
    private Minecraft minecraft;

    @ModifyExpressionValue(method = "renderScreenEffect", at = @At(value = "FIELD", target = "Lnet/minecraft/client/resources/model/ModelBakery;FIRE_1:Lnet/minecraft/client/resources/model/sprite/SpriteId;"))
    private SpriteId modifyOverlayFire(SpriteId spriteId) {
        // noinspection ConstantConditions
        return ((EntitySoulFireAccessor) minecraft.player).reasonabletweaks$isOnSoulFire() ? ReasonableTweaksConstants.SOUL_FIRE_1 : spriteId;
    }
}
