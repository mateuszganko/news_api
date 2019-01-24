package org.news.core.converters;

import org.junit.Assert;
import org.junit.Test;
import org.news.client.dto.TopHeadlineResponse;
import org.news.core.models.ArticleDto;
import org.news.core.models.NewsDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsConverterTest {

    @Test
    public void test(){
        ArticleConverter articleConverter = new ArticleConverter();
        NewsConverter converter = new NewsConverter(articleConverter);

        TopHeadlineResponse.Article article = new TopHeadlineResponse.Article();
        article.setAuthor("author");
        article.setContent("content");
        article.setPublishedAt(new Date());

        List<TopHeadlineResponse.Article> articles = new ArrayList<>();
        articles.add(article);

        TopHeadlineResponse headline = new TopHeadlineResponse();
        headline.setStatus("ok");
        headline.setTotalResults(1);
        headline.setArticles(articles);

        NewsDto newsDto = converter.topHeadlineToDto("pl", "technology", headline);
        Assert.assertEquals("technology", newsDto.getCategory());
        Assert.assertEquals("pl", newsDto.getCountry());
        Assert.assertEquals(1, newsDto.getArticles().size());

        ArticleDto articleDto = newsDto.getArticles().get(0);
        Assert.assertEquals(article.getAuthor(), articleDto.getAuthor());
    }

}