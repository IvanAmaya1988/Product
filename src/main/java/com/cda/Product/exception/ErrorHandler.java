package com.cda.Product.exception;

import com.cda.Product.dto.ApiErrorResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        ApiErrorResponse body = new ApiErrorResponse(e.getStatus(), LocalDateTime.now(),e.toString(), e.getMessage(), request.getRequestURI());
        ResponseEntity<ApiErrorResponse> response = new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        return response;
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiErrorResponse> handleConflict(ConflictException e, HttpServletRequest request) {
        ApiErrorResponse body = new ApiErrorResponse(409, LocalDateTime.now(), e.toString(), e.getMessage(), request.getRequestURI());
        ResponseEntity<ApiErrorResponse> response = new ResponseEntity<>(body, HttpStatus.CONFLICT);
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleFieldNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
        Object[] messages = e.getDetailMessageArguments();
        String message="";
        for(Object msj: messages){
            String stringMsj = (String) msj;
            message = message.concat(stringMsj);
        }
        ApiErrorResponse body = new ApiErrorResponse(400, LocalDateTime.now(),e.toString(), message, request.getRequestURI());
        ResponseEntity<ApiErrorResponse> response = new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        return response;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception e,HttpServletRequest request){
        ApiErrorResponse body = new ApiErrorResponse( 500,LocalDateTime.now(), e.toString(), e.getMessage(), request.getRequestURI());
        ResponseEntity<ApiErrorResponse> response = new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
}
