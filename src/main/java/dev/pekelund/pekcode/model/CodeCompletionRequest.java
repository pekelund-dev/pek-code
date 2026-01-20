package dev.pekelund.pekcode.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeCompletionRequest {
    private String code;
    private String language;
    private int cursorPosition;
}
