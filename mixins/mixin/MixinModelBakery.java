package dev.foxgirl.mace3d.mixin;

import dev.foxgirl.mace3d.Mace3D;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelBakery.class)
public abstract class MixinModelBakery {

    @Shadow
    private void loadSpecialItemModelAndDependencies(ModelResourceLocation id) {
        throw new AssertionError();
    }

    @Inject(
        method = "loadSpecialItemModelAndDependencies(Lnet/minecraft/client/resources/model/ModelResourceLocation;)V",
        at = @At("HEAD")
    )
    private void mace3d$injected$loadSpecialItemModelAndDependencies(ModelResourceLocation id, CallbackInfo info) {
        if (ItemRenderer.SPYGLASS_IN_HAND_MODEL.equals(id)) {
            for (var model : Mace3D.getMaceModels()) {
                loadSpecialItemModelAndDependencies(model.normalModelIdentifier);
                loadSpecialItemModelAndDependencies(model.inHandModelIdentifier);
            }
        }
    }

    /*
    @Inject(
        method = "<init>", at = @At(
            value = "INVOKE", ordinal = 0,
            target = "Lnet/minecraft/client/resources/model/ModelBakery;loadSpecialItemModelAndDependencies(Lnet/minecraft/client/resources/model/ModelResourceLocation;)V"
        )
    )
    // @Inject(
    //     method = "<init>(Lnet/minecraft/client/color/block/BlockColors;Lnet/minecraft/util/profiling/ProfilerFiller;Ljava/util/Map;Ljava/util/Map;)V",
    //     at = @At("TAIL")
    // )
    private void mace3d$injected$__init__(CallbackInfo info) {
        for (var model : Mace3D.getMaceModels()) {
            Mace3D.LOGGER.info("loadSpecialItemModelAndDependencies({})", model.normalModelIdentifier);
            loadSpecialItemModelAndDependencies(model.normalModelIdentifier);
            Mace3D.LOGGER.info("loadSpecialItemModelAndDependencies({})", model.inHandModelIdentifier);
            loadSpecialItemModelAndDependencies(model.inHandModelIdentifier);
        }
    }
    */

}
