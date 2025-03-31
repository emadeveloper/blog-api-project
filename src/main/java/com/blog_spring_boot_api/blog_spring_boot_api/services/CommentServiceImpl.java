package com.blog_spring_boot_api.blog_spring_boot_api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.blog_spring_boot_api.blog_spring_boot_api.dto.CommentDTO;
import com.blog_spring_boot_api.blog_spring_boot_api.exceptions.BlogAppException;
import com.blog_spring_boot_api.blog_spring_boot_api.exceptions.ResourceNotFoundException;
import com.blog_spring_boot_api.blog_spring_boot_api.model.Comment;
import com.blog_spring_boot_api.blog_spring_boot_api.model.Post;
import com.blog_spring_boot_api.blog_spring_boot_api.repository.CommentRepository;
import com.blog_spring_boot_api.blog_spring_boot_api.repository.RepositoryPost;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private RepositoryPost repositoryPost;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentDTO createComment(long postId, CommentDTO commentDTO) {
        Comment comment = convertDTOToEntity(commentDTO);
        Post post = repositoryPost.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);

        return convertEntityToDTO(newComment);
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> convertEntityToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(Long postId, Long commentId) {
        Post post = repositoryPost.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if (comment.getPost().getId() != post.getId()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Comment does not belong to Post");
        }
        return convertEntityToDTO(comment);
    }

    @Override
    public CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentRequest) {
        Post post = repositoryPost.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if (comment.getPost().getId() != post.getId()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Comment does not belong to Post");
        }

        comment.setName(commentRequest.getName());
        comment.setBody(commentRequest.getBody());
        comment.setEmail(commentRequest.getEmail());

        Comment updatedComment = commentRepository.save(comment);

        return convertEntityToDTO(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = repositoryPost.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        if (comment.getPost().getId() != post.getId()) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Comment does not belong to Post");
        }

        commentRepository.delete(comment);
    }
    private CommentDTO convertEntityToDTO(Comment comment) {
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);

        return commentDTO;
    }

    private Comment convertDTOToEntity(CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        return comment;
    }
}
