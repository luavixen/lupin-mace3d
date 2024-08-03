package dev.foxgirl.mace3d;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public final class Mace3D {

    public static final Logger LOGGER = LogManager.getLogger();

    public static final class MaceModel {
        public final Item item;
        public final ModelResourceLocation normalModelIdentifier;
        public final ModelResourceLocation inHandModelIdentifier;

        private MaceModel(Item item, ModelResourceLocation normalModelIdentifier, ModelResourceLocation inHandModelIdentifier) {
            this.item = item;
            this.normalModelIdentifier = normalModelIdentifier;
            this.inHandModelIdentifier = inHandModelIdentifier;
        }
    }

    private static final @NotNull ArrayList<@NotNull MaceModel> MODELS = new ArrayList<>();

    private static void registerMaceModel(Item item, ResourceLocation normalModelIdentifier, ResourceLocation inHandModelIdentifier) {
        Objects.requireNonNull(item, "Argument 'item'");
        Objects.requireNonNull(normalModelIdentifier, "Argument 'normalModelIdentifier'");
        Objects.requireNonNull(inHandModelIdentifier, "Argument 'inHandModelIdentifier'");
        MODELS.add(new MaceModel(item, ModelResourceLocation.inventory(normalModelIdentifier), ModelResourceLocation.inventory(inHandModelIdentifier)));
    }

    static {
        registerMaceModel(
            Items.MACE,
            ResourceLocation.withDefaultNamespace("mace"),
            ResourceLocation.fromNamespaceAndPath("mace3d", "mace_in_hand")
        );
    }

    public static @NotNull ArrayList<@NotNull MaceModel> getMaceModels() {
        return MODELS;
    }

    public static void registerExternalMaceModel(
        @NotNull Item item,
        @NotNull ResourceLocation normalModelIdentifier,
        @NotNull ResourceLocation inHandModelIdentifier
    ) {
        registerMaceModel(item, normalModelIdentifier, inHandModelIdentifier);
    }

}
