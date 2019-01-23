package org.news.client.dto;

import lombok.Data;

import java.util.List;

@Data
public final class SourceResponse extends BaseResponse {

    private List<Source> sources;

    @Override
    public String toString() {
        return "SourceResponse{" +
                "sources=" + sources +
                ", status='" + status + '\'' +
                '}';
    }

    @Data
    public static class Source {
        /**
         *  The identifier of the news source. You can use this with our other endpoints.
         */
        private String id;

        /**
         *  The name of the news source
         */
        private String name;

        /**
         * A description of the news source
         */
        private String description;

        /**
         *  The URL of the homepage.
         */
        private String url;

        /**
         * The type of news to expect from this news source.
         */
        private String category;

        /**
         * The language that this news source writes in.
         */
        private String language;

        /**
         * The country this news source is based in (and primarily writes about).
         */
        private String country;

        @Override
        public String toString() {
            return "Source{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", url='" + url + '\'' +
                    ", category='" + category + '\'' +
                    ", language='" + language + '\'' +
                    ", country='" + country + '\'' +
                    '}';
        }
    }
}
