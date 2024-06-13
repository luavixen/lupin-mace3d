package dev.foxgirl.mace3d;

import com.google.common.io.CharStreams;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;

public final class Mace3D implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Executors.newScheduledThreadPool(1).schedule(() -> {
            MinecraftClient.getInstance().execute(() -> {
                /*try {
                    var resource = MinecraftClient.getInstance().getResourceManager().getResourceOrThrow(Identifier.of("mace3d", "icon.png"));
                    System.out.println(resource);
                    System.out.println(CharStreams.toString(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }*/
                MinecraftClient.getInstance().getResourceManager().findAllResources("textures", __ -> true).forEach((id, resource) -> {
                    if (id.toString().contains("mace3d")) {
                        System.out.println(resource);
                    }
                });
            });
        }, 5, java.util.concurrent.TimeUnit.SECONDS);
    }

}
