package org.news.client;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NewsApiConfig {

    /**
     * secret API key
     */
    private String apiKey;

    /**
     * base api url
     */
    private String url;
}
