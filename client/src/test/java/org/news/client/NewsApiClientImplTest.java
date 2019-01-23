package org.news.client;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.news.client.dto.SourceResponse;
import org.news.client.dto.TopHeadlineResponse;
import org.news.client.exception.NewsClientException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

public class NewsApiClientImplTest extends BaseTest {

    private static boolean ENABLED_MOCK_SERVER = true; //zmienna na potrzeby strzałów symulacyjnnych do prawdziwego endpointa
    private MockRestServiceServer mockServer;
    private NewsApiConfig config;
    private NewsApiClient client;

    @Before
    public final void before(){
        RestTemplate restTemplate = new RestTemplate();
        config = new NewsApiConfig();
        config.setUrl("https://newsapi.org");
        config.setApiKey("02c5680f12fe4c85a5f2d8f7665b7760");

        client = new NewsApiClientImpl(config, restTemplate);
        if(ENABLED_MOCK_SERVER) {
            mockServer = MockRestServiceServer.bindTo(restTemplate).build();
        }
    }

    @After
    public final void after(){
        if(ENABLED_MOCK_SERVER) {
            mockServer.reset();
        }
    }

    private void verifyServer(){
        if(ENABLED_MOCK_SERVER) {
            mockServer.verify();
        }
    }

    @Test
    public void testTopHeadlineResponse(){
        mockServer.expect(ExpectedCount.times(1), requestTo(config.getUrl() + "/v2/top-headlines?apiKey=" + config.getApiKey()+ "&page=0&pageSize=20&country=pl"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withSuccess(new ClassPathResource("topHeadlinesResponse.json"), MediaType.APPLICATION_JSON));

        TopHeadlineResponse response = client.topHeadlines("pl", null, 0, 20);
        log.info("Response: {} " , response);
        verifyServer();

        Assert.assertEquals(1, response.getTotalResults());
        Assert.assertEquals(1, response.getArticles().size());

        TopHeadlineResponse.Article articleActual = response.getArticles().get(0);
        Assert.assertEquals("TVN24", articleActual.getAuthor());
    }

    @Test
    public void testTopHeadlineResponseClientError() {
        try {
            mockServer.expect(ExpectedCount.times(1), requestTo(config.getUrl() + "/v2/top-headlines?apiKey=" + config.getApiKey() + "&page=0&pageSize=120&country=pl"))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(MockRestResponseCreators.withBadRequest().body(new ClassPathResource("topHeadlinesClientErrorResponse.json")));
            client.topHeadlines("pl", null, 0, 120);
            Assert.fail();
        } catch(NewsClientException e){
            Assert.assertEquals("pageSizeTooBig", e.getCode());
            Assert.assertEquals("The maximum value of the pageSize param is 100 articles. You have requested 120.", e.getMessage());
        }
        verifyServer();
    }

    @Test
    public void testSourceResponse(){
        mockServer.expect(ExpectedCount.times(1), requestTo(config.getUrl() + "/v2/sources?apiKey=" + config.getApiKey()))
                .andExpect(method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withSuccess(new ClassPathResource("sourcesResponse.json"), MediaType.APPLICATION_JSON));

        SourceResponse response = client.sources(null, null, null);
        log.info("Response: {} " , response);
        verifyServer();

        SourceResponse.Source sourceActual = response.getSources().get(0);
        Assert.assertEquals("abc-news", sourceActual.getId());
    }
}