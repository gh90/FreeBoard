package kr.co.blockcom.board.common.util;

import org.springframework.stereotype.Component;

import kr.co.blockcom.board.common.util.model.PageVo;

@Component
public class PageUtil {
	
	private PageUtil() {
		
	}
	
	public <T extends PageVo> T setPaging(T pageVo) {
		//기본블록사이즈 10
		pageVo.setBlockSize(3);	
		return setDefaultPaging(pageVo);
	}
	
	public <T extends PageVo> T setPaging(T pageVo,int blockSize) {
		//블록사이즈
		pageVo.setBlockSize(blockSize);
		return setDefaultPaging(pageVo);
	}
	
	private <T extends PageVo> T setDefaultPaging(T pageVo) {
		if(pageVo.getBlockSize()<1) {
			pageVo.setBlockSize(10);
		}
		if(pageVo.getPagingSize()<1) {
			pageVo.setPagingSize(10);
		}
		if(pageVo.getNowPage()<1) {
			pageVo.setNowPage(1);
		}
		pageVo.setTotalPage((int)((pageVo.getTotalPost()-1)/pageVo.getPagingSize())+1);
		
		if(pageVo.getNowPage()>pageVo.getTotalPage()) {
			pageVo.setNowPage(pageVo.getTotalPage());
		}
		//전체 페이지 갯수
		//해당 페이지의 시작게시물 limit 용
		pageVo.setStartPost(pageVo.getPagingSize()*(pageVo.getNowPage()-1));
		//해당 페이지가 속한 블록
		pageVo.setNowBlock(((int)(pageVo.getNowPage()-1)/pageVo.getBlockSize())+1);
		//마지막 블록
		pageVo.setLastBlock((int)((pageVo.getTotalPage()-1)/pageVo.getBlockSize())+1);
		//현재 페이지가 속한 블록의 시작페이지
		pageVo.setStartPage((pageVo.getNowBlock()-1)*(pageVo.getBlockSize())+1);
		//현재 페이지가 속한 블록의 마지막페이지
		pageVo.setEndPage((pageVo.getNowBlock())*(pageVo.getBlockSize()));
		
		//endpage 적합성 확인
		if(pageVo.getEndPage()>pageVo.getTotalPage()) {
			pageVo.setEndPage(pageVo.getTotalPage());
		}
		return pageVo;
		
		
	}

}
