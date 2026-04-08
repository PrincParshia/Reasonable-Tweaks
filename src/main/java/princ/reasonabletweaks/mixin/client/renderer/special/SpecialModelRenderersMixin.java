package princ.reasonabletweaks.mixin.client.renderer.special;

import net.minecraft.client.renderer.special.SpecialModelRenderers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import princ.reasonabletweaks.client.renderer.special.EndCrystalSpecialRenderer;

import static net.minecraft.client.renderer.special.SpecialModelRenderers.ID_MAPPER;
import static princ.reasonabletweaks.ReasonableTweaksConstants.withDefaultNamespace;

@Mixin(SpecialModelRenderers.class)
public class SpecialModelRenderersMixin {
    @Inject(method = "bootstrap", at = @At("HEAD"))
    private static void bootstrap(CallbackInfo callbackInfo) {
        ID_MAPPER.put(withDefaultNamespace("end_crystal"), EndCrystalSpecialRenderer.Unbaked.MAP_CODEC);
    }
}
