package com.extremsport.user.adapter.in.web.dto;

import java.util.List;

public record UpdateAuthorRequest(
        String penName,
        String biography,
        String specialization,
        List<String> expertise,
        String profileImageUrl,
        String socialMediaLinks
) {}

