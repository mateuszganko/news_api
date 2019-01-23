package org.news.core.models;

import lombok.Data;

import java.util.List;

@Data
public class NewsDto {

    private String country;
    private String category;
    private List<ArticleDto> articles;


    @Override
    public String toString() {
        return "NewsDto{" +
                "country='" + country + '\'' +
                ", category='" + category + '\'' +
                ", articles=" + articles +
                '}';
    }
}
