package princ.reasonabletweaks.mixin.client.renderer.feature;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.feature.FlameFeatureRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.sprite.AtlasManager;
import net.minecraft.client.resources.model.sprite.SpriteId;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import princ.reasonabletweaks.client.resources.model.ModelBakeryImpl;

import static princ.reasonabletweaks.ReasonableTweaksConstants.EntityRenderStateDataKeys.IS_ON_SOUL_FIRE;

@Mixin(FlameFeatureRenderer.class)
public class FlameFeatureRendererMixin {
    @Redirect(
            method = "renderFlame",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/resources/model/sprite/AtlasManager;get(Lnet/minecraft/client/resources/model/sprite/SpriteId;)Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;",
                    ordinal = 0
            )
    )
    TextureAtlasSprite TextureAtlasSprite1(final AtlasManager atlasManager, final SpriteId sprite, @Local final EntityRenderState state) {
        if (Boolean.TRUE.equals(state.getData(IS_ON_SOUL_FIRE))) {
            return atlasManager.get(ModelBakeryImpl.SOUL_FIRE_0);
        }
        return atlasManager.get(sprite);
    }

    @Redirect(
            method = "renderFlame",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/resources/model/sprite/AtlasManager;get(Lnet/minecraft/client/resources/model/sprite/SpriteId;)Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;",
                    ordinal = 1
            )
    )
    TextureAtlasSprite TextureAtlasSprite2(final AtlasManager atlasManager, final SpriteId sprite, @Local final EntityRenderState state) {
        if (Boolean.TRUE.equals(state.getData(IS_ON_SOUL_FIRE))) {
            return atlasManager.get(ModelBakeryImpl.SOUL_FIRE_1);
        }
        return atlasManager.get(sprite);
    }
}
