package com.tech44.API_Exam.service;

import com.tech44.API_Exam.domain.Post;
import com.tech44.API_Exam.domain.User;
import com.tech44.API_Exam.exception.ResourceNotFoundException;
import com.tech44.API_Exam.repo.PostRepository;
import com.tech44.API_Exam.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostService {

    private static final Logger log = LoggerFactory.getLogger(PostService.class);

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Post createPost(Post post) {
        log.info("Creating post: {}", post);
        User user = userRepository.findById(post.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        post.setUser(user);
        return postRepository.save(post);
    }

    public Post getPostById(Long id) {
        log.info("Fetching post with id: {}", id);
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
    }

    public List<Post> getAllPosts() {
        log.info("Fetching all posts");
        return postRepository.findAll();
    }

    public List<Post> getPostsByTitle(String title) {
        log.info("Fetching posts with title: {}", title);
        return postRepository.findByTitle(title);
    }

    public List<Post> getPostsByUserId(Long userId) {
        log.info("Fetching posts with user id: {}", userId);
        return postRepository.findByUserId(userId);
    }

    public Post updatePost(Long id, Post postDetails) {
        log.info("Updating post with id: {}", id);
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        post.setTitle(postDetails.getTitle());
        post.setContent(postDetails.getContent());
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        log.info("Deleting post with id: {}", id);
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        postRepository.delete(post);
    }
}