package com.extremsport.forum.application.service;

import java.util.UUID;

public class ThreadNotFoundException extends RuntimeException {
    public ThreadNotFoundException(UUID threadId) {
        super("Thread not found: " + threadId);
    }
}

