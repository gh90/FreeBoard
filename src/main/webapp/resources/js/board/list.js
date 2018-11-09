/**
 * 
 */

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
			console.log("목록");
			console.log(data);
			var postList_html="";
			
			for(var temp_list in data ){
				postList_html+="<tr>";
				postList_html+="<td>"+data[temp_list].seq+"</td>";
				postList_html+="<td><a href='' >";
				postList_html+=data[temp_list].title;
				postList_html+="</a></td>";
				postList_html+="<td>";
				postList_html+=data[temp_list].writer;
				postList_html+="</td>";
				postList_html+="<td>";
				postList_html+=data[temp_list].reg_date;
				postList_html+="</td>";
				postList_html+="<td>";
				postList_html+=data[temp_list].view_count;
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
