package dev.pekelund.pekcode.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeFile {
    private String path;
    private String name;
    private String content;
    private String language;
}
