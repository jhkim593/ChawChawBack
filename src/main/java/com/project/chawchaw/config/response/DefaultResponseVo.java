package com.project.chawchaw.config.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DefaultResponseVo<T> {

    private String responseMessage;
    private Boolean isSuccess;
    private T data;

    public DefaultResponseVo(String responseMessage, Boolean isSuccess) {
        this.responseMessage = responseMessage;
        this.isSuccess = isSuccess;
    }

    public static<T> DefaultResponseVo<T> res(final String responseMessage, final Boolean isSuccess) {
        return res(responseMessage, isSuccess, null);
    }

    public static<T> DefaultResponseVo<T> res(final String responseMessage, final Boolean isSuccess, final T t) {
        return DefaultResponseVo.<T>builder()
                .data(t)
                .responseMessage(responseMessage)
                .isSuccess(isSuccess)
                .build();
    }

}