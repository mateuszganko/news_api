package org.news.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.news.client.dto.ErrorResponse;
import org.news.client.dto.SourceResponse;
import org.news.client.dto.TopHeadlineResponse;
import org.news.client.exception.NewsClientException;
import org.news.client.logger.LoggingRequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

public class NewsApiClientImpl implements NewsApiClient {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final static String COUNTRY_PARAM = "country";
    private final static String CATEGORY_PARAM = "category";
    private final static String SOURCES_PARAM = "sources";
    private final static String QUERY_PARAM = "q";
    private final static String LANGUAGE_PARAM = "language";

    private final static String PAGE_SIZE_PARAM = "pageSize";
    private final static String PAGE_PARAM = "page";
    private final static String API_KEY_PARAM = "apiKey";

    private final NewsApiConfig config;
    private final ObjectMapper mapper;
    private final RestTemplate restTemplate;

    public NewsApiClientImpl(NewsApiConfig config){
        this(config, new RestTemplate());
    }

    protected NewsApiClientImpl(NewsApiConfig config, RestTemplate restTemplate){
        validateConfig(config);
        this.config = config;
        this.restTemplate = restTemplate;
        this.mapper = new ObjectMapper();
        this.restTemplate.setErrorHandler(new ResponseErrorHandler());
        this.restTemplate.getInterceptors().add(new LoggingRequestInterceptor());
    }

    private static void validateConfig(NewsApiConfig config){
        if(StringUtils.isEmpty(config.getUrl())){
            throw new IllegalArgumentException("url must be not empty");
        }else if(StringUtils.isEmpty(config.getApiKey())) {
            throw new IllegalArgumentException("api key must be not empty");
        }
    }

    @Override
    public TopHeadlineResponse topHeadlines(String country, String category, int page, int pageSize) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(config.getUrl() + "/v2/top-headlines");
        uriBuilder.queryParam(API_KEY_PARAM, config.getApiKey());
        uriBuilder.queryParam(PAGE_PARAM, page);
        uriBuilder.queryParam(PAGE_SIZE_PARAM, pageSize);

        putIfNotNull(uriBuilder, COUNTRY_PARAM, country);
        putIfNotNull(uriBuilder, CATEGORY_PARAM, category);

        String uri = uriBuilder.toUriString();

        ResponseEntity<TopHeadlineResponse> response = restTemplate.getForEntity(uri, TopHeadlineResponse.class);
        return response.getBody();
    }

    @Override
    public SourceResponse sources(String country, String category, String language) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(config.getUrl() + "/v2/sources");
        uriBuilder.queryParam(API_KEY_PARAM, config.getApiKey());

        putIfNotNull(uriBuilder, COUNTRY_PARAM, country);
        putIfNotNull(uriBuilder, CATEGORY_PARAM, category);
        putIfNotNull(uriBuilder, LANGUAGE_PARAM, language);

        String uri = uriBuilder.toUriString();

        ResponseEntity<SourceResponse> response = restTemplate.getForEntity(uri, SourceResponse.class);
        return response.getBody();
    }

    private void putIfNotNull(UriComponentsBuilder builder, String paramName, Object param){
        if(!StringUtils.isEmpty(param)) {
            builder.queryParam(paramName, param);
        }
    }


    private class ResponseErrorHandler extends DefaultResponseErrorHandler {

        @Override
        public boolean hasError(ClientHttpResponse httpResponse)
                throws IOException {
            return (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                            || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
        }

        @Override
        protected void handleError(ClientHttpResponse response, HttpStatus statusCode) throws IOException {

            log.error("Response error: {} {}", response.getStatusCode(), response.getStatusText());

            if(response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR){
                ErrorResponse errorResponse = mapper.readValue(response.getBody(), ErrorResponse.class);
                throw new NewsClientException(errorResponse.getCode(), errorResponse.getMessage());
            }else if(response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR){
                throw new NewsClientException("serverError", "Unable download data from remote source");
            }
            //pomijam łapanie timeoutów i innych nieoczekiwanych błędów.
        }
    }
}
