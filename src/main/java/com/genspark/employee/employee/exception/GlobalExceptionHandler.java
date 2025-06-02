package com.genspark.employee.employee.exception;

import com.genspark.employee.employee.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalStateException(IllegalStateException exception)
    {
        return new ResponseEntity<>(new ApiResponse<>("error", exception.getMessage(), null),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception exception)
    {
        return new ResponseEntity<>(new ApiResponse<>("error", "An unexpected error occurred : " + exception.getMessage(), null),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
