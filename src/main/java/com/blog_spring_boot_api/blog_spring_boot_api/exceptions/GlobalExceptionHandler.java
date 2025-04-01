package com.blog_spring_boot_api.blog_spring_boot_api.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.blog_spring_boot_api.blog_spring_boot_api.dto.DetailErrors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<DetailErrors> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        DetailErrors detailErrors = new DetailErrors(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(detailErrors, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BlogAppException.class)
    public ResponseEntity<DetailErrors> handleBlogAppException(BlogAppException exception, WebRequest webRequest) {
        DetailErrors detailErrors = new DetailErrors(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(detailErrors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DetailErrors> handleGlobalException(Exception exception, WebRequest webRequest) {
        DetailErrors detailErrors = new DetailErrors(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(detailErrors, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @SuppressWarnings("null")
    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach(error -> {
                String fieldName = ((FieldError)error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
