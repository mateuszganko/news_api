package org.news.core.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ArticleDto {

    private String author;
    private String title;
    private String description;

    @ApiModelProperty(example = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
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
