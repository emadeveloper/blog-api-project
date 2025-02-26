package com.blog_spring_boot_api.blog_spring_boot_api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.blog_spring_boot_api.blog_spring_boot_api.dto.PostDTO;
import com.blog_spring_boot_api.blog_spring_boot_api.dto.PostResponse;
import com.blog_spring_boot_api.blog_spring_boot_api.exceptions.ResourceNotFoundException;
import com.blog_spring_boot_api.blog_spring_boot_api.model.Post;
import com.blog_spring_boot_api.blog_spring_boot_api.repository.RepositoryPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private RepositoryPost repositoryPost;

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        // Convert DTO to Entity
        Post post = convertEntityToDto(postDTO);

        // Save to database
        repositoryPost.save(post);

        // Convert Entity to DTO
        PostDTO postDTOResponse = convertDTOtoEntity(post);

        return postDTOResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Post> posts = repositoryPost.findAll(pageable);
        
        List<Post> postsList = posts.getContent();  
        List<PostDTO> content = postsList.stream().map(post -> convertDTOtoEntity(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        
        postResponse.setPosts(content);
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setPageSize(posts.getSize());
        postResponse.setCurrentPage(posts.getNumber());
        postResponse.setHasNext(posts.isLast());

        return postResponse;
    }

    @Override   
    public PostDTO getPostById(long id) {
        Post post = repositoryPost.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return convertDTOtoEntity(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, long id) {
        Post post = repositoryPost.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        post.setTitle(postDTO.getTitle());
        post.setAuthor(postDTO.getAuthor());
        post.setContent(postDTO.getContent());

        Post updatedPost = repositoryPost.save(post);

        return convertDTOtoEntity(updatedPost);
    }

    @Override
    public void deletePost(long id) {
        Post post = repositoryPost.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        repositoryPost.delete(post);   
    }

    // METHODS TO CONVERT ENTITY TO DTO AND DTO TO ENTITY
    private PostDTO convertDTOtoEntity(Post post) {
        PostDTO postDTO = new PostDTO();

        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setAuthor(post.getAuthor());

        return postDTO;
    }

    private Post convertEntityToDto(PostDTO postDTO) {
        Post post = new Post();

        post.setId(postDTO.getId());
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setAuthor(postDTO.getAuthor());

        return post;
    }
}
