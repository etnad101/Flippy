package com.github.etnad101.flippy.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


public class RequestHandler extends Thread {
    public static Bazaar get() throws IOException {
        System.out.println("Fetching API");
        RequestHandler thread = new RequestHandler();
        thread.start();
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
}
