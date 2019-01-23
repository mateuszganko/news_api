package org.news.core.configuration;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter @Setter
@Configuration
@ConfigurationProperties(prefix= "news.client")
public class NewsClientProperties {

    private String url;
    private String apiKey;
}
