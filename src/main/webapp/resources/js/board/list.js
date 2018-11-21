/**
 * 
 */

$(document).ready(function(){
	$("#write").on("click",function(){
		location.href="/board/write";
	})
	
	fn_select_postList();
})

var one_click="Y";


function fn_select_postList(){
	one_click="N"
	var post_list_data = {
			"category" : "1"/*$("#category").val()*/
		};
	
	
	$.ajax({
		type: 'post',
		url: '/board/postList',
		data: JSON.stringify(post_list_data),
		dataType: "json",
		contentType : 'application/json; charset=UTF-8',
		success: function (data) {
			var postList_html="";
			var post_list = data.data;
			
			for(var post_element in post_list ){
				postList_html+="<tr>";
				postList_html+="<td>"+post_list[post_element].seq+"</td>";
				postList_html+="<td><a href='/board/postView?post_id="+post_list[post_element].seq+"'>";
				postList_html+=post_list[post_element].title;
				postList_html+="</a></td>";
				postList_html+="<td>";
				postList_html+=post_list[post_element].writer;
				postList_html+="</td>";
				postList_html+="<td>";
				postList_html+=post_list[post_element].reg_date;
				postList_html+="</td>";
				postList_html+="<td>";
				postList_html+=post_list[post_element].view_count;
				postList_html+="</td>";
				postList_html+="</tr>";
				
			}
			$("#postList").html(postList_html);
			
		},
		error: function (xhr, status, error) {
			console.log("실패");
		}
	});
	
	one_click="Y"
}
