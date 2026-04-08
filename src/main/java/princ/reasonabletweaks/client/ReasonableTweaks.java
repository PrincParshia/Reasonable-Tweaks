package princ.reasonabletweaks.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import princ.reasonabletweaks.client.model.object.crystal.EndCrystalModel;

import static princ.reasonabletweaks.ReasonableTweaksConstants.withDefaultNamespace;

public class ReasonableTweaks implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModelLayerRegistry.registerModelLayer(new ModelLayerLocation(withDefaultNamespace("item/end_crystal"), "main"), EndCrystalModel::createBodyLayer);
	}
}