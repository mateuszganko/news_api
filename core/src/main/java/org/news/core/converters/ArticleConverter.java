package org.news.core.converters;

import org.news.client.dto.TopHeadlineResponse;
import org.news.core.models.ArticleDto;
import org.springframework.stereotype.Component;

@Component
public class ArticleConverter {

    public ArticleDto convertToDto(TopHeadlineResponse.Article article){
        ArticleDto dto = new ArticleDto();
        dto.setAuthor(article.getAuthor());
        dto.setArticleUrl(article.getUrl());
        dto.setDate(article.getPublishedAt());
        dto.setImageUrl(article.getUrlToImage());
        dto.setTitle(article.getTitle());
        dto.setDescription(article.getDescription());
        if(article.getSource() != null) {
            dto.setSourceName(article.getSource().getName());
        }
        return dto;
    }

}
