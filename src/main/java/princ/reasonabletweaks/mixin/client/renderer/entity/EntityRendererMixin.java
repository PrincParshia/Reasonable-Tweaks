package princ.reasonabletweaks.mixin.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static princ.reasonabletweaks.ReasonableTweaksConstants.EntityRenderStateDataKeys.IS_ON_SOUL_FIRE;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin<T extends Entity, S extends EntityRenderState> {
    @Unique
    private boolean isOnSoulFire;

    @Inject(method = "extractRenderState", at = @At("HEAD"))
    void extractRenderState(final T entity, final S state, final float partialTicks, final CallbackInfo callbackInfo) {
        if (entity instanceof Player player) {
            Level level = player.level();
            BlockPos blockPos = player.blockPosition();
            BlockState feetBlockState = level.getBlockState(blockPos);

            AABB aABB = player.getBoundingBox();

            level.getBlockStates(aABB)
                    .filter(blockState -> blockState.is(Blocks.FIRE) || blockState.is(Blocks.SOUL_FIRE))
                    .findAny().ifPresent(blockState -> this.isOnSoulFire = blockState.is(Blocks.SOUL_FIRE));

            if (this.isOnSoulFire || feetBlockState.is(Blocks.SOUL_FIRE)) {
                this.isOnSoulFire = !player.isInLava();
            }
        }

        state.setData(IS_ON_SOUL_FIRE, this.isOnSoulFire);
    }
}
