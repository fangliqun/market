$(document).ready(function(){  
	var ajaxurl = "/hello/getAllStudents";
	
	
		    var grid = new pk.Ajaxgrid({  
		        el: $('#DataGrid'),  
		       // url: ajaxurl, //用以获取分页数据的请求  
		        type: 'POST',  
		        idField: 'sid',  
		        autoHeight: false,  
		        pagination: true,  
		        pageSize: 20,  
		        fields: [{  
		            field: '_check'  
		        },{  
		            field: 'sid',
		            text:'学号',
		            weight:40,
		        },{  
		            field: 'name',  
		            text: '姓名',  
		            weight: 100,  
		        },{  
		            field: 'age',  
		            text: '年龄',  
		            weight: 90,  
		        },{  
		            field: 'sex',  
		            text: '性别',  
		            weight: 30,  
		            title: '性别'  
		        },{  
		            field: 'operations',  
		            text: '操作',  
		            weight: 50,  
		            renderer: function(rowData){  
		                return '<a href="#' + rowData.id + '" onclick="alert(\'我要删除的记录是：' + rowData.name + '\')">删除</a>';  
		            }  
		        }]  
		    });  
		  
		   
		    
		    //加载数据
		    var data1=$("#studentList").val();
	    	var data2=JSON.parse(data1);
	        //grid.loadData(data2);
	    	$.ajax({
	    		url:ajaxurl,
	    		type:'GET',
	    		dataType:'json',
	    		success:function(data){
	    			var result = JSON.stringify(data.content);
	    			result=JSON.parse(result);
	    			grid.loadData(result);
	    		}
	    	});
	    	
	    	
	        //新增学生dialog
	        $('#addStudent').on('click', function(){  
	            var dialog = $('#DemoModuleDialog').dialog({  
	                autoOpen : true,  
	                modal : true,  
	                width : 600,  
	                height : 450,  
	                draggable: true,  
	                resizable: false,  
	                buttons : {  
	                    "ok" : {  
	                        text : '确认',  
	                        'class' : 'button button-minor',  
	                        click : function() {  
	                            alert('确认');  
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
		  
		}); 