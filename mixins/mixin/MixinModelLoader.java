package dev.foxgirl.mace3d.mixin;

import dev.foxgirl.mace3d.Mace3D;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.util.ModelIdentifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelLoader.class)
public abstract class MixinModelLoader {

    @Shadow
    private void loadItemModel(ModelIdentifier id) {
        throw new AssertionError();
    }

    @Inject(
        method = "<init>", at = @At(
            value = "INVOKE", ordinal = 0,
            target = "Lnet/minecraft/client/render/model/ModelLoader;loadItemModel(Lnet/minecraft/client/util/ModelIdentifier;)V"
        )
    )
    private void mace3d$injected$__init__(CallbackInfo info) {
        loadItemModel(Mace3D.MACE_IN_HAND);
    }

}
