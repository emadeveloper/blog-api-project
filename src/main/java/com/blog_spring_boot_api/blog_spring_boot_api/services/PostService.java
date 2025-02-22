package com.blog_spring_boot_api.blog_spring_boot_api.services;

import java.util.List;

import com.blog_spring_boot_api.blog_spring_boot_api.dto.PostDTO;

public interface PostService {
    
    public PostDTO createPost (PostDTO postDTO);

    public List<PostDTO> getAllPosts();

    public PostDTO getPostById(long id);

    public PostDTO updatePost(PostDTO postDTO, long id);
    
    public void deletePost(long id);
}
