package com.sneha.code_debug_assistant.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorLogResponse {
    private Long id;
    private String errorMessage;
    private String codeSnippet;
    private String language;
    private LocalDateTime createdAt;
    private String explanation;
    private String fix;
    private String correctedCode;
    private Double confidence;
}
