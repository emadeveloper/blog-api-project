package com.blog_spring_boot_api.blog_spring_boot_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog_spring_boot_api.blog_spring_boot_api.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}