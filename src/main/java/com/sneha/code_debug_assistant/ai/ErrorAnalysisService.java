package com.sneha.code_debug_assistant.ai;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sneha.code_debug_assistant.dto.ErrorAnalysisResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ErrorAnalysisService {

    private final ChatClient chatClient;

    public ErrorAnalysisService(ChatClient chatClient){
        this.chatClient = chatClient;
    }

    public ErrorAnalysisResponse analyze(String errorMessage, String codeSnippet){
        String text = """
        You are an expert programming debugger.

        Analyze the given error and code.

        Tasks:
        1. Explain the error clearly
        2. Suggest a fix
        3. Provide corrected code
        4. Give confidence (0 to 1)

        RULES:
        - PRIORITIZE code syntax over error message
        - Keep explanation simple and concise
        - Output must be JSON

        OUTPUT FORMAT:
        {{
          "language": "",
          "explanation": "",
          "fix": "",
          "correctedCode": "",
          "confidence": 0.0
        }}

        errorMessage: {errorMessage}
        codeSnippet: {codeSnippet}
        """;

        PromptTemplate template = new PromptTemplate(text);
        Prompt prompt = template.create(Map.of(
                "errorMessage", errorMessage,
                "codeSnippet", codeSnippet
        ));

        return chatClient
                .prompt(prompt)
                .call()
                .entity(ErrorAnalysisResponse.class);

    }
}
