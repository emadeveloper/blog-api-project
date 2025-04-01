package com.blog_spring_boot_api.blog_spring_boot_api.dto;

import java.util.Set;

import com.blog_spring_boot_api.blog_spring_boot_api.model.Comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostDTO {
    private Long id;
    
    @NotBlank
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    private String title;
    
    @NotBlank
    @Size(min = 10, max = 1000, message = "Content can't be empty, and should be at least 10 characters long")
    private String content;
    
    private String author;
    
    private Set<Comment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public PostDTO() {
        super();
    }
}
