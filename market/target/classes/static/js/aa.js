$(document).ready(function(){
	    var grid = new pk.Ajaxgrid({  
	        el: $('#DataGrid'),  
	        //url: '', //用以获取分页数据的请求  
	        type: 'POST',  
	        idField: 'title',  
	        autoHeight: false,  
	        pagination: true,  
	        pageSize: 20,  
	        fields: [{  
	            field: '_check'  
	        },{  
	            field: 'title',  
	            text: '标题',  
	            weight: 100  
	        },{  
	            field: 'content',  
	            text: '内容',  
	            weight: 100  
	        }]  
	    });  
	 var ajaxurl = "/showData";
    $.ajax({
    	url:ajaxurl,
    	type:'GET',
		dataType:'json',
		success:function(data){
			var result=JSON.stringify(data.content);
			alert(data);
			alert(result);
			result=JSON.parse(result);
			grid.loadData(result);
		}
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