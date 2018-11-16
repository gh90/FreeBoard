package kr.co.blockcom.board.common.util.model;

public enum ReturnStatusCode {
	
	SUCCESS("0000", "Success"),
	FAIL("0001", "fail"),
	
	WRONG_PASSWORD("1001","패스워드가 틀렸습니다."),
	INVALID_PAGE("1002","잘못된 페이지 이동입니다."),
	
	NO_PASSWORD("1101","패스워드를 입력해 주세요."),
	NO_POST("1102","글이 존재하지않습니다."),
	NO_TITLE("1103","제목을 입력해주세요."),
	NO_COTENT("1102","글을 입력해주세요."),
	NO_WRITER("1102","작성자를 입력해주세요.");
	
	
	
	private String code;
	private String message;
	
	ReturnStatusCode(String code, String message){
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	

}
