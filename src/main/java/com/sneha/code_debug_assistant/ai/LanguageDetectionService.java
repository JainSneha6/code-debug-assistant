package com.sneha.code_debug_assistant.ai;

import com.sneha.code_debug_assistant.dto.LanguageResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LanguageDetectionService {

    private final ChatClient chatClient;

    public LanguageDetectionService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public LanguageResponse detectLanguage(String errorMessage, String codeSnippet){
        String text = """
            You are an expert programming language detector.
            
            Your task is to identify the programming language using the given errorMessage and codeSnippet.
            
            IMPORTANT RULES:
            - PRIORITIZE the codeSnippet syntax over the errorMessage.
            - Use errorMessage only as a secondary hint.
            - Return ONLY ONE language name (no explanation).
            - If unsure, return "Unknown".
            
            LANGUAGE IDENTIFICATION HINTS:
            
            - JSON → {{ "key": "value" }} (double quotes required)
            - JavaScript → {{ name: 'Sneha' }}, console.log(), function()
            - Python → def func():, print(), indentation-based
            - Java → public class, System.out.println()
            - C → #include <stdio.h>
            - C++ → #include <iostream>, std::
            - C# → using System;, Console.WriteLine()
            - Go → package main, func main()
            - Rust → fn main(), let mut, println!
            - Ruby → def method, puts, end
            - PHP → <?php echo
            - Swift → import UIKit, let, var
            - Kotlin → fun main(), val, var
            - TypeScript → type annotations (: string), interface
            - Shell/Bash → #!/bin/bash, echo
            - SQL → SELECT, INSERT, UPDATE
            - HTML → <html>, <div>
            - CSS → selector {{ property: value; }}
            - YAML → key: value (no braces)
            - XML → <tag></tag>
            
            SPECIAL RULES:
            - If syntax matches JSON strictly → return "JSON"
            - If it's JavaScript object literal (invalid JSON) → return "JavaScript"
            - If code is too short or ambiguous → return "Unknown"
            
            EXAMPLES:
            
            Input:
            errorMessage: JsonParseException
            codeSnippet: {{ name: 'Sneha' }}
            Output: JavaScript
            
            Input:
            errorMessage: KeyError
            codeSnippet: data["name"]
            Output: Python
            
            Input:
            errorMessage: syntax error near unexpected token
            codeSnippet: echo "Hello"
            Output: Bash
            
            Input:
            errorMessage: compilation failed
            codeSnippet: fn main() {{ println!("Hello"); }}
            Output: Rust
            
            Input:
            errorMessage: none
            codeSnippet: package main\nfunc main() {{}}
            Output: Go
            
            NOW ANALYZE:
            
            errorMessage: {errorMessage}
            codeSnippet: {codeSnippet}
            """;

        PromptTemplate promptTemplate = new PromptTemplate(text);

        Prompt prompt = promptTemplate.create(Map.of("errorMessage",errorMessage,
        "codeSnippet",codeSnippet));

        return chatClient
                .prompt(prompt)
                .call()
                .entity(LanguageResponse.class);
    }
}
