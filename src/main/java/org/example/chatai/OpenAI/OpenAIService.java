package org.example.chatai.OpenAI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAIService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final String apiUrl = "https://api.openai.com/v1/engines/davinci-codex/completions";
    private final RestTemplate restTemplate = new RestTemplate();

    public String askOpenAI(String prompt) {
        String requestBody = "{\"prompt\": \"" + prompt + "\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);

        return response.getBody();
    }
}
