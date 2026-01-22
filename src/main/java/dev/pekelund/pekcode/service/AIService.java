package dev.pekelund.pekcode.service;

import dev.pekelund.pekcode.model.ChatRequest;
import dev.pekelund.pekcode.model.ChatResponse;
import dev.pekelund.pekcode.model.CodeCompletionRequest;
import dev.pekelund.pekcode.model.CodeCompletionResponse;

public interface AIService {
    ChatResponse chat(ChatRequest request);
    CodeCompletionResponse complete(CodeCompletionRequest request);
}
