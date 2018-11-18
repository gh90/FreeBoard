package kr.co.blockcom.board.common.util;

import org.springframework.stereotype.Component;

import kr.co.blockcom.board.common.util.model.ResultVo;
import kr.co.blockcom.board.common.util.model.ReturnStatusCode;

@Component
public class ResultCodeUtil {

	private ResultCodeUtil() {
	}
	
	public <R> ResultVo<R> getResultInfo(ReturnStatusCode code, R r){
		ResultVo<R> result = new ResultVo<R>();
		result.setCode(code.getCode());
		result.setStatus(code.getCode());
		result.setMessage(code.getMessage());
		result.setData(r);
		return result;
	}
	
	public <R>ResultVo<R> getResultInfo(ReturnStatusCode code){
		ResultVo<R> result = new ResultVo<R>();
		result.setCode(code.getCode());
		result.setStatus(code.getCode());
		result.setMessage(code.getMessage());
		result.setData(null);
		return result;
	}
	
}
