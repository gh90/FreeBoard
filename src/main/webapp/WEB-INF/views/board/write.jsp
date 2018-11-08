<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

		<h2>게시판 글쓰기 이야야양</h2>
		
	    <table width="700" border="3" bordercolor="lightgray" align="center">
	        <tr>
	            <td>작성자</td>
	            <td>
	              <input id="writer" type="text">
	            </td>
	            <td>비밀번호</td>
                   <td>
                     <input id="password" type="password">
                   </td>
	        </tr>
	        <tr>
                   <td>카테고리 </td>
                   <td colspan="5">
                   <!-- // TODO DB 에서 카테고리 긁어와서 뿌려주기  -->
                       <select name="category" id="category" style="width:100px;">
                        <option value="1">자게1</option>
                        <option value="2">자게2</option>
                        <option value="3">자게3</option>
                       </select>
                   </td>        
               </tr>
	        <tr>
                   <td>제 목 </td>
	            <td colspan="4">
	                <input id="title" type="text" size="70" maxlength="100" value=""/>
	            </td>        
	        </tr>
	        <tr>
	            <td>내 용</td>
	            <td  colspan="4">
	                <textarea id="content" cols="72" rows="20"></textarea>            
	            </td>        
	        </tr>
	        <tr>
	            <td>공지 등록 </td>
	            <td>
	                <input type="checkbox" id="notice_flag" value="Y" />
	            </td>
	            <td>비밀글  등록</td>
                   <td>
                       <input type="checkbox" id="secret_flag" value="Y" />
                   </td>    
	        </tr>
	 
	        <tr align="center" valign="middle">
	            <td colspan="5">
	                <input type="reset" value="작성취소" >
	                <input id="submit" type="button" value="등록" >
	                <input type="button" value="목록" >            
	            </td>
	        </tr>
	    </table>    


	    
<script src="/resources/js/board/write.js" defer="defer"></script>
