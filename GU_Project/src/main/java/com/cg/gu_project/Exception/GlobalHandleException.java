package com.cg.gu_project.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandleException {

    @ExceptionHandler(DataInputException.class)
    public ResponseEntity<Object> handleDataInputExeption(DataInputException dataInputException) {
        Map<String, String> errors = new HashMap();

        dataInputException.getMessage();
        return null;
    }
    
}
