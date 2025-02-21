package com.blog_spring_boot_api.blog_spring_boot_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blog_spring_boot_api.blog_spring_boot_api.model.Post;

public interface RepositoryPost extends JpaRepository<Post, Long> {

}
