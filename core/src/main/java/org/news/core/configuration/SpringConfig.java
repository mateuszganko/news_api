package org.news.core.configuration;

import org.news.client.NewsApiClient;
import org.news.client.NewsApiClientImpl;
import org.news.client.NewsApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Autowired
    private NewsClientProperties newsClientProperties;

    @Bean("newsApiClient")
    public NewsApiClient newsApiClient(){
        NewsApiConfig config = new NewsApiConfig();
        config.setUrl(newsClientProperties.getUrl());
        config.setApiKey(newsClientProperties.getApiKey());
        return new NewsApiClientImpl(config);
    }
}
