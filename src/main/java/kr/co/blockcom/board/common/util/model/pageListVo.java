package kr.co.blockcom.board.common.util.model;

import java.util.List;

import kr.co.blockcom.board.vo.board.PageVo;
import lombok.Data;

@Data
public class pageListVo<E> {
	private PageVo pageVo;
	private List<E> data;
}
