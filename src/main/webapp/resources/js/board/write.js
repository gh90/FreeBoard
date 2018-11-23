/**
 * 
 */
let content;
$(document).ready(function(){	
	ClassicEditor
			.create( document.querySelector( '#content' ),{
				language:'ko'
			} )
			.then(newEditor => {
				content=newEditor;
			}).catch( error => {
				console.error( error );
			});
})

$("#submit").on("click",function(){
	if(confirm("정말 등록하시겠습니까?")){
			fn_post_register()
		}else{
			return false;
		}
})

$("#post_list").on("click",function(){
	location.href="/board/list/view";
})

function fn_post_register(){
	var register_data = {
			"category" : $("#category").val(),
			"writer" : $("#writer").val(),
			"password" : $("#password").val(),
			"title" : $("#title").val(),
			"content" : content.getData(),
			"notice_flag" : fn_boolean_check($("#notice_flag").prop("checked")),
			"secret_flag" : fn_boolean_check($("#secret_flag").prop("checked"))
		};
	
	
	$.ajax({
		type: 'post',
		url: '/board/write/xhr',
		data: JSON.stringify(register_data),
		dataType: "json",
		contentType : 'application/json; charset=UTF-8',
		success: function (json) {
			if("0000"==json.code){
				alert("글이 등록되었습니다.");
				location.href ="/board/post/view?post_id="+json.data
			}else{
				alert(json.message);
			}
		},
		error: function (xhr, status, error) {
			alert("글 등록 실패")
		}
	});
}

function fn_boolean_check(check){
	return check==true?"Y":"N";
}