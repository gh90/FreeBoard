<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<h2 align="center">게시판 글보기</h2>
	
    <table width="700" border="3" bordercolor="lightgray" align="center" style="text-align: center">
    	<tr>
			<td width="30">카테고리 </td>
               <td id="category">카테고리</td>
               <td>수정일시</td>
               <td id="mod_date">수정2일시</td>
		</tr>
		<tr>
            <td>제 목 </td>
            <td colspan="4" id="title" ></td>
        </tr>
        <tr>
			<td>작성자</td>
			<td id="writer" width="30"></td>
			<td width="50">등록일자</td>
			<td id="reg_date" width="50">123123</td>
        </tr>
        <tr height="200">
            <td>내 용</td>
            <td  colspan="4" id="content"></td>        
        </tr>
        <tr align="center" valign="middle">
            <td colspan="5">	                
                <input type="button" value="목록" >
                <input id="post_modify" type="button" value="수정" >
                <input id="" type="button" value="삭제" >            
            </td>
        </tr>
    </table>    

<script type="text/javascript">
var post_id ='${post_id}';
console.log("get" + post_id)
$(document).ready(function(){
	init(post_id);
})
</script>
<script src="/resources/js/board/postView.js" defer="defer"></script>
