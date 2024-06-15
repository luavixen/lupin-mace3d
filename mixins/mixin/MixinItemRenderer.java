package dev.foxgirl.mace3d.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import dev.foxgirl.mace3d.Mace3D;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemRenderer.class)
public abstract class MixinItemRenderer {

    @Shadow @Final
    private ItemModels models;

    @Unique
    private void mace3d$tryReplaceMaceBakedModelWith(
        ItemStack stack, LocalRef<BakedModel> modelRef,
        ModelIdentifier modelIdentifier
    ) {
        if (stack.isOf(Items.MACE)) {
            modelRef.set(models.getModelManager().getModel(modelIdentifier));
        }
    }

    @Inject(
        method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
        at = @At(
            value = "INVOKE", ordinal = 0, shift = At.Shift.BEFORE,
            target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"
        )
    )
    private void mace3d$injected$renderItem(
        CallbackInfo info,
        @Local(ordinal = 0, argsOnly = true) ItemStack stack,
        @Local(ordinal = 0, argsOnly = true) LocalRef<BakedModel> modelRef
    ) {
        mace3d$tryReplaceMaceBakedModelWith(stack, modelRef, Mace3D.MACE);
    }

    @Inject(
        method = "getModel(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;I)Lnet/minecraft/client/render/model/BakedModel;",
        at = @At(
            value = "INVOKE", ordinal = 0, shift = At.Shift.BY, by = 2,
            target = "Lnet/minecraft/client/render/item/ItemModels;getModel(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/client/render/model/BakedModel;"
        )
    )
    private void mace3d$injected$getModel(
        CallbackInfoReturnable<BakedModel> info,
        @Local(ordinal = 0, argsOnly = true) ItemStack stack,
        @Local(ordinal = 0, argsOnly = false) LocalRef<BakedModel> modelRef
    ) {
        mace3d$tryReplaceMaceBakedModelWith(stack, modelRef, Mace3D.MACE_IN_HAND);
    }

}
