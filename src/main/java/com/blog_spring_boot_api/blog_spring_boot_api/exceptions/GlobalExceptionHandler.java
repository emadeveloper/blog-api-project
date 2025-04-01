package com.blog_spring_boot_api.blog_spring_boot_api.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.blog_spring_boot_api.blog_spring_boot_api.dto.DetailErrors;

@ControllerAdvice
public class GlobalExceptionHandler {
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
}
