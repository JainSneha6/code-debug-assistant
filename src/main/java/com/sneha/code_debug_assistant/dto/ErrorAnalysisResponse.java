package com.sneha.code_debug_assistant.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorAnalysisResponse {
    private String language;
    private String explanation;
    private String fix;
    private String correctedCode;
    private Double confidence;
}
