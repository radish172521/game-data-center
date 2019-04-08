package com.bootdo.common.utils;

import lombok.Data;

@Data
public class ResponseVO<T> {

    public static final int FAILED_CODE = 1;
    public static final int SUCCESS_CODE = 0;

    private int code = SUCCESS_CODE;
    private String msg = "";
    /**
     * 返回数据
     */
    private T data;

    public static <T> ResponseVO<T> ok(T t) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setData(t);
        return responseVO;
    }

    public static ResponseVO<Void> ok() {
        return new ResponseVO<Void>();
    }

    public static <T> ResponseVO<T> error(String msg, Integer code, T t) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setMsg(msg);
        responseVO.setCode(code);
        responseVO.setData(t);
        return responseVO;
    }
}
