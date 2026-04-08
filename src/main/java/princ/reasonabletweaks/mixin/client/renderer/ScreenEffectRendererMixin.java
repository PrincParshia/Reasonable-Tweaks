package princ.reasonabletweaks.mixin.client.renderer;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import princ.reasonabletweaks.client.resources.model.ModelBakeryImpl;

import static princ.reasonabletweaks.ReasonableTweaksConstants.EntityRenderStateDataKeys.IS_ON_SOUL_FIRE;

@Mixin(ScreenEffectRenderer.class)
public class ScreenEffectRendererMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Redirect(
            method = "renderScreenEffect",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/resources/model/sprite/SpriteGetter;get(Lnet/minecraft/client/resources/model/sprite/SpriteId;)Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;"
            )
    )
    TextureAtlasSprite renderScreenEffect(final SpriteGetter sprites, final SpriteId spriteId, @Local final float partialTicks) {
        Player player = this.minecraft.player;

        if (player != null) {
            EntityRenderState state = this.minecraft.getEntityRenderDispatcher().extractEntity(player, partialTicks);
            if (Boolean.TRUE.equals(state.getData(IS_ON_SOUL_FIRE))) {
                return sprites.get(ModelBakeryImpl.SOUL_FIRE_1);
            }
        }

        return sprites.get(spriteId);
    }
}
