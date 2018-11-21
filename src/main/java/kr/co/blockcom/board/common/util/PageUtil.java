package kr.co.blockcom.board.common.util;

import org.springframework.stereotype.Component;

import kr.co.blockcom.board.vo.board.PageVo;

@Component
public final class PageUtil {
	
	
	public PageVo setPaging(PageVo pageVo) {
		//기본블록사이즈 10
		pageVo.setBlockSize(10);	
		return setDefaultPaging(pageVo);
	}
	
	public PageVo setPaging(PageVo pageVo,int blockSize) {
		//블록사이즈
		pageVo.setBlockSize(blockSize);
		return setDefaultPaging(pageVo);
	}
	
	private PageVo setDefaultPaging(PageVo pageVo) {
		if(pageVo.getBlockSize()<1) {
			pageVo.setBlockSize(10);
		}
		if(pageVo.getPagingSize()<1) {
			pageVo.setPagingSize(10);
		}
		if(pageVo.getNowPage()<1) {
			pageVo.setNowPage(1);
		}
		pageVo.setTotalPage((int)(pageVo.getTotalPost()/pageVo.getPagingSize())+1);
		
		if(pageVo.getNowPage()>pageVo.getTotalPage() ) {
			pageVo.setNowPage(pageVo.getTotalPage());
		}
		//전체 페이지 갯수
		//해당 페이지의 시작게시물 limit 용
		pageVo.setStartPost(pageVo.getPagingSize()*(pageVo.getNowPage()-1));
		//해당 페이지가 속한 블록
		pageVo.setNowBlock(((int)pageVo.getTotalPage()/pageVo.getBlockSize())+1);
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
