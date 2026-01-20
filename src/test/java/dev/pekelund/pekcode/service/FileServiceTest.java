package dev.pekelund.pekcode.service;

import dev.pekelund.pekcode.model.CodeFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {

    private FileService fileService;

    @BeforeEach
    void setUp() {
        fileService = new FileService();
    }

    @Test
    void writeAndReadFile_ShouldWorkCorrectly() throws IOException {
        CodeFile file = new CodeFile("example.java", "example.java", "public class Example {}", "java");
        
        fileService.writeFile(file);
        CodeFile readFile = fileService.readFile("example.java");
        
        assertEquals(file.getContent(), readFile.getContent());
        assertEquals(file.getName(), readFile.getName());
    }

    @Test
    void listFiles_ShouldReturnAllFiles() throws IOException {
        fileService.writeFile(new CodeFile("file1.java", "file1.java", "class File1 {}", "java"));
        fileService.writeFile(new CodeFile("file2.py", "file2.py", "def hello():", "python"));
        
        List<CodeFile> files = fileService.listFiles();
        
        assertTrue(files.size() >= 2);
    }
}
