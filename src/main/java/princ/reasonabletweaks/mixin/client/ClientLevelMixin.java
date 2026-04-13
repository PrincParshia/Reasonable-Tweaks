package princ.reasonabletweaks.mixin.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import princ.reasonabletweaks.EntitySoulFireAccessor;

@Mixin(ClientLevel.class)
public class ClientLevelMixin {


    @Inject(method = "tickNonPassenger", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;tick()V"))
    private void tick(Entity entity, CallbackInfo ci) {
        if (!entity.isOnFire()) {
            ((EntitySoulFireAccessor) entity).reasonabletweaks$setOnSoulFire(false);
        }
    }

    @Inject(method = "tickPassenger", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;rideTick()V"))
    private void rideTick(Entity vehicle, Entity entity, CallbackInfo ci) {
        if (!entity.isOnFire()) {
            ((EntitySoulFireAccessor) entity).reasonabletweaks$setOnSoulFire(false);
        }
    }
}
