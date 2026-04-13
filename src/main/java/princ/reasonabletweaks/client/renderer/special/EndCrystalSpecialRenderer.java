package princ.reasonabletweaks.client.renderer.special;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.object.crystal.EndCrystalModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.EndCrystalRenderState;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import org.joml.Vector3fc;
import princ.reasonabletweaks.client.ReasonableTweaks;

import java.util.function.Consumer;

public class EndCrystalSpecialRenderer implements SpecialModelRenderer<EndCrystalRenderState> {

    private static final Identifier TEXTURE = Identifier.withDefaultNamespace("textures/entity/end_crystal/end_crystal.png");
    private static final float SIN_45 = (float) Math.sin(Math.PI / 4);
    private static final float THIRD_PI = (float) Math.PI / 3;
    private final EndCrystalModel model;

    public EndCrystalSpecialRenderer(EndCrystalModel model) {
        this.model = model;
    }

    @Override
    public EndCrystalRenderState extractArgument(ItemStack stack) {
        EndCrystalRenderState renderState = new EndCrystalRenderState();
        renderState.showsBottom = false;
        renderState.beamOffset = null;

        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        if (player != null) {
            renderState.ageInTicks = player.tickCount + minecraft.getDeltaTracker().getGameTimeDeltaPartialTick(false);
        }
        return renderState;
    }

    @Override
    public void submit(EndCrystalRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
        poseStack.pushPose();

        // noinspection ConstantConditions
        float animationSpeed = renderState.ageInTicks * 3;
        Quaternionf rotation = new Quaternionf().setAngleAxis(THIRD_PI, SIN_45, 0, SIN_45).rotateY(animationSpeed * Mth.DEG_TO_RAD);

        model.resetPose();
        model.base.visible = false;
        model.outerGlass.rotateBy(Axis.YP.rotationDegrees(animationSpeed).rotateAxis(THIRD_PI, SIN_45, 0, SIN_45));
        model.innerGlass.rotateBy(rotation);
        model.cube.rotateBy(rotation);

        poseStack.translate(0, -0.5, 0);
        submitNodeCollector.submitModelPart(model.root(), poseStack, model.renderType(TEXTURE), lightCoords, overlayCoords, null, false, hasFoil, -1, null, outlineColor);
        poseStack.popPose();
    }

    @Override
    public void getExtents(Consumer<Vector3fc> output) {
        PoseStack poseStack = new PoseStack();
        model.root().getExtentsForGui(poseStack, output);
    }

    @Environment(EnvType.CLIENT)
    public record Unbaked() implements SpecialModelRenderer.Unbaked<EndCrystalRenderState> {

        public static final MapCodec<Unbaked> MAP_CODEC = MapCodec.unit(new Unbaked());

        @Override
        public MapCodec<Unbaked> type() {
            return MAP_CODEC;
        }

        @Override
        public EndCrystalSpecialRenderer bake(SpecialModelRenderer.BakingContext context) {
            return new EndCrystalSpecialRenderer(new EndCrystalModel(context.entityModelSet().bakeLayer(ReasonableTweaks.END_CRYSTAL)));
        }
    }
}
