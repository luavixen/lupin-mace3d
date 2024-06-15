package dev.foxgirl.mace3d;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;

public final class Mace3D implements ClientModInitializer {

    public static final ModelIdentifier MACE = ModelIdentifier.ofInventoryVariant(Identifier.ofVanilla("mace"));
    public static final ModelIdentifier MACE_IN_HAND = ModelIdentifier.ofInventoryVariant(Identifier.ofVanilla("mace_in_hand"));

    @Override
    public void onInitializeClient() {
    }

}
