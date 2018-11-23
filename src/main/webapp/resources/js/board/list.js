/**
 * 
 */

$(document).ready(function(){
	$("#write").on("click",function(){
		location.href="/board/write/view";
	})
	
	

	fn_select_postList(1);
})

function fn_select_postList(now_page){
	one_click="N"
	var post_list_data = {
			"category" : "1"/*$("#category").val()*/,
			"nowPage" : now_page
		};
	
	
	$.ajax({
		type: 'post',
		url: '/board/postList/xhr',
		data: JSON.stringify(post_list_data),
		dataType: "json",
		contentType : 'application/json; charset=UTF-8',
		success: function (data) {
			var postList_html="";
			var postPaging_html="";
			var post_list = data.data.data;
			var page_vo = data.data.pageVo
			console.log(page_vo);
			for(var post_element in post_list ){
				postList_html+="<tr>";
				postList_html+="<td>"+post_list[post_element].seq+"</td>";
				postList_html+="<td><a href='/board/post/view?post_id="+post_list[post_element].seq+"'>";
				postList_html+=post_list[post_element].title;
				postList_html+="</a></td>";
				postList_html+="<td>";
				postList_html+=post_list[post_element].writer;
				postList_html+="</td>";
				postList_html+="<td>";
				postList_html+= moment.unix(post_list[post_element].reg_date/1000).format("YY/MM/DD HH:MM");
				postList_html+="</td>";
				postList_html+="<td>";
				postList_html+=post_list[post_element].view_count;
				postList_html+="</td>";
				postList_html+="</tr>";
				
			}
			$("#postList").html(postList_html);
			if(page_vo.nowPage==1){
				postPaging_html+="<span><<</span> &nbsp;&nbsp;"
				postPaging_html+="<span><</span> &nbsp;&nbsp;"
			}else{
				postPaging_html+="<span class='page_move' data-page=1><<</span> &nbsp;&nbsp;"
				postPaging_html+="<span class='page_move' data-page="+(page_vo.nowPage*1-page_vo.blockSize*1)+"><</span> &nbsp;&nbsp;"
			}

			for(var i=page_vo.startPage;i<=page_vo.endPage;i++){
				if(page_vo.nowPage==i){
					postPaging_html+="<span style='font-weight:bold;'>"+i+"</span> &nbsp;&nbsp;"
				}else{
					postPaging_html+="<span class='page_move' data-page="+i+">"+i+"</span> &nbsp;&nbsp;"
				}

			}
			if(page_vo.nowPage==page_vo.totalPage){
				postPaging_html+="<span>></span> &nbsp;&nbsp;"
				postPaging_html+="<span>>></span> &nbsp;&nbsp;"
			}else{
				postPaging_html+="<span class='page_move' data-page="+(page_vo.nowPage*1+page_vo.blockSize*1)+">></span> &nbsp;&nbsp;"
				postPaging_html+="<span class='page_move' data-page="+page_vo.totalPage+">>></span> &nbsp;&nbsp;"
			}

			$("#post_page").html(postPaging_html);
			$(".page_move").hover(function (){console.log("aaaaa");$(this).css("cursor","pointer")},function (){$(this).css("cursor","")})
			fn_page_click()
		},
		error: function (xhr, status, error) {
			console.log("실패");
		}
	});
}

function fn_page_click(){
	$(".page_move").on("click",function(){
		fn_select_postList($(this).data("page"));
	})
}
