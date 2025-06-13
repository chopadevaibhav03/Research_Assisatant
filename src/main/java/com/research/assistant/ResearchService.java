package com.research.assistant;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class ResearchService {
    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApikey;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public ResearchService(WebClient.Builder webClientBuilder,ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.build();
        this.objectMapper = objectMapper;
    }

    public String processContent(ResearchRequest request) {

        // Build the prompt
        String prompt = buildPrompt(request);
        // Query the AI model API
        Map<String,Object> requestBody = Map.of(
                "contents" ,new Object[]{
                        Map.of("parts" , new Object[]{
                                Map.of("text",prompt)
                        })
                    }
                );

                String response = webClient.post()
                        .uri(geminiApiUrl+geminiApikey)
                        .bodyValue(requestBody)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
                // parse the response
                // return the response

                return extractTextFromResponse(response);

    }

    private String extractTextFromResponse(String response) {
        try {
            GeminiResponse geminiResponse = objectMapper.readValue(response,GeminiResponse.class);
            if (geminiResponse.getCandidates() != null && !geminiResponse.getCandidates().isEmpty()){
                GeminiResponse.Candidate firstCandidate = geminiResponse.getCandidates().get(0);
                if (firstCandidate.getContent() != null &&
                firstCandidate.getContent().getParts() != null &&
                !firstCandidate.getContent().getParts().isEmpty()){
                    return firstCandidate.getContent().getParts().get(0).getText();
                }
            }
            return  "No response found! try again...";
        }catch (Exception e){
            return "Error Parsing: " +e.getMessage();
        }

    }

    private String buildPrompt(ResearchRequest request){
        StringBuilder prompt = new StringBuilder();
        switch (request.getOperation()){
            case "summarise" :
                prompt.append("Provide a clear and concise summary of the following text in few sentences: \n\n");
                break;
            case "suggest":
                prompt.append("Based on the following content: suggest the related topics and further reading. Format with clear heading bullet points: \n\n");
                break;
            default:
                throw new IllegalArgumentException("oops something went wrong!!" + request.getOperation());
        }
        prompt.append(request.getContent());
        return prompt.toString();
    }
}
