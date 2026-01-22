package dev.pekelund.pekcode.service;

import dev.pekelund.pekcode.model.ChatRequest;
import dev.pekelund.pekcode.model.ChatResponse;
import dev.pekelund.pekcode.model.CodeCompletionRequest;
import dev.pekelund.pekcode.model.CodeCompletionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service("geminiService")
public class GeminiService implements AIService {

    private static final Logger logger = LoggerFactory.getLogger(GeminiService.class);
    private final ChatClient chatClient;

    public GeminiService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public ChatResponse chat(ChatRequest request) {
        try {
            String content = request.getMessage();
            if (request.getCodeContext() != null && !request.getCodeContext().isEmpty()) {
                content = "Code context:\n```" + request.getLanguage() + "\n" + 
                         request.getCodeContext() + "\n```\n\n" + request.getMessage();
            }

            String response = chatClient.prompt()
                    .user(content)
                    .call()
                    .content();

            return new ChatResponse(response, "assistant");
        } catch (Exception e) {
            logger.error("Error calling Gemini API", e);
            return new ChatResponse("Error: " + e.getMessage(), "error");
        }
    }

    @Override
    public CodeCompletionResponse complete(CodeCompletionRequest request) {
        try {
            String prompt = String.format(
                "Complete the following %s code. Provide only the completion suggestion without explanation:\n```%s\n%s\n```",
                request.getLanguage(),
                request.getLanguage(),
                request.getCode()
            );

            String response = chatClient.prompt()
                    .user(prompt)
                    .call()
                    .content();

            return new CodeCompletionResponse(response, "AI-generated suggestion");
        } catch (Exception e) {
            logger.error("Error getting code completion", e);
            return new CodeCompletionResponse("", "Error: " + e.getMessage());
        }
    }
}
