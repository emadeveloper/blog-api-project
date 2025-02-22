package com.blog_spring_boot_api.blog_spring_boot_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog_spring_boot_api.blog_spring_boot_api.dto.PostDTO;
import com.blog_spring_boot_api.blog_spring_boot_api.model.Post;
import com.blog_spring_boot_api.blog_spring_boot_api.repository.RepositoryPost;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private RepositoryPost repositoryPost;

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        // Convert DTO to Entity
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setAuthor(postDTO.getAuthor());

        Post postCreated = repositoryPost.save(post);
        
        //Conver Entity to DTO
        PostDTO postResponse = new PostDTO();

        postResponse.setId(postCreated.getId());
        postResponse.setTitle(postCreated.getTitle());
        postResponse.setContent(postCreated.getContent());
        postResponse.setAuthor(postCreated.getAuthor());

        return postResponse;
    }

}
