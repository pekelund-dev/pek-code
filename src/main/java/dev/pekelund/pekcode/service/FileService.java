package dev.pekelund.pekcode.service;

import dev.pekelund.pekcode.model.CodeFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);
    private final Path workspaceRoot = Paths.get(System.getProperty("user.home"), "pek-code-workspace");

    public FileService() {
        try {
            if (!Files.exists(workspaceRoot)) {
                Files.createDirectories(workspaceRoot);
                logger.info("Created workspace directory: {}", workspaceRoot);
            }
        } catch (IOException e) {
            logger.error("Failed to create workspace directory", e);
        }
    }

    public List<CodeFile> listFiles() throws IOException {
        List<CodeFile> files = new ArrayList<>();
        if (!Files.exists(workspaceRoot)) {
            return files;
        }

        try (Stream<Path> paths = Files.walk(workspaceRoot)) {
            paths.filter(Files::isRegularFile)
                 .forEach(path -> {
                     try {
                         String relativePath = workspaceRoot.relativize(path).toString();
                         String content = Files.readString(path);
                         String language = detectLanguage(path.toString());
                         files.add(new CodeFile(relativePath, path.getFileName().toString(), content, language));
                     } catch (IOException e) {
                         logger.error("Error reading file: {}", path, e);
                     }
                 });
        }
        return files;
    }

    public CodeFile readFile(String relativePath) throws IOException {
        Path filePath = workspaceRoot.resolve(relativePath).normalize();
        
        if (!filePath.startsWith(workspaceRoot)) {
            throw new SecurityException("Access denied: path outside workspace");
        }

        if (!Files.exists(filePath)) {
            throw new IOException("File not found: " + relativePath);
        }

        String content = Files.readString(filePath);
        String language = detectLanguage(relativePath);
        return new CodeFile(relativePath, filePath.getFileName().toString(), content, language);
    }

    public CodeFile writeFile(CodeFile file) throws IOException {
        Path filePath = workspaceRoot.resolve(file.getPath()).normalize();

        if (!filePath.startsWith(workspaceRoot)) {
            throw new SecurityException("Access denied: path outside workspace");
        }

        Files.createDirectories(filePath.getParent());
        Files.writeString(filePath, file.getContent());
        
        logger.info("File written: {}", file.getPath());
        return file;
    }

    public void deleteFile(String relativePath) throws IOException {
        Path filePath = workspaceRoot.resolve(relativePath).normalize();

        if (!filePath.startsWith(workspaceRoot)) {
            throw new SecurityException("Access denied: path outside workspace");
        }

        if (Files.exists(filePath)) {
            Files.delete(filePath);
            logger.info("File deleted: {}", relativePath);
        }
    }

    private String detectLanguage(String filename) {
        String lower = filename.toLowerCase();
        if (lower.endsWith(".java")) return "java";
        if (lower.endsWith(".js")) return "javascript";
        if (lower.endsWith(".ts")) return "typescript";
        if (lower.endsWith(".py")) return "python";
        if (lower.endsWith(".html")) return "html";
        if (lower.endsWith(".css")) return "css";
        if (lower.endsWith(".xml")) return "xml";
        if (lower.endsWith(".json")) return "json";
        if (lower.endsWith(".md")) return "markdown";
        return "plaintext";
    }
}
