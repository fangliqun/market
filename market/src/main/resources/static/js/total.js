 $(document).ready(function(){  
	  $('.pk-nav').pkNav();
	 	var xdata='';
	 	var ydata='';
	    $.ajax({
  		url:'/selectAllOrder',
  		type:'GET',
  		dataType:'json',
  		success:function(data){
  			if(data.ret==1){
  				var a = data.content;
  				var xdata=new Array();
  				var ydata=new Array();
  				var x=0;
  				for (var key in a)
  			    {	
  					xdata[x]=key;
//  					xdata=xdata+'\"'+key+'\",';
//  					xdata=xdata+key+',';
  					ydata[x]=a[key];
  					x++;
  			    }
//  				xdata='['+xdata+']';
//  				xdata='['+xdata+']';
//  				ydata='['+ydata+']';
//  				xdata= new Array("桃子1", "桃子2", "桃子3", "桃子4", "桃子5");
//				ydata= new Array(5, 4, 6, 3, 4);

			 	  require.config({
			            paths: {
			                echarts: 'http://echarts.baidu.com/build/dist'
			            }
			        });
			 	  require(
		                [
		                    'echarts',
		                    'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
		                ],
		          function (ec) {
                  // 基于准备好的dom，初始化echarts图表
                  var myChart = ec.init(document.getElementById('main')); 
                  
                  var option = {
             			 tooltip: {
                            show: true
                        },
                        legend: {
                            data:['销量']
                        },
                        xAxis : [
                            {
                                type : 'category',
                                data : xdata
                            }
                        ],
                        yAxis : [
                            {
                                type : 'value'
                            }
                        ],
                        series : [
                            {
                                "name":"销量",
                                "type":"bar",
//                                "data":[5, 20]
                                "data":ydata
                            }
                        ]
                    };
                  
                  
                  // 为echarts对象加载数据 
                  myChart.setOption(option); 
              }
          );
  			}else{
  				alert("请求表单失败");
  			}
  			
  		},
  		error:function(){
  			alert("请求失败");
  		}
  	});		
	 	var userid;
	    var username;
	    
	    var Request = new Object();
	    Request = GetRequest();
	    userid=Request['userid'];
	    username=Request['username'];
	    
	    
	 	$("#b").click(function(){
	    	window.location.href="/orders?userid="+userid+"&username="+username;
	    });
	    
	    $("#a").click(function(){
	    	window.location.href="/goods?userid="+userid+"&username="+username;
	    });
	    
	    
	    
	    
	    
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
		         renderer: function(rowData){  
		              return '<a href="#" onclick="update('+rowData.goodsid+',\''+rowData.money+'\',\''+rowData.goodsname+'\',\''+rowData.inventory+'\')">'+rowData.goodsname+'</a>';  
		         }  
	        },{  
	        	field: 'money',
		         text:'价格',
		         weight:40,
	        },{  
	        	field: 'inventory',
		         text:'库存',
		         weight:40,
	        }]  
	    });  
	  
//	 
	 	
 });
 
 
 function getSer(){
	 
	 var option = {
			 tooltip: {
               show: true
           },
           legend: {
//           	padding: 5, 
//           	itemGap: 10,     
               data:['销量']
           },
           xAxis : [
               {
                   type : 'category',
//                   boundaryGap : false,
                   data : ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋"],
//                   data : xdata
               }
           ],
           yAxis : [
               {
                   type : 'value'
               }
           ],
           series : [
               {
                   "name":"销量",
                   "type":"bar",
                   barGap:'5%',
//                   "data":[5, 20]
                   "data":[5, 20, 40, 10, 10, 20]
               }
           ]
       };
	 return option;
 }
 
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
 function initTime(){
		$('#searchTime').on('click', function(){   
	        var opt = {   
	            showtime: true,   
	            dateFmt:'yyyy-MM-dd',
	            maxDate:'%y-%M-%d',
	            readOnly:true
	        };   
	        WdatePicker.call(this, opt);   
	    });
		
	}
//	var xdata;
// 	var ydata;
// 	$.ajax({
//		url:'/selectAllOrderGoodsname',
//		type:'GET',
//		dataType:'json',
//		success:function(data){
//			if(data.ret==1){
//				var result = data.content;
//				xdata='';
//				ydata='';
//				for(var i=0;i<result.length;i++){
//					if(i==result.length-1){
//						$.ajax({
//				    		url:'/selectAllOrder',
//				    		type:'GET',
//				    		dataType:'json',
//				    		success:function(data){
//				    			if(data.ret==1){
//				    				var a = data.content;
//				    				
//				    				ydata=ydata+a.result[i];
//				    			}else{
//				    				alert("请求表单失败");
//				    			}
//				    			
//				    		},
//				    		error:function(){
//				    			alert("请求失败");
//				    		}
//				    	});		
//						xdata=xdata+'\"'+result[i]+'\"';
//					}else{
////						var b=result[i];
////						alert(b);
////						$.ajax({
////				    		url:'/selectAllOrder',
////				    		type:'GET',
////				    		dataType:'json',
////				    		success:function(data){
////				    			if(data.ret==1){
////				    				var a = data.content;
////////				    				$.JOSNparse
//////				    				alert(a.桃子);
//////				    				alert(JSON.parse(a));
//////				    				alert(a);
//////				    				console.log(a);
////				    				ydata=ydata+a.b+",";
////				    			}else{
////				    				alert("请求表单失败");
////				    			}
////				    			
////				    		},
////				    		error:function(){
////				    			alert("请求失败");
////				    		}
////				    	});		
//						xdata=xdata+'\"'+result[i]+'\",';
//					}
//				}
//				xdata='['+xdata+']';
//				alert(xdata);
//				ydata='['+ydata+']';
//			}else{
//				alert("请求表单失败");
//			}
//			
//		},
//		error:function(){
//			alert("请求失败");
//		}
//	});