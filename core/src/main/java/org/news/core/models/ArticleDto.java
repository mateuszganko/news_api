package org.news.core.models;

import lombok.Data;

@Data
public class ArticleDto {

    private String author;
    private String title;
    private String description;
    private String date;
    private String sourceName;
    private String articleUrl;
    private String imageUrl;

    @Override
    public String toString() {
        return "ArticleDto{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", articleUrl='" + articleUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
