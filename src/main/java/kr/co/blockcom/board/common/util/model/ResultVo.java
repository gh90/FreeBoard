package kr.co.blockcom.board.common.util.model;


public class ResultVo<T> {
	private String code;
	private String message;	
	private ReturnStatusCode returnStatusCode;	
	private T data;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	public void setReturnStatusCode(ReturnStatusCode returnStatusCode ) {
		this.returnStatusCode=returnStatusCode;
		this.message = returnStatusCode.getMessage();
		this.code = returnStatusCode.getCode();
	}
	
	public ReturnStatusCode getReturnStatusCode() {
		return returnStatusCode;
	}
	
}
