/**
 * 
 */

var one_click="Y";

$("#submit").on("click",function(){
	if(one_click=="Y"){
		fn_post_register()
	}
})


function fn_post_register(){
	one_click="N"
	var register_data = {
			"category" : $("#category").val(),
			"writer" : $("#writer").val(),
			"password" : $("#password").val(),
			"title" : $("#title").val(),
			"content" : $("#content").val(),
			"notice_flag" : $("#notice_flag").val(),
			"secret_flag" : $("#secret_flag").val()
		};
	
	
	$.ajax({
		type: 'post',
		url: '/board/writeSubmit.do',
		data: register_data,
		dataType: "json",
		contentType : 'application/json',
		success: function (data) {
			alert("글 등록 성공")
		},
		error: function (xhr, status, error) {
			alert("글 등록 실패")
		}
	});
	
	one_click="Y"
}