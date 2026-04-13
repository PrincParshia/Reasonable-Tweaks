package princ.reasonabletweaks.mixin.client;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import princ.reasonabletweaks.EntitySoulFireAccessor;

@Mixin(Entity.class)
public abstract class EntityMixin implements EntitySoulFireAccessor {

    @Unique
    private boolean reasonabletweaks$isOnSoulFire;

    @Inject(method = "setRemainingFireTicks", at = @At("HEAD"))
    private void setRemainingSoulFireTicks(int remainingTicks, CallbackInfo ci) {
        Entity entity = (Entity) (Object) this;
        Level level = entity.level();

        if (!level.isClientSide()) {
            return;
        }

        if (entity.isInLava() || reasonabletweaks$isInBlock(Blocks.FIRE, level, entity)) {
            reasonabletweaks$isOnSoulFire = false;
        }
        else if (reasonabletweaks$isInBlock(Blocks.SOUL_FIRE, level, entity)) {
            reasonabletweaks$isOnSoulFire = true;
        }
    }

    @Unique
    private static boolean reasonabletweaks$isInBlock(Block block, Level level, Entity entity) {
        BlockState feetBlockState = level.getBlockState(entity.blockPosition());
        return feetBlockState.is(block) || level.getBlockStates(entity.getBoundingBox())
                .anyMatch(state -> state.is(block));
    }

    @Override
    public boolean reasonabletweaks$isOnSoulFire() {
        return reasonabletweaks$isOnSoulFire;
    }

    @Override
    public void reasonabletweaks$setOnSoulFire(boolean onSoulFire) {
        reasonabletweaks$isOnSoulFire = onSoulFire;
    }
}
