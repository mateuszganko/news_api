package org.news.client;

import org.news.client.dto.SourceResponse;
import org.news.client.dto.TopHeadlineResponse;

public interface NewsApiClient {

    /**
     * returns breaking news headlines for a country and category, or currently running on a single or multiple sources.
     * This is perfect for use with news tickers or anywhere you want to display live up-to-date news headlines and images.
     * @param country - The 2-letter ISO 3166-1 code of the country you want to get headlines for. Possible options: ae ar at au be bg br ca ch cn co cu cz de eg fr gb gr hk hu id ie il in it jp kr lt lv ma mx my ng nl no nz ph pl pt ro rs ru sa se sg si sk th tr tw ua us ve za . Note: you can't mix this param with the sources param.
     * @param category - The category you want to get headlines for. Possible options: business entertainment general health science sports technology . Note: you can't mix this param with the sources param.
     * @param page - Use this to page through the results if the total results found is greater than the page size.
     * @param pageSize - The number of results to return per page (request). 20 is the default, 100 is the maximum.
     * @return
     */
    TopHeadlineResponse topHeadlines(String country, String category, int page, int pageSize);


    /**
     *
     * @param country - Find sources that display news in a specific country. Possible options: ae ar at au be bg br ca ch cn co cu cz de eg fr gb gr hk hu id ie il in it jp kr lt lv ma mx my ng nl no nz ph pl pt ro rs ru sa se sg si sk th tr tw ua us ve za . Default: all countries.
     * @param category - Find sources that display news of this category. Possible options: business entertainment general health science sports technology . Default: all categories.
     * @param language - Find sources that display news in a specific language. Possible options: ar de en es fr he it nl no pt ru se ud zh . Default: all languages.
     * @return
     */
    SourceResponse sources(String country, String category, String language);
}
