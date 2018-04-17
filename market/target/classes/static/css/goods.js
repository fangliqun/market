$(document).ready(function(){  
    $('.pk-nav').pkNav();
    
    var ajaxurl = "/selectGoods";
    var userid;
    var username;
    
    var Request = new Object();
    Request = GetRequest();
    userid=Request['userid'];
    username=Request['username'];
    
    var grid = new pk.Ajaxgrid({  
        el: $('#DataGrid'),  
        url: '', //用以获取分页数据的请求  
        type: 'POST',  
        idField: 'goodsid',  
        autoHeight: false,  
        pagination: true,  
        pageSize: 20,  
        fields: [{  
            field: '_check'  
        },{  
       	 field: 'goodsid',
         text:'商品编号',
         weight:40,
        },{  
        	 field: 'goodsname',
	         text:'商品名',
	         weight:40,
	         renderer: function(rowData){  
	              return '<a href="#" onclick="update('+rowData.goodsid+')">'+rowData.goodsname+'</a>';  
	         }  
        },{  
        	field: 'money',
	         text:'价格',
	         weight:40,
        },{  
        	field: 'inventory',
	         text:'库存',
	         weight:40,
        },{  
            field: 'operations',  
            text: '操作',  
            weight: 50,  
            renderer: function(rowData){  
            	var a=rowData.goodsname;
                return '<a href="#" onclick="buy('+rowData.goodsid+',\''+a+'\',\''+username+'\')">购买</a>';  
            }  
        }]  
    });  
    
    
    
   
    $.ajax({
		url:ajaxurl,
		type:'GET',
		dataType:'json',
		success:function(data){
			if(data.ret==1){
				var result = JSON.stringify(data.content);
				result=JSON.parse(result);
				grid.loadData(result);
			}else{
				alert("请求表单失败");
			}
			
		},
		error:function(){
			alert("请求失败");
		}
	});
   
    
    window.resize = function(){  
        var ct = $('#DataGrid'),  
            w = ct.width(),  
            h = ct.height(),  
            n = [0,1,2,3,4,5,-1,-2,-3,-4,-5];  
  
        var timer = setInterval(function() {  
            ct.width(w + 30*n[Math.floor(Math.random()*10)]);  
            ct.height(h + 30*n[Math.floor(Math.random()*10)]);  
            grid.resize();  
        },500);  
        setTimeout(function() {  
            clearInterval(timer);  
            ct.width(w);  
            ct.height(h);  
            grid.resize();  
        }, 5000);  
    };  
  
    $(".navB").click(function(){
    	window.location.href="/orders?userid="+userid+"&username="+username;
    });
    
    $(".navC").click(function(){
    	window.location.href="/total?userid="+userid+"&username="+username;
    });
    
    $(".add").click(function(){
   	 addShow();
    });
    
//    $("#shangchuan").click(function(){
//    	addGoods();
//       });
//    
//
//    $("#quxiao").click(function(){
//    	$("#goodsform").hide();
//       });
    
    $(".update").click(function(){
    	 var records = grid.getSelected();  
    	  alert('你选择的记录是：' + $.toJSON(records));  
     });
    
    
    $(".delete").click(function(){
//    	obj = document.getElementsByName("undefined");
//        check_val = [];
//        for(k in obj){
//            if(obj[k].checked)
//                check_val.push(obj[k].title);
//        }
//        alert(check_val);
    	var ids = grid.getSelectedIds();  
    	var msg = "您真的确定要删除吗？\n\n请确认！"; 
    	if (confirm(msg)==true){ 
    		$.ajax({
    			url:'/deletetGoods',
    			type: 'POST',
    			data:{"datas":ids.join(",")},
    			dataType:'json',
    			success:function(data){
    				if(data.ret==1){
//    					window.location.href="/goods?userid="+userid+"&username="+username;
    					window.location.reload();
    				}else{
    					alert(data.msg);
    				}
    			},
    			error:function(){
    				alert("请求失败");
    			}
    		});
    	}else{ 
    		 return false; 
    	} 
    });
    
    
    
}); 
function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}
function addGoods(){
	var goodsname=$(".goodsname").val();
	var money=$(".money").val();
	var inventory=$(".inventory").val();
	$.ajax({
		url:'/insertGoods',
		type: 'POST',
		data:{"goodsname":goodsname,"money":money,"inventory":inventory},
		dataType:'json',
		async: true,
		success:function(data){
			if(data.ret==1){
//				window.location.href="/goods?userid="+userid+"&username="+username;
				window.location.reload();
			}else{
				alert(data.msg);
			}
		},
		error:function(){
			alert("请求失败");
		}
	});
}

function buy(goodsid,goodsname,username){                  //怎么获取表单中的多条数据具体数据
	var number=prompt("请输入你想要买的数量","");
	if(number){
		$.ajax({
			url:'/updateGoods',
			type: 'POST',
			data:{"goodsid":goodsid,"number":number,"goodsname":goodsname,"username":username},
			dataType:'json',
			async: true,
			success:function(data){
				if(data.ret==1){
//					window.location.href="/goods?userid="+userid+"&username="+username;
					window.location.reload();
				}else{
					alert(data.msg);
				}
			},
			error:function(){
				alert("请求失败");
			}
		});
	}
}
function update(rowData){
	$('.bianhao').html(rowData);
	 var dialog = $('#update').dialog({  
	        autoOpen : true,  
	        modal : true,  
	        width : 600,  
	        height : 450,  
	        draggable: true,  
	        resizable: false,  
	        buttons : {  
	            "ok" : {  
	                text : '修改',  
	                'class' : 'button button-minor',  
	                click : function() {  
	                	var goodsname=$(".goodsnames").val();
	                	var money=$(".moneys").val();
	                	var inventory=$(".inventorys").val();
	                	$.ajax({
	            			url:'/update',
	            			type: 'POST',
	            			data:{"goodsid":rowData,"inventory":inventory,"money":money,"goodsname":goodsname},
	            			dataType:'json',
	            			async: true,
	            			success:function(data){
	            				if(data.ret==1){
//	            					window.location.href="/goods?userid="+userid+"&username="+username;
	            					window.location.reload();
	            				}else{
	            					alert(data.msg);
	            				}
	            			},
	            			error:function(){
	            				alert("请求失败");
	            			}
	            		});
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
}
function addShow(){
	var dialog = $('#goodsform').dialog({  
        autoOpen : true,  
        modal : true,  
        width : 600,  
        height : 450,  
        draggable: true,  
        resizable: false,  
        buttons : {  
            "ok" : {  
                text : '上传',  
                'class' : 'button button-minor',  
                click : function() {  
                	var goodsname=$(".goodsname").val();
                	var money=$(".money").val();
                	var inventory=$(".inventory").val();
                	$.ajax({
            			url:'/insertGoods',
            			type: 'POST',
            			data:{"inventory":inventory,"money":money,"goodsname":goodsname},
            			dataType:'json',
            			async: true,
            			success:function(data){
            				if(data.ret==1){
//            					window.location.href="/goods?userid="+userid+"&username="+username;
            					window.location.reload();
            				}else{
            					alert(data.msg);
            				}
            			},
            			error:function(){
            				alert("请求失败");
            			}
            		});
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
}

