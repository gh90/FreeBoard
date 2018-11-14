<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<div>
			<input type="button" id="write" value="글쓰기">
		</div>
		<div>
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
						<th scope="col">번호</th>
						<th scope="col">제목</th>
						<th scope="col">작성자</th>
						<th scope="col">작성일</th>
						<th scope="col">조회수</th>
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
		</div>
		<!-- //table -->
<script src="/resources/js/board/list.js" defer="defer"></script>
