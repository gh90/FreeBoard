package kr.co.blockcom.board.common.util.model;

import java.util.List;

import lombok.Data;

@Data
public class pageListVo<E> {
	private E pageVo;
	private List<E> data;
}
