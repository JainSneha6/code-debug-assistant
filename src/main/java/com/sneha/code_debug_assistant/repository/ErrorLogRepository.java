package com.sneha.code_debug_assistant.repository;

import com.sneha.code_debug_assistant.entity.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long> {

}
