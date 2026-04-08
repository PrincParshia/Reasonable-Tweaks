package princ.reasonabletweaks.client.resources.model;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.sprite.SpriteId;

public class ModelBakeryImpl {
    public static final SpriteId SOUL_FIRE_0;
    public static final SpriteId SOUL_FIRE_1;

    static {
        SOUL_FIRE_0 = Sheets.BLOCKS_MAPPER.defaultNamespaceApply("soul_fire_0");
        SOUL_FIRE_1 = Sheets.BLOCKS_MAPPER.defaultNamespaceApply("soul_fire_1");
    }
}
