package princ.reasonabletweaks.mixin.client.renderer.feature;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.feature.FlameFeatureRenderer;
import net.minecraft.client.resources.model.sprite.SpriteId;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import princ.reasonabletweaks.ReasonableTweaksConstants;

import static princ.reasonabletweaks.ReasonableTweaksConstants.IS_ON_SOUL_FIRE;

@Mixin(FlameFeatureRenderer.class)
public class FlameFeatureRendererMixin {

    @ModifyExpressionValue(method = "renderFlame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/resources/model/ModelBakery;FIRE_0:Lnet/minecraft/client/resources/model/sprite/SpriteId;"))
    private SpriteId modifyFlame0(SpriteId spriteId, @Local(argsOnly = true) EntityRenderState state) {
        return Boolean.TRUE.equals(state.getData(IS_ON_SOUL_FIRE)) ? ReasonableTweaksConstants.SOUL_FIRE_0 : spriteId;
    }

    @ModifyExpressionValue(method = "renderFlame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/resources/model/ModelBakery;FIRE_1:Lnet/minecraft/client/resources/model/sprite/SpriteId;"))
    private SpriteId modifyFlame1(SpriteId spriteId, @Local(argsOnly = true) EntityRenderState state) {
        return Boolean.TRUE.equals(state.getData(IS_ON_SOUL_FIRE)) ? ReasonableTweaksConstants.SOUL_FIRE_1 : spriteId;
    }
}
