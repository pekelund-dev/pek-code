package dev.pekelund.pekcode.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeCompletionResponse {
    private String suggestion;
    private String explanation;
}
