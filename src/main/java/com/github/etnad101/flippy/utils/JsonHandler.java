package com.github.etnad101.flippy.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class JsonHandler {
    public static Bazaar getBazzar() throws IOException {
        System.out.println("Fetching API");

        ObjectMapper mapper = new ObjectMapper();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.hypixel.net/v2/skyblock/bazaar")
                .build();

        Response response = client.newCall(request).execute();
        Bazaar bazaar = mapper.readValue(response.body().string(), Bazaar.class);
        System.out.println("Done!");
        return bazaar;
    }

    public static Recipes getRecipes() throws IOException {
        ResourceLocation resourceLocation = new ResourceLocation("flippy", "recipes.json");
        IResourceManager iresourceManager = Minecraft.getMinecraft().getResourceManager();
        IResource iResource = iresourceManager.getResource(resourceLocation);
        InputStream inputStream = iResource.getInputStream();

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        for (int length; (length = inputStream.read(buffer)) != -1; ) {
            result.write(buffer, 0, length);
        }

        String rawJson = result.toString("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(rawJson, Recipes.class);
    }
}
