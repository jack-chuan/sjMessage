$(function(){
    //渲染搜索框
    // searchRender();
    //附件下载服务器地址
    var downloadServerIp = "";
    //获取附件下载服务器地址
    /*$.ajax({
        type : "POST",
        url : '/msgFile/downloadIpAddress',
        dataType : "json",
        async : false,
        success : function (ipJson) {
            if(ipJson.code === '200'){
                downloadServerIp = ipJson.data;
            } else {
                layer.alert(ipJson.msg, {"skin": "layui-layer-lan","title": '系统提示'});
            }
        }
    });*/
    $.ajax({
        type : "POST",
        url : getRootPath()+'/config/getConfig',
        dataType : "json",
        async : false,
        success : function (ipJson) {
            if(ipJson.code === '200'){
                downloadServerIp = ipJson.data.download_server;
            } else {
                layer.alert(ipJson.msg, {"skin": "layui-layer-lan","title": '系统提示'});
            }
        }
    });
    var sDate = "";//开始时间
    var eDate = "";//结束时间

    //日期
    layui.use(['laydate'], function() {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#sDate'
            ,theme: '#1e9fff'
            ,done: function (value, date) {
                if($.trim(eDate) != "" && $.trim(value) != "" && value > eDate){
                    layer.alert("起始时间不能大于结束时间！", {"skin": "layui-layer-lan","title": '系统提示'});
                    $("#sDate").val("");
                } else {
                    sDate = value;
                }
            }
            ,trigger: 'click'
        });
        laydate.render({
            elem: '#eDate'
            ,theme: '#1e9fff'
            ,done: function (value, date) {
                if($.trim(sDate) != "" && $.trim(value) != "" && value < sDate){
                    layer.alert("结束时间不能小于开始时间！", {"skin": "layui-layer-lan","title": '系统提示'});
                    $("#eDate").val("");
                } else {
                    eDate = value;
                }
            }
            ,trigger: 'click'
        });
    });
	layui.use(['table','form'], function(){
	  var table = layui.table,form = layui.form;
	  table.render({
	    elem: '#cgTab'
	    ,url: getRootPath()+'/draftBox/queryDraftBoxList'
        ,method: 'post'
        ,request: {
            pageName: 'pageNum'
            ,limitName: 'pageSize'
        }
        ,response: {
              statusCode: 200
        }
	    // ,height: $("body").height() - $(".toolbar").outerHeight(true) - 40
	    ,limit: 15
        ,limits:[10,15,20,25,30]
        ,toolbar: '#barDemo'
        ,defaultToolbar: ['filter', 'print']
        ,page: {theme: '#1E9FFF'} //开启分页
	    ,cols: [[
	      {type:'checkbox'}	
	      ,{type:'numbers', title: '序号'}
	      ,{field:'MSG_TITLE', width:'35%', title: '标题',align: 'left',templet: function(d){
	          if(d.FILE_COUNT > 0){
                  return '<i class="iconfont">&#xe63e;</i><a href="javascript:;" data-id="'+ d.MSG_ID +'" lay-event="edit" class="layui-table-link" title="'+d.MSG_TITLE+'">'+ d.MSG_TITLE +'</a>';
              } else {
                  return '<a href="javascript:;" data-id="'+ d.MSG_ID +'" lay-event="edit" class="layui-table-link" title="'+d.MSG_TITLE+'">'+ d.MSG_TITLE +'</a>';
              }
	      }}
          ,{field:'CREATE_DATE', title: '时间',align: 'center',sort: true, templet: function (d) {
                return '<span title="'+d.CREATE_DATE+'">'+d.CREATE_DATE+'</span>';
          }}
          ,{field:'MSG_TYPE', title: '类型',align: 'center', sort: true, templet: function (d) {
                return '<span title="'+d.MSG_TYPE+'">'+d.MSG_TYPE+'</span>';
          }}
          ,{field:'RECEIVE_NAME', width:'35%', title: '收件人',align: 'left', templet: function (d) {
                return '<span title="'+d.RECEIVE_NAME+'">'+d.RECEIVE_NAME+'</span>';
          }}
	    ]]
	  });
	  form.render();
	  table.on('tool(cgTabF)', function (obj) {
		  if(obj.event === 'edit'){
              window.location.href = getRootPath()+"/pageJump/toDraftboxForwardPage?msgId="+ encodeURIComponent(obj.data.MSG_ID)+"&downloadServerIp="+ encodeURIComponent(downloadServerIp);
	      }
	  });
	  table.on('toolbar(cgTabF)', function (obj) {
	      if (obj.event === 'del') {
	    	  var data = table.checkStatus('cgTab').data;
	    	  if(data != ""){
	    		  layer.confirm('确定删除？', {
		    		  skin: "layui-layer-lan",
		    		  title: '系统提示'
		    	  }, function(){
		    		  var dataIds = [];
		    		  $.each(data,function(i,n){
		    			  dataIds.push(n.ID);
		    		  });
		    		  var params = {};
		    		  params.id = dataIds.join(",");
		    		  $.ajax({  
		    		  	  type : "POST",
		    		  	  url : getRootPath()+'/draftBox/deleteDraftMsg',
		    		  	  dataType : "json",
		    		  	  async : false,
		    		  	  data : params,
		    		  	  success:function(iJson){
		    		  		  if (iJson.code === '200'){
		    		  			layer.alert(iJson.msg, {"skin": "layui-layer-lan","title": '系统提示'});
                                  table.reload('cgTab',{
                                      page: {
                                          curr: 1 //重新从第 1 页开始
                                      }
                                  });
		    		  		  }else{
		    		  				layer.alert(iJson.msg, {"skin": "layui-layer-lan","title": '系统提示'});
		    		  		  }
		    		  	  } 
		    		  });
		    	  }, function(index){
		    		  layer.close(index); 
		    	  });
	    	  }else{
	    		  layer.alert('请至少选择一条删除的消息！', {"skin": "layui-layer-lan","title": '系统提示'});
	    	  }
	      } /*else if(obj.event === 'for') {
              var data = table.checkStatus('cgTab').data;
              if(data != "" && data.length === 1){
                  var msgId = data[0].MSG_ID;
                  window.location.href = "/pageJump/toDraftboxForwardPage?msgId="+ encodeURIComponent(msgId)+"&downloadServerIp="+ encodeURIComponent(downloadServerIp);
              }else{
                  layer.alert('请选择一条转发的消息！', {"skin": "layui-layer-lan","title": '系统提示'});
              }
          }*/
	  });
	  //搜索
       form.on('submit(filterCondition)', function(data){
           var msgTitle = $.trim(data.field.msgTitle);
           var sDate = $.trim(data.field.sDate);
           var eDate = $.trim(data.field.eDate);
           table.reload("cgTab", {
               page: {
                   curr: 1 //重新从第 1 页开始
               },
               where:{
                   msgTitle:msgTitle,
                   sDate:sDate,
                   eDate:eDate
               }
           });
       });
	});
    //渲染搜索框
    function searchRender() {
        var aInp = document.getElementsByTagName('input');
        var sArray = [];
        for (var i = 0; i < aInp.length; i++) {
            aInp[i].index = i;
            sArray.push(aInp[i].value);
            aInp[i].onfocus = function() {
                if (sArray[this.index] == aInp[this.index].value) {
                    aInp[this.index].value = '';
                }
                aInp[this.index].style.color = '#000000';
            };
            aInp[i].onblur = function() {
                if (aInp[this.index].value == '') {
                    aInp[this.index].value = sArray[this.index];
                    aInp[this.index].style.color = '#757575';
                }
            };
        }
    }
});