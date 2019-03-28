package com.bootdo.gamedata.vo;


/**
 * 此类将抽象化， 请使用其子类: PageResponse, GridResponse, BasicResponse
 * @author Chenguiban
 * @version 0.1, 2012-05-04
 */
public abstract  class GenericResponse { 

	public static final int FAILED_CODE = 1;//error code待定义。
	public static final int SUCCESS_CODE = 0;
	public static final String FAILED_MSG = "操作失败";
	public static final String SUCCESS_MSG = "操作成功";

	protected int resultCode = SUCCESS_CODE;
	protected String resultMessage = "";
	
	public GenericResponse() {
	}
	
	public GenericResponse(int resultCode, String resultMessage) {
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
	}
	
	/**
	 * @return the resultCode
	 */
	public int getResultCode() {
		return resultCode;
	}

	/**
	 * @return the resultMessage
	 */
	public String getResultMessage() {
		return resultMessage;
	}

	/**
	 * @param resultCode
	 *            the resultCode to set
	 */
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * @param resultMessage
	 *            the resultMessage to set
	 */
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}


}
