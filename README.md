# Pek Code

An AI-powered code editor built with **Spring Boot 4.0.1** and **Java 25**, using **Spring AI** for intelligent code assistance.

## Features

- 🚀 **Modern Spring Boot 4.0.1** architecture
- ☕ **Java 25** - Latest Java runtime
- 🤖 **Multi-AI Provider Support** - Choose between Anthropic Claude or Google Gemini
- 💡 **Code Completion** - AI-powered code suggestions
- 💬 **AI Chat Assistant** - Ask questions about your code
- 📝 **Code Editor** - Web-based editor with syntax highlighting
- 📁 **File Management** - Create, read, update, and delete files
- 🔒 **Secure** - Sandboxed workspace with path validation

## Technology Stack

- **Spring Boot 4.0.1** - Latest Spring Boot framework
- **Java 25** - Latest Java runtime
- **Spring AI 1.0.0-M5** - AI integration framework
- **Maven** - Build tool
- **Lombok** - Reduce boilerplate code

## AI Providers

### Anthropic Claude (Default)
Set environment variable:
```bash
export ANTHROPIC_API_KEY=your-api-key-here
```

### Google Gemini
Set environment variables:
```bash
export AI_PROVIDER=gemini
export GEMINI_PROJECT_ID=your-project-id
export GEMINI_LOCATION=us-central1
```

## Getting Started

### Prerequisites
- Java 25 or higher
- Maven 3.6+
- API keys for your chosen AI provider

### Build and Run

1. Clone the repository:
```bash
git clone https://github.com/pekelund-dev/pek-code.git
cd pek-code
```

2. Set your AI provider credentials (see AI Providers section above)

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

5. Open your browser and navigate to:
```
http://localhost:8080
```

## API Endpoints

### File Operations
- `GET /api/files` - List all files
- `GET /api/files/{path}` - Read a specific file
- `POST /api/files` - Create or update a file
- `DELETE /api/files/{path}` - Delete a file

### AI Operations
- `POST /api/ai/chat` - Chat with AI assistant
  ```json
  {
    "message": "How do I create a REST controller?",
    "codeContext": "optional code snippet",
    "language": "java"
  }
  ```

- `POST /api/ai/complete` - Get code completion
  ```json
  {
    "code": "public class MyClass {",
    "language": "java",
    "cursorPosition": 20
  }
  ```

## Configuration

Edit `src/main/resources/application.yml`:

```yaml
server:
  port: 8080

spring:
  ai:
    anthropic:
      api-key: ${ANTHROPIC_API_KEY:}
      chat:
        options:
          model: claude-3-5-sonnet-20241022
          max-tokens: 4096
    vertex:
      ai:
        gemini:
          project-id: ${GEMINI_PROJECT_ID:}
          location: ${GEMINI_LOCATION:us-central1}

ai:
  provider: ${AI_PROVIDER:anthropic}
```

## Architecture

```
pek-code/
├── src/main/java/dev/pekelund/pekcode/
│   ├── PekCodeApplication.java       # Main Spring Boot application
│   ├── config/
│   │   └── AIConfig.java             # AI provider configuration
│   ├── controller/
│   │   ├── AIController.java         # AI endpoints
│   │   └── FileController.java       # File management endpoints
│   ├── model/
│   │   ├── ChatRequest.java
│   │   ├── ChatResponse.java
│   │   ├── CodeCompletionRequest.java
│   │   ├── CodeCompletionResponse.java
│   │   └── CodeFile.java
│   └── service/
│       ├── AIService.java            # AI service interface
│       ├── AnthropicService.java     # Claude implementation
│       ├── GeminiService.java        # Gemini implementation
│       └── FileService.java          # File operations
└── src/main/resources/
    ├── application.yml                # Application configuration
    └── static/
        └── index.html                 # Web UI
```

## License

MIT

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.
