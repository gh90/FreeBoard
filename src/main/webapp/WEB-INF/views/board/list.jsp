<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<h2 align="center">게시판 리스트</h2>
		<div >
			<div style="float: left">
			</div>
			<table width=700 summary="메뉴버튼" border="0" style="padding-top: 200;float: auto;text-align: right;margin-left: auto;margin-right: auto" >
					<tr>						
						<td style="text-align: left">
							카테고리 :
							<select name="category" id="category">
								<option value="1">자게1</option>
								<option value="2">자게2</option>	
								<option value="3">자게3</option>	
							</select>
						</td>
						<td>
							<input type="button" id="write" value="글쓰기">
						</td>
					</tr>
			</table>
			<table width=700 summary="자유게시판 리스트" border="1" style="padding-top: 200;float: auto;text-align: center;margin-left: auto;margin-right: auto" >
				<colgroup>
					<col style="width:10%;">
					<col style="width:40%;">
					<col style="width:20%;">
					<col style="width:20%;">
					<col style="width:10%;">
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody id="postList">
					<tr>
						<td>번호</td>
						
						<td>
							<a href="" >
									글제목
							</a>
						</td>
						<td>
								작성자
						</td>
						
						<td>
								작성일
						</td>
						<td>
								조회수
						</td>
					</tr>
				</tbody>
			</table>
			<div id="post_page" style="text-align: center">
				<span style="font-weight: bold;">1</span> <span>2</span>
			</div>
		</div>
		<!-- //table -->
<script src="/resources/js/board/list.js" defer="defer"></script>
