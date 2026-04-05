package com.sneha.code_debug_assistant.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorLogRequest {
    private String errorMessage;
    private String codeSnippet;
    private String language;
}
