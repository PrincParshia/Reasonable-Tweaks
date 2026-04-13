package princ.reasonabletweaks;

import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ReasonableTweaksConstants {
    public static final String NAMESPACE = "reasonable-tweaks";
    public static final String NAME = "Reasonable Tweaks";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public static Identifier id(String string) {
        return Identifier.fromNamespaceAndPath(NAMESPACE, string);
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
