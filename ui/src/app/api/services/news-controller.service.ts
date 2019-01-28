/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { NewsDto } from '../models/news-dto';

/**
 * News Controller
 */
@Injectable({
  providedIn: 'root',
})
class NewsControllerService extends __BaseService {
  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * @param params The `NewsControllerService.GetNewsByCountryAndCategoryUsingGETParams` containing the following parameters:
   *
   * - `country`: country
   *
   * - `category`: category
   *
   * - `pageSize`: pageSize
   *
   * - `page`: page
   *
   * @return OK
   */
  getNewsByCountryAndCategoryUsingGETResponse(params: NewsControllerService.GetNewsByCountryAndCategoryUsingGETParams): __Observable<__StrictHttpResponse<NewsDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;


    if (params.pageSize != null) __params = __params.set('pageSize', params.pageSize.toString());
    if (params.page != null) __params = __params.set('page', params.page.toString());
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/news/${params.country}/${params.category}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<NewsDto>;
      })
    );
  }
  /**
   * @param params The `NewsControllerService.GetNewsByCountryAndCategoryUsingGETParams` containing the following parameters:
   *
   * - `country`: country
   *
   * - `category`: category
   *
   * - `pageSize`: pageSize
   *
   * - `page`: page
   *
   * @return OK
   */
  getNewsByCountryAndCategoryUsingGET(params: NewsControllerService.GetNewsByCountryAndCategoryUsingGETParams): __Observable<NewsDto> {
    return this.getNewsByCountryAndCategoryUsingGETResponse(params).pipe(
      __map(_r => _r.body as NewsDto)
    );
  }
}

module NewsControllerService {

  /**
   * Parameters for getNewsByCountryAndCategoryUsingGET
   */
  export interface GetNewsByCountryAndCategoryUsingGETParams {

    /**
     * country
     */
    country: string;

    /**
     * category
     */
    category: string;

    /**
     * pageSize
     */
    pageSize?: number;

    /**
     * page
     */
    page?: number;
  }
}

export { NewsControllerService }
