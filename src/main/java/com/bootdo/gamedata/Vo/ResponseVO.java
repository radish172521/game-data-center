package com.bootdo.gamedata.Vo;

import lombok.Data;

import java.io.Serializable;


@Data
public class ResponseVO<T> extends GenericResponse implements Serializable{


  /**
   * 返回数据
   */
  private T data;


  public static <T> ResponseVO<T> ok(T t){
    ResponseVO responseVO = new ResponseVO();
    responseVO.setData(t);
    responseVO.setResultMessage(ResponseVO.SUCCESS_MSG);
    return responseVO;
  }

  public static ResponseVO<Void> ok(){
    return new ResponseVO<Void>();
  }

  public static <T> ResponseVO<T> error(String msg,Integer code){
    ResponseVO responseVO = new ResponseVO();
    responseVO.setResultMessage(msg);
    responseVO.setResultCode(code);
    return responseVO;


  }





}
