/**
 * 
 */

var one_click="Y";

$("#submit").on("click",function(){
	if(one_click=="Y"){
		fn_post_register()
	}
})

$("#post_list").on("click",function(){
	location.href="/board/list";
})

function fn_post_register(){
	one_click="N"
	var register_data = {
			"category" : $("#category").val(),
			"writer" : $("#writer").val(),
			"password" : $("#password").val(),
			"title" : $("#title").val(),
			"content" : $("#content").val(),
			"notice_flag" : fn_boolean_check($("#notice_flag").prop("checked")),
			"secret_flag" : fn_boolean_check($("#secret_flag").prop("checked"))
		};
	
	
	$.ajax({
		type: 'post',
		url: '/board/writeSubmit',
		data: JSON.stringify(register_data),
		dataType: "json",
		contentType : 'application/json; charset=UTF-8',
		success: function (data) {
			alert("글 등록 성공")
		},
		error: function (xhr, status, error) {
			alert("글 등록 실패")
		}
	});
	
	one_click="Y"
}


function fn_boolean_check(check){
	return check==true?"Y":"N";
}