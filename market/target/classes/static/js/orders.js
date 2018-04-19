$(document).ready(function(){  
    $('.pk-nav').pkNav();
    
    var userid;
    var username;
    
    var grid = new pk.Ajaxgrid({  
        el: $('#DataGrid'),  
        url: '/selectOrders', //用以获取分页数据的请求  
        type: 'Get',  
        idField: 'orderid',  
        autoHeight: false,  
        pagination: true,  
        pageSize: 20,  
        fields: [{  
            field: '_check'  
        },{  
	       	 field: 'orderid',
	         text:	'编号编号',
	         weight:40,
        },{  
        	 field: 'goodsname',
	         text:'商品名',
	         weight:40,
        },{  
	       	 field: 'number',
	         text:'数量',
	         weight:40,
        },{  
        	field: 'username',
	         text:'订单者',
	         weight:40,
        },{  
        	field: 'address',
	         text:'地址',
	         weight:40,
        },{  
	      	field: 'callnumber',
	       text:'电话',
	       weight:40,
        },{  
        	field: 'time',
	         text:'订单时间',
	         weight:40,
        }]  
    });  
    
    $(".delete").click(function(){
    	var ids = grid.getSelectedIds();  
    	var msg = "您真的确定要删除吗？\n\n请确认！"; 
    	if (confirm(msg)==true){
    		$.ajax({
    			url:'/deleteOrders',
    			type: 'POST',
    			data:{"datas":ids.join(",")},
    			dataType:'json',
    			success:function(data){
    				if(data.ret==1){
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
//    
//    $.ajax({
//		url:ajaxurl,
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
  
    window.getSelectedIds = function(){  
        var ids = grid.getSelectedIds();  
        alert('你选择的记录的id是：' + ids.join(','));  
    };  
  
    window.getSelected = function(){  
        var records = grid.getSelected();  
        alert('你选择的记录是：' + $.toJSON(records));  
    };  
  
    window.load = function(){  
        grid.load({  
            name: 'admin'  
        });  
    };  
  
    window.reload = function(){  
        grid.reload();  
    };  
  
    window.getData = function(){  
        var data = grid.getData();  
        alert($.toJSON(data));  
    };  
  
    window.loadData = function(){  
    }; 
    
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
    $(".navA").click(function(){
		window.location.href="/goods?userid="+userid+"&username="+username;
    });
    $(".navC").click(function(){
    	window.location.href="/total?userid="+userid+"&username="+username;
    });
    
    
    var Request = new Object();
    Request = GetRequest();
    userid=Request['userid'];
    username=Request['username'];
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
function deleteOrders(){
	
}
