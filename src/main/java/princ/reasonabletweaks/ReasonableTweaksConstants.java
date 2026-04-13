package princ.reasonabletweaks;

import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReasonableTweaksConstants {

    public static final String NAMESPACE = "reasonable-tweaks";
    public static final String NAME = "Reasonable Tweaks";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);
    public static final RenderStateDataKey<Boolean> IS_ON_SOUL_FIRE = RenderStateDataKey.create(() -> NAMESPACE + ":isOnSoulFire");
    public static final SpriteId SOUL_FIRE_0 = Sheets.BLOCKS_MAPPER.defaultNamespaceApply("soul_fire_0");
    public static final SpriteId SOUL_FIRE_1 = Sheets.BLOCKS_MAPPER.defaultNamespaceApply("soul_fire_1");

    public static Identifier id(String string) {
        return Identifier.fromNamespaceAndPath(NAMESPACE, string);
    }
}
