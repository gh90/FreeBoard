package kr.co.blockcom.board.vo.board;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class BoardFree implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1661457704979008310L;
	private int seq;
	private int parent_seq;
	private int category;
	private String writer;
	private String password;
	private String title;
	private String content;
	private int view_count;
	private String comment_flag;
	private String notice_flag;
	private String secret_flag;
	private String delete_flag;
	private Date mod_date;
	private Date reg_date;
}
