/**
 * 
 */
$(document).ready(function(){
	$("#post_modify").on("click",function(){
		fn_post_modify();
	});
})


function init(post_id){
	fn_post_view(post_id);
}

function fn_post_view(post_id){
	$.ajax({
		type: 'post',
		data: post_id, 
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

function fn_post_modify(){
	
	var modify_data = {
			"seq" :"39",
			"password" :$("#password").val()
		};
	
	
	$.ajax({
		type: 'post',
		data: JSON.stringify(modify_data), 
		url: '/board/modify',
		contentType:'application/json; charset=UTF-8',
		success: function (data) {
		console.log(data);
		
		},
		error: function (xhr, status, error) {
			console.log(xhr);
			console.log(status);
			console.log(error);
		}
	});
}