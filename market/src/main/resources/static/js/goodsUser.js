$(document).ready(function(){  
//	$(window).resize(function() {
//		 init();
//	});
	var aid=0;
    $('.pk-nav').pkNav();
    
    var userid;
    var username;
    
    var Request = new Object();
    Request = GetRequest();
    userid=Request['userid'];
    username=Request['username'];
    
    var grid = new pk.Ajaxgrid({  
        el: $('#DataGrid'),  
        url: '/selectGoods', //用以获取分页数据的请求  
        type: 'GET',  
        idField: 'goodsid',  
        autoHeight: false,  
        pagination: true,  
        pageSize: 20,  
        fields: [{  
       	 field: 'goodsid',
         text:'商品编号',
         weight:40,
        },{  
        	 field: 'goodsname',
	         text:'商品名',
	         weight:40,
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
                return '<a href="#" onclick="buy('+rowData.goodsid+',\''+a+'\',\''+username+'\',\''+rowData.money+'\')">购买</a>';  
            }  
        }]  
    });  
    
    $('.limit-wrapper .name').html(username);
    $('.logout').click(function(){
    	 $.ajax({
    			url:'/logout',
    			type:'GET',
    			dataType:'json',
    			success:function(data){
    				if(data.ret==1){
    					window.location.href="/login";
    				}else{
    					alert("请求表单失败");
    				}
    				
    			},
    			error:function(){
    				alert("请求失败");
    			}
    		});
    });
   
//    $.ajax({
//		url:'/selectGoods',
//		type:'GET',
//		dataType:'json',
//		success:function(data){
//			if(data.ret==1){
//				var result = JSON.stringify(data.content);
//				result=JSON.parse(result);
//				grid.loadData(result);
//			}else{
//				alert("请求表单失败");
//			}
//			
//		},
//		error:function(){
//			alert("请求失败");
//		}
//	});
    $(".goodsnamexxxx").change(function(){
    	var goodsname=$(".goodsnamexxxx").val();
    	$.ajax({
    		url:'/selectGoodsName',
    		type: 'GET',
    		data:{"goodsname":goodsname},
    		dataType:'json',
    		async: true,
    		success:function(data){
    			if(data.ret==1){
    				alert(data.msg);
    			}
    		},
    		error:function(){
    			alert("请求失败");
    		}
    	});
    });
//   
    
    var area = document.getElementById('notice_list_content');
//    area.innerHTML += area.innerHTML;
    area.scrollTop = 0;
    var timer;
    function startMove(){
        timer = setInterval(scrollUp,500);
        area.scrollTop++;        
    }
    function scrollUp()
    {
    	$.ajax({
    		url:'/readmessagefile',
    		type:'GET',
    		dataType:'json',
    		success:function(data){
    			if(data.ret==1){
    				var result = data.content;
    				var html='';
    				for(var i=0;i<result.length;i++){
    					html=html+'<li><a href="#">'+result[result.length-1-i]+'</a></li>';
    				}
    				$('.l').append(html);
    			}else{
    				var html='';
    				html=html+"没有消息";
    				$('.l').html(html);
    			}
    			
    		},
    		error:function(){
    			alert("请求失败");
    		}
    	});
        if(area.scrollTop%33 === 0)
        {
            clearInterval(timer);
            setTimeout(startMove,1000);
        }
        else
        {
            area.scrollTop++;
            if(area.scrollTop >= area.scrollHeight/2)
            {
                area.scrollTop = 0;
            }
        }
    }
    setTimeout(startMove,1000);
    
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
    	window.location.href="/ordersUser?userid="+userid+"&username="+username;
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
    $.ajax({
		url:'/selectAllGoods',
		type: 'GET',
		dataType:'json',
		async: true,
		success:function(data){
			if(data.ret==1){
				meau=data.content;
				var html = '';
				for(i=0;i<meau.length;i++){
					html = html + '<option goodsid=' + meau[i].goodsid + ' value=' + meau[i].money + '>' + meau[i].goodsname + '</option>';
					
//					document.getElementById("c").options[i]=new Option(meau[i].goodsname,meau[i].money);
				}
				$("#c").append(html);
//				for(i=0;i<meau.length;i++){
//					document.getElementById("c").options[i].attr("goodsid",meau[i].goodsid);
//				}
			}else{
				alert(data.msg);
			}
		},
		error:function(){
			alert("请求失败");
		}
	});
    
  /*  $(".sss").click(function(){
    	var totalmoney=$('.goumai').html();
    	var number=$('.goumai').html();
//    });*/
    findmoney();
    findtotal();
    $(".jia").click(function(){
    	var aa=$(".a").html();
    	aid=aid+1;
    	var box=$("<div class='a' id='"+aid+"'>"+aa+"</div>");
    	$('.zz').append(box);
    	findmoney();
    	findtotal();
//    	$.ajax({
//    		url:'/selectAllGoods',
//    		type: 'GET',
//    		dataType:'json',
//    		async: true,
//    		success:function(data){
//    			if(data.ret==1){
//    				meau=data.content;
//    				for(i=0;i<meau.length;i++){
//    					document.getElementById("c").options[i]=new Option(meau[i].goodsname,meau[i].money);
//    				}
//    			}else{
//    				alert(data.msg);
//    			}
//    		},
//    		error:function(){
//    			alert("请求失败");
//    		}
//    	});
    });
//    $(".total").click(function(){
//    	$('.x').html($('.goumai').html()*$('.ss').val());
//    });
   
    $(".buymany").click(function(){
//    	 $('.x').html($('.goumai').html()*$('.goumai').html());
    	var dialog = $('#buymany').dialog({  
	        autoOpen : true,  
	        modal : true,  
	        width : 600,  
	        height : 450,  
	        draggable: true,  
	        resizable: false,  
	        buttons : {  
	            "ok" : {  
	                text : '购买',  
	                'class' : 'button button-minor',  
	                click : function() {  
	                	$(".a").each(function(){
	                		var goodsname=$(this).find('.b option:selected').text()
	                		var number=$(this).find('.ss').val();
	                		var goodsid=$(this).find('.b option:selected').attr("goodsid");
	                		$.ajax({
		    	    			url:'/updateGoods',
		    	    			type: 'POST',
		    	    			data:{"goodsid":goodsid,"number":number,"goodsname":goodsname,"username":username},
		    	    			dataType:'json',
		    	    			async: true,
		    	    			success:function(data){
		    	    				if(data.ret==1){
//		    	    					window.location.href="/goods?userid="+userid+"&username="+username;
//		    	    					 jAlert('购买成功！', '标题', 'ok');  
		    	    				}else{
		    	    					alert(data.msg);
		    	    				}
		    	    			},
		    	    			error:function(){
		    	    				alert("请求失败");
		    	    			}
		    	    		});
	                	});
	                	alert("购买成功");
    					window.location.reload();
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

function buy(goodsid,goodsname,username,money){                  //怎么获取表单中的多条数据具体数据
	 jPrompt('请输入您的数量：', 1, '标题', function (number) {  
	        if(number){  
	        	$.ajax({
	    			url:'/updateGoods',
	    			type: 'POST',
	    			data:{"goodsid":goodsid,"number":number,"goodsname":goodsname,"username":username},
	    			dataType:'json',
	    			async: true,
	    			success:function(data){
	    				if(data.ret==1){
//	    					window.location.href="/goods?userid="+userid+"&username="+username;
	    					alert("购买成功");
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
	    });  
//	var number=prompt("请输入你想要买的数量","");
//	if(number){
//	}
}
function update(goodsid,money,goodsname,inventory){
	$(".bianhao").html(goodsid);
	 $(".goodsnames").val(goodsname);
	 $(".moneys").val(money);
	 $(".inventorys").val(inventory);
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
	            			data:{"goodsid":goodsid,"inventory":inventory,"money":money,"goodsname":goodsname},
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
	            				window.location.reload();
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
//	$.ajax({
//		url:'/selectGoodsMeau',
//		type: 'GET',
//		dataType:'json',
//		async: true,
//		success:function(data){
//			if(data.ret==1){
//				var meau=data.content;
//				for(i=0;i<meau.length;i++){
//					document.getElementById("DropList").options[i]=new Option(meau[i].text,meau[i].value);
//				}
//			}else{
//				alert(data.msg);
//			}
//		},
//		error:function(){
//			alert("请求失败");
//		}
//	});
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
                	var goodsname=$(".goodsnamexxxx").val();
//                	var goodsname=$('#DropList option:selected').text();//选中的文本
                	var money=$(".goodsform .money").val();
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

function init(){
	alert($(".pk-nav nav-init").outerHeight(true));
	alert($(".crud").outerHeight(true));
	$(".datagrid").height($(document.body).height()
			-$(".pk-nav nav-init").outerHeight(true)
			-$(".crud").outerHeight(true)
			);
}

function findmoney(){
	$(".b").click(function(){
    	var money=$(this).find('option:selected').attr("value");
//    	$('.b option:selected')
    	$(this).find(".howmoney").html(money);
    });
}
function findtotal(){
	 $(".a").change(function(){
	    	var money=$(this).find('option:selected').attr("value");
	    	var number=$(this).find('.ss').val();
	    	$(this).find('.x').html(money*number);
	    	
	    	var a=$(".zz").find(".x");
	    	var sum=0;
	    	for(var i=0;i<a.length;i++){
	    		sum=sum*1 + $(a[i]).text()*1;
	    	}
	    	$(".t").html(sum);
//	    	$(this).find(".howmoney").html(money);
	    });
	
}

