package com.extremsport.article.adapter.out.cms;

import com.extremsport.article.domain.model.Article;
import com.extremsport.article.domain.port.out.CmsPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Secondary Adapter (Driven): CMS integration.
 *
 * KEY AGILITY POINT: This adapter currently integrates with IBM FileNet.
 * When the customer decides to switch to a cloud-based CMS, ONLY this
 * adapter needs to be replaced. The domain and application layers remain unchanged.
 *
 * Strategy: Use a profile-based approach to switch between CMS implementations:
 * - @Profile("filenet") -> FileNetCmsAdapter
 * - @Profile("cloud-cms") -> CloudCmsAdapter
 */
@Slf4j
@Component
@Profile("!dev")
public class CmsAdapter implements CmsPort {

    private final RestTemplate restTemplate;
    private final String cmsBaseUrl;

    public CmsAdapter(
            @Value("${cms.base-url:http://localhost:8090}") String cmsBaseUrl) {
        this.restTemplate = new RestTemplate();
        this.cmsBaseUrl = cmsBaseUrl;
    }

    @Override
    public void syncArticleToCms(Article article) {
        try {
            log.info("Syncing article {} to CMS at {}", article.getId(), cmsBaseUrl);
            // Integration point - currently IBM FileNet REST API
            // Will be replaced when cloud CMS is chosen
            // restTemplate.postForEntity(cmsBaseUrl + "/api/documents", article, Void.class);
        } catch (Exception e) {
            log.warn("Failed to sync article to CMS (graceful degradation): {}", e.getMessage());
        }
    }

    @Override
    public String getMediaUrl(String assetId) {
        return cmsBaseUrl + "/api/assets/" + assetId;
    }

    @Override
    public boolean isAvailable() {
        try {
            restTemplate.getForEntity(cmsBaseUrl + "/health", String.class);
            return true;
        } catch (Exception e) {
            log.warn("CMS is not available: {}", e.getMessage());
            return false;
        }
    }
}
