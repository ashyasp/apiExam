package com.tech44.API_Exam.service;

import com.tech44.API_Exam.domain.Comment;
import com.tech44.API_Exam.domain.Post;
import com.tech44.API_Exam.exception.ResourceNotFoundException;
import com.tech44.API_Exam.repo.CommentRepository;
import com.tech44.API_Exam.repo.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentService {

    private static final Logger log = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public Comment createComment(Comment comment) {
        log.info("Creating comment: {}", comment);
        Post post = postRepository.findById(comment.getPost().getId()).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    public Comment getCommentById(Long id) {
        log.info("Fetching comment with id: {}", id);
        return commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
    }

    public List<Comment> getAllComments() {
        log.info("Fetching all comments");
        return commentRepository.findAll();
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        log.info("Fetching comments for post with id: {}", postId);
        return commentRepository.findByPostId(postId);
    }

    public Comment updateComment(Long id, Comment commentDetails) {
        log.info("Updating comment with id: {}", id);
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        comment.setContent(commentDetails.getContent());
        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        log.info("Deleting comment with id: {}", id);
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        commentRepository.delete(comment);
    }
}