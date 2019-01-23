package org.news.core.converters;

import org.news.client.dto.TopHeadlineResponse;
import org.news.core.models.NewsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class NewsConverter {

    private final ArticleConverter articleConverter;

    @Autowired
    public NewsConverter(ArticleConverter articleConverter){
        this.articleConverter = articleConverter;
    }

    public NewsDto topHeadlineToDto(String country, String category, TopHeadlineResponse response){
        NewsDto dto = new NewsDto();
        dto.setCountry(country);
        dto.setCategory(category);
        dto.setArticles(response.getArticles().stream().map(article -> articleConverter.convertToDto(article)).collect(Collectors.toList()));
        return dto;
    }
}
