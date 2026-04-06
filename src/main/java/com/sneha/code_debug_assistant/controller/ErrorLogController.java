package com.sneha.code_debug_assistant.controller;

import com.sneha.code_debug_assistant.dto.ErrorLogRequest;
import com.sneha.code_debug_assistant.dto.ErrorLogResponse;
import com.sneha.code_debug_assistant.entity.ErrorLog;
import com.sneha.code_debug_assistant.service.ErrorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/errors")
public class ErrorLogController {

    @Autowired
    private ErrorLogService service;

    @PostMapping
    public ResponseEntity<ErrorLog> create(@RequestBody ErrorLogRequest request){
        return ResponseEntity.status(201).body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<ErrorLogResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ErrorLogResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(service.deleteById(id));
    }

    @PostMapping("/analyze")
    public ResponseEntity<ErrorLogResponse> analyze(@RequestBody ErrorLogRequest request){
        return ResponseEntity.ok(service.analyze(request));
    }
}
