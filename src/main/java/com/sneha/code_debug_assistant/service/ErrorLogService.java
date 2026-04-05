package com.sneha.code_debug_assistant.service;

import com.sneha.code_debug_assistant.ai.LanguageDetectionService;
import com.sneha.code_debug_assistant.dto.ErrorLogRequest;
import com.sneha.code_debug_assistant.dto.ErrorLogResponse;
import com.sneha.code_debug_assistant.dto.LanguageResponse;
import com.sneha.code_debug_assistant.entity.ErrorLog;
import com.sneha.code_debug_assistant.repository.ErrorLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ErrorLogService {

    @Autowired
    private ErrorLogRepository repository;

    @Autowired
    private LanguageDetectionService service;

    public ErrorLog create(ErrorLogRequest request){
        LanguageResponse response = service.detectLanguage(request.getErrorMessage(), request.getCodeSnippet());
        ErrorLog errorLog = new ErrorLog();
        errorLog.setErrorMessage(request.getErrorMessage());
        errorLog.setCodeSnippet(request.getCodeSnippet());
        errorLog.setLanguage(response.getLanguage());
        errorLog.setCreatedAt(LocalDateTime.now());

        return repository.save(errorLog);
    }

    private ErrorLogResponse mapToResponse(ErrorLog errorLog){
        ErrorLogResponse response = new ErrorLogResponse();
        response.setId(errorLog.getId());
        response.setErrorMessage(errorLog.getErrorMessage());
        response.setCodeSnippet(errorLog.getCodeSnippet());
        response.setLanguage(errorLog.getLanguage());
        response.setCreatedAt(errorLog.getCreatedAt());
        return response;
    }

    public List<ErrorLogResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ErrorLogResponse findById(Long id) {
        ErrorLog errorLog = repository.findById(id).orElseThrow(()->new RuntimeException("Error Not Found"));
        return mapToResponse(errorLog);
    }

    public String deleteById(Long id) {
        if(!repository.existsById(id)){
            throw new RuntimeException("Error Not Found");
        }
        repository.deleteById(id);
        return "Deleted";
    }
}
