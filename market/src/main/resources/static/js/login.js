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
					if(userid==1){
						window.location.href="/goods?userid="+userid+"&username="+username;
					}else{
						window.location.href="/goodsUser?userid="+userid+"&username="+username;
					}
					
				}else{
					alert(data.msg);
				}
				
			},
			error:function(){
				alert("登录请求失败");
			}
		});
	});
	$(".register").click(function(){
		var dialog = $('#register').dialog({  
	        autoOpen : true,  
	        modal : true,  
	        width : 600,  
	        height : 450,  
	        draggable: true,  
	        resizable: false,  
	        buttons : {  
	            "ok" : {  
	                text : '注册',  
	               'class' : 'button button-minor',  
	                click : function() {
	                	var username=$('#usernameA').val();
	                	var address=$('#address').val();
	                	var callnumber=$('#callnumber').val();
	                	var password=$('#passwordA').val();
	                	var passwordC=$('#passwordCA').val();
	                	if(password==passwordC){
	                		$.ajax({
		    	    			url:'/registerUser',
		    	    			type: 'POST',
		    	    			data:{"username":username,"password":password,"address":address,"callnumber":callnumber},
		    	    			dataType:'json',
		    	    			async: true,
		    	    			success:function(data){
		    	    				if(data.ret==1){
		    	    					alert("注册成功");
		    	    					window.location.reload();
		    	    				}else{
		    	    					alert(data.msg);
		    	    				}
		    	    			},
		    	    			error:function(){
		    	    				alert("注册失败");
		    	    			}
		    	    		});
	                	}else{
	                		alert("密码不一致");
	                	}
	                }  
	            },  
	            "cancel" : {  
	                text : '取消',  
	                'class' : 'button button-cancel',  
	                click : function() {  
	                    dialog.dialog("close");  
	                }  
	            }  
	        }  
	   });  
	});
});