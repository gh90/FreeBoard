<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<h2 align="center">게시판 글보기</h2>
	
    <table width="700" border="3" bordercolor="lightgray" align="center" style="text-align: center">
    	<tr class="modify_hide">
    		<td> 조회수</td>
    		<td id="view_count"></td>
    		<td colspan="2" class="secret_hide" style="display: none"><input type="button" id="secret_post_view" value="비밀글 보기">&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" id="secret_password"></td>
    		
    	</tr>
       	<tr>
			<td width="100">카테고리 </td>
               <td id="category">카테고리</td>
               <td class="modify_hide">수정일시</td>
               <td class="modify_hide" id="mod_date"></td>
               <td class="modify_show" style="display:none" >비밀번호</td>
               <td class="modify_show" style="display:none"><input type="password" id="password"></td>
		</tr>
		<tr>
            <td>제 목 </td>
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
                <input class="modify_hide" id="post_list" type="button" value="목록" >
                <input class="modify_hide" id="post_modify" type="button" value="수정" >
                <input class="modify_show" id="post_modify_ok" style="display: none" type="button" value="확인" >
                <input class="modify_show" id="post_modify_no" style="display: none" type="button" value="취소" >
                <input class="modify_hide" id="post_delete" type="button" value="삭제" >            
            </td>
        </tr>
    </table>    

<script type="text/javascript">
var post_id ='${post_id}';
$(document).ready(function(){
	init(post_id);
})
</script>
<script src="/resources/js/board/postView.js" defer="defer"></script>
