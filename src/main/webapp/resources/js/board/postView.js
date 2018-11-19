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
		fn_secret_view()
		
	});

	$("#post_delete").on("click",function(){
		fn_delet_mode()
		
	});

	$("#post_delete_no").on("click",function(){
		fn_delete_mode_no();
	});
	

	$("#post_delete_ok").on("click",function(){
		if(confirm("정말 삭제하시겠습니까?")){
			fn_post_delete();
		}else{
			return false;
		}
	});
	
	$("#cmt_reg_submit").on("click",function(){
		if(confirm("정말 등록하시겠습니까?")){
			fn_comment_register();
		}else{
			return false;
		}
	});	
	
})


function init(post_id){
	fn_post_view(post_id);
	fn_select_commentList();
}

function fn_post_view(post_id){
	$.ajax({
		type: 'post',
		data: {"post_id":post_id}, 
		url: '/board/postView',
		success: function (json) {
			if("0000"==json.code){
				$("#category").text(json.data.category);
				$("#mod_date").text(json.data.mod_date);
				$("#title").text(json.data.title);
				$("#writer").text(json.data.writer);
				$("#reg_date").text(json.data.reg_date);
				$("#content").text(json.data.content);
				$("#view_count").text(json.data.view_count);
				
				if("Y"==json.data.secret_flag){
					$(".secret_show").show();
					$(".secret_hide").hide();
				}
				
			}else{
				alert(json.message);
				location.href="/board/list";
			}
		},
		error: function (xhr, status, error) {
			console.log(xhr);
			console.log(status);
			console.log(error);
		}
	});
}

function fn_cmt_view(post_id){
	$.ajax({
		type: 'post',
		data: {"post_id":post_id}, 
		url: '/board/postView',
		success: function (json) {
			if("0000"==json.code){
				$("#category").text(json.data.category);
				$("#mod_date").text(json.data.mod_date);
				$("#title").text(json.data.title);
				$("#writer").text(json.data.writer);
				$("#reg_date").text(json.data.reg_date);
				$("#content").text(json.data.content);
				$("#view_count").text(json.data.view_count);
				
				if("Y"==json.data.secret_flag){
					$(".secret_show").show();
					$(".secret_hide").hide();
				}
				
			}else{
				alert(json.message);
				location.href="/board/list";
			}
		},
		error: function (xhr, status, error) {
			console.log(xhr);
			console.log(status);
			console.log(error);
		}
	});
}

function fn_modify_mode(){
	$("#board_type").text("게시글 수정하기");
	$(".modify_hide").hide();
	$(".modify_show").show();
	$("#title_modify").val($("#title").text());
	$("#content_modify").val($("#content").text())
	$("#password").val("");
	
}

function fn_modify_mode_no(){
	$("#board_type").text("게시판 글보기");
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

function fn_secret_view(){
	
	var secret_data = {
			post_id:post_id,
			secret_password:$("#secret_password").val()
		};
	$.ajax({
		type: 'post',
		data: secret_data,
		url: '/board/secretView',
		success: function (json) {
			if("0000"==json.code){
				$("#title").text(json.data.title);
				$("#content").text(json.data.content);
				$(".secret_show").hide();
				$(".secret_hide").show();
			}else{
				alert(json.message);
			}
		},
		error: function (xhr, status, error) {
			console.log(xhr);
			console.log(status);
			console.log(error);
		}
	});
}

function fn_post_delete(){
	
	var delete_data = {
			post_id:post_id,
			delete_password:$("#password").val()
		};
	$.ajax({
		type: 'post',
		data: delete_data,
		url: '/board/deleteSubmit',
		success: function (json) {
			if("0000"==json.code){
				alert("글을 삭제하였습니다.");
				location.href ="/board/list"
				
			}else{
				alert(json.message);
			}
		},
		error: function (xhr, status, error) {
			console.log(xhr);
			console.log(status);
			console.log(error);
		}
	});
}

function fn_delet_mode(){
	$("#board_type").text("게시글 삭제하기");
	$(".delete_hide").hide();
	$(".delete_show").show();
	$("#password").val("");
	
}

function fn_delete_mode_no(){
	$("#board_type").text("게시판 글보기");
	$(".delete_hide").show();
	$(".delete_show").hide();
}

function fn_comment_register(){
	var register_data = {
			"category" : "1",
			"parent_seq" : post_id,
			"writer" : $("#cmt_writer").val(),
			"password" : $("#cmt_password").val(),
			"content" : $("#cmt_content").val(),
			"secret_flag" : fn_boolean_check($("#cmt_secret_flag").prop("checked"))
		};
	
	
	$.ajax({
		type: 'post',
		url: '/board/writeCommentSubmit',
		data: JSON.stringify(register_data),
		dataType: "json",
		contentType : 'application/json; charset=UTF-8',
		success: function (json) {
			if("0000"==json.code){
				alert("글이 등록되었습니다.");
				fn_select_commentList();
			}else{
				alert(json.message);
			}
		},
		error: function (xhr, status, error) {
			alert("글 등록 실패")
		}
	});
	
}


function fn_select_commentList(){
	one_click="N"
	var comment_list_data = {
			"category" : "1"/*$("#category").val()*/
			,"parent_seq":post_id
		};
	
	$.ajax({
		type: 'post',
		url: '/board/commentList',
		data: JSON.stringify(comment_list_data),
		dataType: "json",
		contentType : 'application/json; charset=UTF-8',
		success: function (data) {
			var commentList_html="";

			if(data.length>0){
			
				for(var temp_list in data ){
					commentList_html+="<div class='comment'>"
					commentList_html+="<tr id='seq_"+ data[temp_list].seq +"'>";
					commentList_html+="<td>"+data[temp_list].writer+"</td><td>등록 시간 : "+data[temp_list].reg_date+"</td>";
					commentList_html+="<td colspan='2'><input type='button' data-type='modify' class='cmt_mod_hide' value='수정하기'><input type='button' data-type='delete' class='cmt_mod_hide' value='삭제하기'>"
					commentList_html+="<div class='cmt_mod_show' style='display:none'><input type='password'><input type='button' value='확인'><input type='button' value='취소'></div></td>"
					commentList_html+="</tr>"
					commentList_html+="<tr class='cmt_mod_hide'><td colspan='5'>"+data[temp_list].content+"</td></tr>"
					commentList_html+="<tr class='cmt_mod_show' style='display:none'><td colspan='5'><input type='text'></td></tr>"
					commentList_html+="</div>"
				}
				
			}else{
				commentList_html+="<tr style='text-align:center;'>";
				commentList_html+="<td colspan='3'>댓글이 없습니다.</td>";
				commentList_html+="</tr>";
			}
			$("#comment_list").html(commentList_html);
			comment_mode();
			
		},
		error: function (xhr, status, error) {
			console.log("실패");
		}
	});
	
	one_click="Y"
}

function fn_boolean_check(check){
	return check==true?"Y":"N";
}

function comment_mode(){
	$("#comment_list").find(":input").on("click", function(){
		console.log($(this).val);
		if($(this).data("type")=="modify"){
			
		}else if($(this).data("type")=="delete"){
			console.log("삭제하기")
		}
	})
}
