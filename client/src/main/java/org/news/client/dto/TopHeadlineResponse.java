package org.news.client.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public final class TopHeadlineResponse extends BaseResponse {

    /**
     * The total number of results available for your request.
     */
    private int totalResults;

    /**
     * The results of the request.
     */
    private List<Article> articles;

    @Override
    public String toString() {
        return "TopHeadlineResponse{" +
                "totalResults=" + totalResults +
                ", articles=" + articles +
                ", status='" + status + '\'' +
                '}';
    }

    @Data
    public static class Article {
        /**
         * The identifier id and a display name name for the source this article came from.
         */
        private Source source;

        /**
         * The author of the article
         */
        private String author;

        /**
         * The headline or title of the article.
         */
        private String title;

        /**
         * A description or snippet from the article.
         */
        private String description;

        /**
         * The direct URL to the article.
         */
        private String url;

        /**
         * The URL to a relevant image for the article.
         */
        private String urlToImage;

        /**
         * The date and time that the article was published, in UTC (+000)
         */
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss", timezone = "UTC")
        private Date publishedAt;

        /**
         * The unformatted content of the article, where available. This is truncated to 260 chars for Developer plan users.
         */
        private String content;

        @Override
        public String toString() {
            return "Article{" +
                    "source=" + source +
                    ", author='" + author + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", url='" + url + '\'' +
                    ", urlToImage='" + urlToImage + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }

        @Data
        public static class Source{
            private String id;
            private String name;
            @Override
            public String toString() {
                return "Source{" +
                        "id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        '}';
            }
        }
    }
}
