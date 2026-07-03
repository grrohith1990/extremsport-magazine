package com.extremsport.forum.application.service;

import java.util.UUID;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(UUID postId) {
        super("Post not found: " + postId);
    }
}

