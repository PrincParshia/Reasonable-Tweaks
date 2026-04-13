package princ.reasonabletweaks.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.object.crystal.EndCrystalModel;
import princ.reasonabletweaks.ReasonableTweaksConstants;

public class ReasonableTweaks implements ClientModInitializer {
    public static final ModelLayerLocation END_CRYSTAL = new ModelLayerLocation(ReasonableTweaksConstants.id("item/end_crystal"), "main");

    @Override
    public void onInitializeClient() {
        this.registerModelLayers();
    }

    private void registerModelLayers() {
        ModelLayerRegistry.registerModelLayer(END_CRYSTAL, EndCrystalModel::createBodyLayer);
    }
}