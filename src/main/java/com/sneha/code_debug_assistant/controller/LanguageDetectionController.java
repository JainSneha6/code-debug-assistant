package com.sneha.code_debug_assistant.controller;

import com.sneha.code_debug_assistant.ai.LanguageDetectionService;
import com.sneha.code_debug_assistant.dto.LanguageRequest;
import com.sneha.code_debug_assistant.dto.LanguageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LanguageDetectionController {

    @Autowired
    private LanguageDetectionService service;

    @PostMapping("/detect-language")
    public ResponseEntity<LanguageResponse> detectLanguage(@RequestBody LanguageRequest request){
        LanguageResponse response = service.detectLanguage(
                request.getErrorMessage(),
                request.getCodeSnippet()
        );
        return ResponseEntity.ok(response);
    }
}


