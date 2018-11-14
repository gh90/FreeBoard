/**
 * 
 */
$(document).ready(function(){
	
	$("#post_list").on("click",function(){
		location.href="/board/list";
	});
	$("#post_modify").on("click",function(){
		fn_modify_mode();
	});
	
	$("#post_modify_no").on("click",function(){
		fn_modify_mode_no();
	});
	
	$("#post_modify_ok").on("click",function(){
		if(confirm("정말 수정하시겠습니까?")){
			fn_post_modify();
		}else{
			return false;
		}
	});
	
	$("#secret_post_view").on("click",function(){
		fn_modify_mode_no();
	});
})


function init(post_id){
	fn_post_view(post_id);
}

function fn_post_view(post_id,secret_passsword){
	var post_view_data = {"seq":post_id,"passsword":passsword}
	$.ajax({
		type: 'post',
		data: JSON.stringify(post_view_data), 
		url: '/board/postView',
		contentType:'application/json; charset=UTF-8',
		success: function (json) {
			console.log(json)
			if("0000"==json.code){
				$("#category").text(json.data.category);
				$("#mod_date").text(json.data.mod_date);
				$("#title").text(json.data.title);
				$("#writer").text(json.data.writer);
				$("#reg_date").text(json.data.reg_date);
				$("#content").text(json.data.content);
				$("#view_count").text(json.data.view_count);
				
				if("Y"==json.data.secret_flag){
					$(".secret_hide").show();
				}
				
			}else{
				alert(json.message);
				location.href="/board/list";
			}
			//비밀글 처리 필요
		
		},
		error: function (xhr, status, error) {
			console.log(xhr);
			console.log(status);
			console.log(error);
		}
	});
	
	
}

function fn_modify_mode(){
	$(".modify_hide").hide();
	$(".modify_show").show();
	$("#title_modify").find(":input").val($("#title").text());
	$("#content_modify").find("textarea").val($("#content").text())
	$("#password").val("");
	
}

function fn_modify_mode_no(){
	$(".modify_hide").show();
	$(".modify_show").hide();
}

function fn_post_modify(){
	
	var modify_data = {
			"seq" :post_id,
			"password" :$("#password").val(),
			"title": $("#title_modify").val(),
			"content" : $("#content_modify").val()
		};
	
	
	$.ajax({
		type: 'post',
		data: JSON.stringify(modify_data), 
		url: '/board/modifySubmit',
		contentType:'application/json; charset=UTF-8',
		success: function (data) {
			if("0000"==data.code){
				alert("글을 수정하였습니다.");
				location.href ="/board/postView?post_id="+post_id;
			}else{
				alert(data.message);
			}
		},
		error: function (xhr, status, error) {
			console.log(xhr);
			console.log(status);
			console.log(error);
		}
	});
}