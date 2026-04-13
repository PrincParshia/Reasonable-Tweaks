package princ.reasonabletweaks.mixin.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import princ.reasonabletweaks.EntitySoulFireAccessor;

import static princ.reasonabletweaks.ReasonableTweaksConstants.IS_ON_SOUL_FIRE;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin<T extends Entity, S extends EntityRenderState> {

    @Inject(method = "extractRenderState", at = @At("HEAD"))
    private void extractRenderState(T entity, S state, float partialTicks, CallbackInfo callbackInfo) {
        state.setData(IS_ON_SOUL_FIRE, ((EntitySoulFireAccessor) entity).reasonabletweaks$isOnSoulFire());
    }
}
