package com.extremsport.article.application.service;

import java.util.UUID;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(UUID articleId) {
        super("Article not found: " + articleId);
    }
}

