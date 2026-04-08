package princ.reasonabletweaks.client.renderer.special;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.projectile.TridentModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import org.joml.Vector3fc;
import princ.reasonabletweaks.client.model.object.crystal.EndCrystalModel;

import java.util.function.Consumer;

public class EndCrystalSpecialRenderer implements NoDataSpecialModelRenderer {
    private final EndCrystalModel model;

    public EndCrystalSpecialRenderer(final EndCrystalModel model) {
        this.model = model;
    }

    public void submit(final PoseStack poseStack, final SubmitNodeCollector submitNodeCollector, final int lightCoords, final int overlayCoords, final boolean hasFoil, final int outlineColor) {
        submitNodeCollector.submitModelPart(this.model.root(), poseStack, this.model.renderType(TridentModel.TEXTURE), lightCoords, overlayCoords, null, false, hasFoil, -1, null, outlineColor);
    }

    public void getExtents(final Consumer<Vector3fc> output) {
        PoseStack poseStack = new PoseStack();
        this.model.root().getExtentsForGui(poseStack, output);
    }

    @Environment(EnvType.CLIENT)
    public record Unbaked() implements NoDataSpecialModelRenderer.Unbaked {
        public static final MapCodec<Unbaked> MAP_CODEC = MapCodec.unit(new Unbaked());

        public MapCodec<Unbaked> type() {
            return MAP_CODEC;
        }

        public EndCrystalSpecialRenderer bake(final SpecialModelRenderer.BakingContext context) {
            return new EndCrystalSpecialRenderer(new EndCrystalModel(context.entityModelSet().bakeLayer(ModelLayers.END_CRYSTAL)));
        }
    }
}
