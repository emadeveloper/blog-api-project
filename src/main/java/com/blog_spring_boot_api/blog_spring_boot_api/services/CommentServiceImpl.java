package com.blog_spring_boot_api.blog_spring_boot_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog_spring_boot_api.blog_spring_boot_api.dto.CommentDTO;
import com.blog_spring_boot_api.blog_spring_boot_api.exceptions.ResourceNotFoundException;
import com.blog_spring_boot_api.blog_spring_boot_api.model.Comment;
import com.blog_spring_boot_api.blog_spring_boot_api.model.Post;
import com.blog_spring_boot_api.blog_spring_boot_api.repository.CommentRepository;
import com.blog_spring_boot_api.blog_spring_boot_api.repository.RepositoryPost;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private RepositoryPost repositoryPost;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentDTO createComment(long postId, CommentDTO commentDTO) {
        Comment comment = convertDTOToEntity(commentDTO);
        Post post = repositoryPost.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);

        return convertEntityToDTO(newComment);
    }

    private CommentDTO convertEntityToDTO(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setName(comment.getName());
        commentDTO.setEmail(comment.getEmail());
        commentDTO.setBody(comment.getBody());

        return commentDTO;
    }

    private Comment convertDTOToEntity (CommentDTO commentDTO){
        Comment comment = new Comment();

        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());
        
        return comment;
    }

}
