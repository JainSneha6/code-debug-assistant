package com.sneha.code_debug_assistant.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String errorMessage;

    @Column(length = 3000)
    private String codeSnippet;

    @Column(length = 3000)
    private String explanation;

    @Column(length = 3000)
    private String fix;

    @Column(length = 3000)
    private String correctedCode;

    private Double confidence;
    private String language;
    private LocalDateTime createdAt;
}
