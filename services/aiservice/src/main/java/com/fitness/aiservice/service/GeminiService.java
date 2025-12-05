package com.fitness.aiservice.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
@Slf4j
public class GeminiService {

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApikey;

    public GeminiService(WebClient.Builder webClientBuilder){
        this.webClient=webClientBuilder.build();
    }

    public String getRecommendations(String details){

        Map<String,Object> requestBody=Map.of(
                "contents",new Object[]{
                        Map.of(
                                "parts",new Object[]{
                                        Map.of("text",details)
                                }
                        )
                }
        );
log.info("Generating recommendation");
        String response=webClient.post().uri(geminiApiUrl)
                .header("Content-Type","application/json")
                .header("x-goog-api-key",geminiApikey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }

}
