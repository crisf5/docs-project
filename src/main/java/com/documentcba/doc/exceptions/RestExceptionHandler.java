package com.documentcba.doc.exceptions;

import com.documentcba.doc.dto.ApiErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(value = DocumentNotFound.class)
    protected ResponseEntity<Map<String, ApiErrorDTO>> HandleDocumentNotFound(RuntimeException e, WebRequest request){

        LocalDateTime date = LocalDateTime.now();
        ApiErrorDTO apiErrorDTO= new ApiErrorDTO(
                date, HttpStatus.NOT_FOUND, e.getMessage(),
                ((ServletWebRequest)request).getRequest().getRequestURI().toString()
        );

        Map<String, ApiErrorDTO> error = new HashMap<>();
        error.put("error", apiErrorDTO);


        return ResponseEntity.status(apiErrorDTO.getStatus()).body(error);
    }

    @ExceptionHandler(value = FileNotCreated.class)
    protected ResponseEntity<Map<String, ApiErrorDTO>> HandleFileNotCreated(RuntimeException e, WebRequest request){

        LocalDateTime date = LocalDateTime.now();
        ApiErrorDTO apiErrorDTO= new ApiErrorDTO(
                date, HttpStatus.BAD_REQUEST, e.getMessage(),
                ((ServletWebRequest)request).getRequest().getRequestURI().toString()
        );

        Map<String, ApiErrorDTO> error = new HashMap<>();
        error.put("error", apiErrorDTO);


        return ResponseEntity.status(apiErrorDTO.getStatus()).body(error);
    }

    @ExceptionHandler(value = ParamNotFound.class)
    protected ResponseEntity<Map<String, ApiErrorDTO>> HandleParamNotFound(RuntimeException e, WebRequest request){

        LocalDateTime date = LocalDateTime.now();
        ApiErrorDTO apiErrorDTO= new ApiErrorDTO(
                date, HttpStatus.NOT_FOUND, e.getMessage(),
                ((ServletWebRequest)request).getRequest().getRequestURI().toString()
        );

        Map<String, ApiErrorDTO> error = new HashMap<>();
        error.put("error", apiErrorDTO);


        return ResponseEntity.status(apiErrorDTO.getStatus()).body(error);
    }

}
