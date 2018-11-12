/**
 * 
 */

$(document).ready(function(){
	fn_post_view();
})


function fn_post_view(){
	$.ajax({
		type: 'post',
		data: $("#post_id").val().toString(), 
		url: '/board/postView',
		contentType:'application/json; charset=UTF-8',
		success: function (data) {
		$("#category").text(data.category);
		$("#mod_date").text(data.mod_date);
		$("#title").text(data.title);
		$("#writer").text(data.writer);
		$("#reg_date").text(data.reg_date);
		$("#content").text(data.content);
		
		},
		error: function (xhr, status, error) {
			console.log(xhr);
			console.log(status);
			console.log(error);
		}
	});
	
	
}
