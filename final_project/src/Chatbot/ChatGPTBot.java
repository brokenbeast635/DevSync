package Chatbot;

import java.net.http.*;
import java.net.URI;

public class ChatGPTBot implements Chatbot {

    private String apiKey;

    public ChatGPTBot(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String getResponse(String userMessage) {
        try {
            String url = "https://api.openai.com/v1/chat/completions";

            String json = "{"
                    + "\"model\": \"gpt-4o-mini\","
                    + "\"messages\": [{\"role\": \"user\", \"content\": \"" + userMessage.replace("\"", "\\\"") + "\"}]"
                    + "}";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            String body = response.body();

            // Check if OpenAI returned an error
            if (body.contains("\"error\":")) {
                if (body.contains("insufficient_quota")) {
                    return "⚠ Error: Your API quota is exhausted. Please check your OpenAI plan.";
                } else if (body.contains("invalid_api_key")) {
                    return "⚠ Error: Invalid API key. Please check your key.";
                } else {
                    return "⚠ API Error: " + body;
                }
            }

            // Extract AI response
            String pattern = "\"content\":\\s*\"([^\"]*)\"";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern, java.util.regex.Pattern.DOTALL);
            java.util.regex.Matcher m = p.matcher(body);

            if (m.find()) {
                String output = m.group(1);
                output = output.replace("\\n", "\n");
                output = output.replace("\\\"", "\"");
                return output;
            }

            return "⚠ Error: Could not extract AI message.";

        } catch (Exception e) {
            return "⚠ Exception: " + e.getMessage();
        }
    }
}
