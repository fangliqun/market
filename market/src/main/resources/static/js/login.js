$(document).ready(function(){
	var userid;
	var loginurl="/userLogin";
	$(".login").click(function(){//多个button? 必须放在初始化里?
		var username=$("#username").val();
		var password=$("#password").val();
		$.ajax({
			url:loginurl,
			type: 'POST',
			data:{"username":username,"password":password},
			dataType:'json',
			success:function(data){
				if(data.ret==1){
					userid=data.content.userid;//不可以直接访问页面吗   
					window.location.href="/goods?userid="+userid+"&username="+username;
				}else{
					alert(data.msg);
				}
				
			},
			error:function(){
				alert("登录请求失败");
			}
		});
	});
});