package dev.pekelund.pekcode.controller;

import dev.pekelund.pekcode.model.ChatRequest;
import dev.pekelund.pekcode.model.ChatResponse;
import dev.pekelund.pekcode.model.CodeCompletionRequest;
import dev.pekelund.pekcode.model.CodeCompletionResponse;
import dev.pekelund.pekcode.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*")
public class AIController {

    @Autowired
    private AIService aiService;

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {
        ChatResponse response = aiService.chat(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/complete")
    public ResponseEntity<CodeCompletionResponse> complete(@RequestBody CodeCompletionRequest request) {
        CodeCompletionResponse response = aiService.complete(request);
        return ResponseEntity.ok(response);
    }
}
