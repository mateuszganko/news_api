package org.news.client.dto;

import lombok.Data;

@Data
public class BaseResponse  {

    /**
     * If the request was successful or not. Options: ok, error. In the case of error a code and message property will be populated.
     */
    protected String status;
}
