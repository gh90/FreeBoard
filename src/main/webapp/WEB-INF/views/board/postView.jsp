<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<h2 id="board_type" align="center">게시판 글보기</h2>
	
    <table style="text-align: center;width:700px;margin: auto;" border="2" bordercolor="black">
    	<tr class="modify_hide delete_hide">
    		<td> 조회수</td>
    		<td id="view_count"></td>
    		<td colspan="2" class="secret_show" style="display: none"><input type="button" id="secret_post_view" value="비밀글 보기">&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" id="secret_password" placeholder="password.."></td>
    		
    	</tr>
       	<tr>
			<td width="10%">카테고리 </td>
               <td id="category">카테고리</td>
               <td class="modify_hide delete_hide">수정일시</td>
               <td class="modify_hide delete_hide" id="mod_date"></td>
               <td class="modify_show delete_show" style="display:none" >비밀번호</td>
               <td class="modify_show delete_show" style="display:none"><input type="password" id="password"></td>
		</tr>
		<tr>
            <td style="max-width: 100">제 목 </td>
            <td colspan="3" id="title" class="modify_hide" ></td>
            <td colspan="3" class="modify_show" style="display: none" ><input type="text" id="title_modify" style="float: left" size="70" maxlength="100" ></td>
        </tr>
        <tr>
			<td>작성자</td>
			<td id="writer" width="30"></td>
			<td width="50">등록일자</td>
			<td id="reg_date" width="50">123123</td>
        </tr>
        <tr height="200">
            <td>내 용</td>
            <td  colspan="3" id="content" class="modify_hide"></td>
            <td  colspan="3" class="modify_show" style="display: none"><textarea id="content_modify" cols="72" rows="20"></textarea></td>
        </tr>
        <tr align="center" valign="middle">
            <td colspan="5">	                
                <input class="modify_hide delete_hide" id="post_list" type="button" value="목록" >
                <input class="modify_hide delete_hide secret_hide" id="post_modify" type="button" value="수정" >
                <input class="modify_show" id="post_modify_ok" style="display: none" type="button" value="확인" >
                <input class="modify_show" id="post_modify_no" style="display: none" type="button" value="취소" >
                <input class="delete_show" id="post_delete_ok" style="display: none" type="button" value="삭제" >
                <input class="delete_show" id="post_delete_no" style="display: none" type="button" value="취소" >
                <input class="modify_hide secret_hide delete_hide" id="post_delete" type="button" value="삭제" >            
            </td>
        </tr>
    </table>
    <h2  align="center">댓글영역</h2>
    <table id="comment_list" style="text-align: left;width:700px;margin: auto;" border="2" bordercolor="black">
    		<tr style="text-align: center;">
				<td colspan="3">댓글이 없습니다.</td>
			</tr>
			<!-- <tr>
				<td>hole</td><td>등록시간 : </td><td colspan="2"><input type="button" value="수정하기"><input type="button" value="삭제하기"></td> 
			</tr>
			<tr>
				<td colspan="5">내일은 월요일</td>
			</tr> -->
    </table>    
	<table style="text-align: left;width:700px;margin: auto;" border="2" bordercolor="black">
		<tr>
			<td>작성자 : <input id="cmt_writer" type="text"></td><td>패스워드 : <input  id="cmt_password" type="password"></td><td> 비밀 : <input  id="cmt_secret_flag" type="checkbox" value="Y"></td>
		</tr>
		<tr>
			<td colspan="2" style="border-color: white;">내용 : <input  id="cmt_content" type="text" size="70"></td><td><input type="button" id="cmt_reg_submit" value="등록하기"></td>
		</tr>
	</table>
<script type="text/javascript">
var post_id ='${post_id}';
$(document).ready(function(){
	init(post_id);
})
</script>
<script src="/resources/js/board/postView.js" defer="defer"></script>
