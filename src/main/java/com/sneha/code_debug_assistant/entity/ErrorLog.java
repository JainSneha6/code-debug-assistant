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

    @Column(length = 2000)
    private String codeSnippet;
    private String language;
    private LocalDateTime createdAt;
}
