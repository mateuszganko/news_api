package org.news.client.exception;

import lombok.Getter;

@Getter
public class NewsClientException extends RuntimeException {

    private String code;
    private String message;


    public NewsClientException(String code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "NewsClientException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
