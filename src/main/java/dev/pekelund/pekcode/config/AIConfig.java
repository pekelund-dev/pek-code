package dev.pekelund.pekcode.config;

import dev.pekelund.pekcode.service.AIService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AIConfig {

    @Value("${ai.provider:anthropic}")
    private String aiProvider;

    @Bean
    @Primary
    public AIService aiService(
            @Qualifier("anthropicService") AIService anthropicService,
            @Qualifier("geminiService") AIService geminiService) {
        
        if ("gemini".equalsIgnoreCase(aiProvider)) {
            return geminiService;
        }
        return anthropicService; // default to Anthropic
    }
}
