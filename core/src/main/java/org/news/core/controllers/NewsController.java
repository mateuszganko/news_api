package org.news.core.controllers;

import org.news.client.NewsApiClient;
import org.news.client.dto.TopHeadlineResponse;
import org.news.core.converters.NewsConverter;
import org.news.core.models.NewsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsApiClient client;
    private final NewsConverter newsConverter;

    @Autowired
    public NewsController(NewsApiClient client, NewsConverter newsConverter){
        this.client = client;
        this.newsConverter = newsConverter;
    }

    @GetMapping(value = "{country}/{category}")
    public NewsDto getNewsByCountryAndCategory(@PathVariable("country") String country,
                                               @PathVariable("category") String category,
                                               @RequestParam(value = "page", defaultValue = "0", required = false)  int page,
                                               @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize){
        TopHeadlineResponse topHeadlineResponse = client.topHeadlines(country, category, page, pageSize);
        return newsConverter.topHeadlineToDto(country, category, topHeadlineResponse);
    }
}