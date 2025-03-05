package com.blog_spring_boot_api.blog_spring_boot_api.exceptions;

import org.springframework.http.HttpStatus;

public class BlogAppException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String message;
    private HttpStatus status;

    public BlogAppException(HttpStatus status, String message) {
        super();
        this.message = message;
        this.status = status;
    }

    public BlogAppException(HttpStatus status, String message, String message1) {
        super();
        this.message = message;
        this.status = status;
        this.message = message1;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
