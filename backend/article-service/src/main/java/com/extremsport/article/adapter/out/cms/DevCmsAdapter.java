package com.extremsport.article.adapter.out.cms;

import com.extremsport.article.domain.model.Article;
import com.extremsport.article.domain.port.out.CmsPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("dev")
@Primary
public class DevCmsAdapter implements CmsPort {

    @Override
    public void syncArticleToCms(Article article) {
        log.info("[DEV CMS] Article synced: {}", article.getTitle());
    }

    @Override
    public String getMediaUrl(String assetId) {
        return "https://placeholder.dev/assets/" + assetId;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}

