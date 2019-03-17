package com.ming.api;

import lombok.Data;

/**
 * @author Mingzhao
 * @date 2019/3/17 16:08
 */
@Data
public class BaseResponse<T> {
    private Integer rtnCode;
    private String msg;
    private T data;

    public BaseResponse() {

    }

    public BaseResponse(Integer rtnCode, String msg, T data) {
        super();
        this.rtnCode = rtnCode;
        this.msg = msg;
        this.data = data;
    }
}
