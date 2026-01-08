package com.foundit.service;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GeminiMatchService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final OkHttpClient client;

    public GeminiMatchService(OkHttpClient client) {
        this.client = client;
    }

    public int getMatchScore(String lostDesc, String foundDesc) {

        try {
            String prompt = """
                Compare the following two item descriptions and return ONLY a number between 0 and 100 indicating similarity.

                Lost Item: %s
                Found Item: %s
                """.formatted(lostDesc, foundDesc);

            JSONObject requestBody = new JSONObject()
                    .put("contents", new JSONArray()
                            .put(new JSONObject()
                                    .put("parts", new JSONArray()
                                            .put(new JSONObject().put("text", prompt))
                                    )
                            )
                    );

            Request request = new Request.Builder()
                    .url(apiUrl + "?key=" + apiKey)
                    .post(RequestBody.create(
                            requestBody.toString(),
                            MediaType.parse("application/json")
                    ))
                    .build();

            try (Response response = client.newCall(request).execute()) {

                if (!response.isSuccessful()) return 0;

                String body = response.body().string();
                JSONObject json = new JSONObject(body);

                String text = json
                        .getJSONArray("candidates")
                        .getJSONObject(0)
                        .getJSONObject("content")
                        .getJSONArray("parts")
                        .getJSONObject(0)
                        .getString("text")
                        .replaceAll("[^0-9]", "");

                return Integer.parseInt(text);
            }

        } catch (IOException | NumberFormatException e) {
            return 0;
        }
    }
}
