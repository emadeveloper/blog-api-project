package com.blog_spring_boot_api.blog_spring_boot_api.dto;

public class CommentDTO {
    private long id;
    private String name;
    private String email;
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
