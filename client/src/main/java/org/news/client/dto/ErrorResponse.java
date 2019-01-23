package org.news.client.dto;

import lombok.Data;

@Data
public class ErrorResponse extends BaseResponse {

    /**
     *  request code error
     */
    protected String code;

    /**
     *  request message error
     */
    protected String message;

    @Override
    public String toString() {
        return "BaseResponse{" +
                "status='" + status + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
