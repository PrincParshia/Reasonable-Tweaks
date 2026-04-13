package princ.reasonabletweaks;

import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static princ.reasonabletweaks.ReasonableTweaksConstants.EntityRenderStateDataKeys.IS_ON_SOUL_FIRE;

public class ReasonableTweaksConstants {
    public static final String NAMESPACE = "reasonable-tweaks";
    public static final String NAME = "Reasonable Tweaks";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public static Identifier id(String string) {
        return Identifier.fromNamespaceAndPath(NAMESPACE, string);
    }

    public static boolean isOnSoulFire(Player player, EntityRenderState state, Level level) {
        if (player != null) {
            BlockPos blockPos = player.blockPosition();
            BlockState feetBlockState = level.getBlockState(blockPos);

            AABB aABB = player.getBoundingBox();

            level.getBlockStates(aABB)
                    .filter(blockState -> blockState.is(Blocks.FIRE) || blockState.is(Blocks.SOUL_FIRE))
                    .findAny().ifPresent(blockState -> state.setData(IS_ON_SOUL_FIRE, blockState.is(Blocks.SOUL_FIRE)));

            if (Boolean.TRUE.equals(state.getData(IS_ON_SOUL_FIRE)) || feetBlockState.is(Blocks.SOUL_FIRE)) {
                state.setData(IS_ON_SOUL_FIRE, !player.isInLava());
            }
        }

        return Boolean.TRUE.equals(state.getData(IS_ON_SOUL_FIRE));
    }

    public static class EntityRenderStateDataKeys {
        public static final RenderStateDataKey<Boolean> IS_ON_SOUL_FIRE;

        static String withPrefix(String string) {
            return NAMESPACE + ":" + string;
        }

        static {
            IS_ON_SOUL_FIRE = RenderStateDataKey.create(() -> withPrefix("isOnSoulFire"));
        }
    }
}
