CREATE TABLE `board_category` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `board_free` (
  `seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '자유게시판 seq',
  `parent_seq` int(11) DEFAULT NULL COMMENT '댓글의 게시글 번호, 댓글만 값이 있음',
  `category` int(11) DEFAULT NULL COMMENT '카테고리',
  `writer` varchar(10) DEFAULT NULL COMMENT '작성자',
  `password` varchar(45) DEFAULT NULL,
  `title` varchar(30) DEFAULT NULL COMMENT '제목',
  `content` text COMMENT '내용',
  `view_count` int(11) DEFAULT '0' COMMENT '조회수',
  `comment_flag` char(1) DEFAULT 'N' COMMENT '댓글인지 아닌지',
  `notice_flag` char(1) DEFAULT 'N' COMMENT '공지 플래그',
  `secret_flag` char(1) DEFAULT 'N' COMMENT '비밀글 플래그',
  `delete_flag` char(1) DEFAULT 'N' COMMENT '삭제 플래그',
  `mod_date` datetime DEFAULT NULL,
  `reg_date` datetime DEFAULT NULL,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
