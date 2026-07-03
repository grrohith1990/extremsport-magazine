package com.extremsport.article.domain.port.out;

import com.extremsport.article.domain.model.Article;

/**
 * Secondary Port (Driven): CMS System abstraction.
 *
 * KEY AGILITY POINT: This port abstracts the CMS system.
 * Currently backed by IBM FileNet, but can be swapped to a cloud-based
 * CMS without changing the application core.
 *
 * The adapter implementing this interface is the ONLY place where
 * CMS-specific code lives.
 */
public interface CmsPort {

    /**
     * Syncs an article to the external CMS for content management workflows.
     */
    void syncArticleToCms(Article article);

    /**
     * Retrieves media/assets associated with an article from the CMS.
     */
    String getMediaUrl(String assetId);

    /**
     * Checks if the CMS system is available (for circuit breaker).
     */
    boolean isAvailable();
}

