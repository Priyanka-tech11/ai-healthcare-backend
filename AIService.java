package ai.healthcare.example.healthcare.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.MediaType;

import java.util.Map;

@Service
public class AIService {

    @Value("${OPENROUTER_API_KEY}")
    private String apiKey;

    public String getDiagnosis(Map<String, String> req) throws Exception {

        OkHttpClient client = new OkHttpClient();

        String prompt = "Patient details:\n"
                + "Name: " + req.get("name") + "\n"
                + "Age: " + req.get("age") + "\n"
                + "Gender: " + req.get("gender") + "\n"
                + "Weight: " + req.get("weight") + "\n"
                + "Height: " + req.get("height") + "\n"
                + "Symptoms: " + req.get("symptoms") + "\n"
                + "Duration: " + req.get("duration") + "\n"
                + "Past Diseases: " + req.get("pastDiseases") + "\n"
                + "Give only:\n"
                + "1. Possible diseases\n"
                + "2. Precautions\n"
                + "Do not include extra explanation.";

        String safePrompt = prompt.replace("\"", "\\\"").replace("\n", "\\n");

        MediaType mediaType = MediaType.parse("application/json");

        String body = "{"
                + "\"model\": \"openai/gpt-3.5-turbo\","
                + "\"messages\": ["
                + "{ \"role\": \"user\", \"content\": \"" + safePrompt + "\" }"
                + "]"
                + "}";

        RequestBody requestBody = RequestBody.create(body, mediaType);

        Request request = new Request.Builder()
                .url("https://openrouter.ai/api/v1/chat/completions")
                .post(requestBody)
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .addHeader("HTTP-Referer", "http://localhost:8080")
                .addHeader("X-Title", "Healthcare App")
                .build();

        Response response = client.newCall(request).execute();

        String responseBody = response.body().string();

       
        String result = responseBody.split("\"content\":\"")[1]
                .split("\",\"refusal\"")[0];

        
        result = result.replace("\\n", "\n");

        return result;
    }
}