package dev.pekelund.pekcode.controller;

import dev.pekelund.pekcode.model.CodeFile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FileControllerTest {

    @Test
    void codeFileModel_ShouldWorkCorrectly() {
        CodeFile file = new CodeFile("test.java", "test.java", "public class Test {}", "java");
        
        assertNotNull(file);
        assertEquals("test.java", file.getName());
        assertEquals("java", file.getLanguage());
        assertEquals("public class Test {}", file.getContent());
    }
}
