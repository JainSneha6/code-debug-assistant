package com.sneha.code_debug_assistant.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LanguageRequest {
    private String errorMessage;
    private String codeSnippet;
}
