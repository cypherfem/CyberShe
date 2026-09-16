package com.ajith.practice;
import java.net.http.HttpClient;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import com.fasterxml.jackson.databind.JsonNode;
//jackson is the libary to work with JSON
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiPractice {
    public static void main(String[] args) {
        String myApikey = System.getenv("OPENROUTER_API_KEY");
        System.out.println("API key found: " + (myApikey != null));

        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create("https://openrouter.ai/api/v1/chat/completions");

        String json = """
{
  "model": "nvidia/nemotron-3-nano-30b-a3b:free",
  "messages": [
    {
      "role": "user",
      "content": "Analyze this sentence for gender bias. Reply in exactly 4 short lines: Bias: Yes or No, Category: one category, Explanation: one short sentence, Rewrite: one short neutral rewrite. Sentence: women are dramatic and emotional."
    }
  ]
}
""";

   HttpRequest myRequest = HttpRequest.newBuilder()
                .uri(uri).timeout(Duration.ofSeconds(30))
                .header("Authorization","Bearer " + myApikey).header("Content-Type", "application/json")
                //Publisher = Java has an object that knows how to provide/send the body data when the HTTP request happens.
           .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
try {
    HttpResponse<String> response =
                        client.send(
                                myRequest,
                                HttpResponse.BodyHandlers.ofString()
                        );
    System.out.println("Current Status: " + response.statusCode());


    //this as a JSON reader/helper
    ObjectMapper Ourmapper = new ObjectMapper();
    // response.body() is the JSON text OpenRouter sent back
    // readTree() organizes that JSON so we can move through it easily //
    // "root" means the top/start of the JSON
    JsonNode root = Ourmapper.readTree(response.body());
    String AIanswer = root
            .get("choices")
            .get(0)
            .get("message")
            .get("content")
            .asText();

    System.out.println("AI answer:");
    System.out.println(AIanswer);
        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}