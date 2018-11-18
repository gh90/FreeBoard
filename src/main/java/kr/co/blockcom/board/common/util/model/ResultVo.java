package kr.co.blockcom.board.common.util.model;

import lombok.Data;

@Data
public class ResultVo<T> {
	private String code;
	private String message;	
	private String status;	
	private T data;
}
