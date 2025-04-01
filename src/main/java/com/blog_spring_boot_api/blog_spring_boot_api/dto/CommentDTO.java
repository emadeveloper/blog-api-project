package com.blog_spring_boot_api.blog_spring_boot_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CommentDTO {
    private long id;

    @NotEmpty
    @Size(min = 5, max = 100, message = "Name must be between 5 and 100 characters")
    private String name;
    
    @NotEmpty
    @Email
    private String email;
    
    @NotEmpty
    private String body;

    public CommentDTO(){
        super();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    

}
