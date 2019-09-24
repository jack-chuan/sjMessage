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
	    elem: '#ljTab'
	    ,url: getRootPath()+'/message/queryTrashMessage'
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
        , where: {
          mstate: 'A',
          rstate: 'R'
        }
        ,toolbar: '#barDemo'
        ,defaultToolbar: ['filter', 'print']
        ,page: {theme: '#1E9FFF'} //开启分页
	    ,cols: [[
	      {type:'checkbox'}	
	      ,{type:'numbers', title: '序号'}
          ,{title: '标题', width:'26%', align: 'left', templet: function(d){
              var msg_title_value = d.msg_title;
              if(msg_title_value.indexOf("span") != -1){
                  var start = msg_title_value.indexOf(">");
                  var end = msg_title_value.lastIndexOf("<");
                  msg_title_value = msg_title_value.substring(start+1,end);
              }
              if(d.file_count > 0){
                  return '<i class="iconfont">&#xe63e;</i><a href="javascript:;" data-id="'+ d.msg_id +'" lay-event="edit" class="layui-table-link" title="'+msg_title_value+'">'+ d.msg_title +'</a>';
              } else {
                  return '<a href="javascript:;" data-id="'+ d.msg_id +'" lay-event="edit" class="layui-table-link" title="'+msg_title_value+'">'+ d.msg_title +'</a>';
              }
	      }}
          ,{field:'orgname',  width:'15%', title: '机构', align: 'center', templet: function (d) {
                return '<span title="'+d.orgname+'">'+d.orgname+'</span>';
          }}
          ,{field:'deptname',  width:'10%', title: '部门', align: 'center', templet: function (d) {
                return '<span title="'+d.deptname+'">'+d.deptname+'</span>';
           }}
          ,{field:'sender', title: '发送人', align: 'center', templet: function (d) {
                return '<span title="'+d.sender+'">'+d.sender+'</span>';
          }}
          ,{field:'create_date', title: '时间', align: 'center', sort: true, templet: function (d) {
                return '<span title="'+d.create_date+'">'+d.create_date+'</span>';
          }}
          ,{field:'source', title: '来源', align: 'center', templet: function (d) {
              var source_value = d.source;
              if("S" == d.source){
                  source_value = "发件箱";
              } else if("T" == d.source){
                  source_value = "草稿箱";
              } else if("R" == d.source){
                  source_value = "收件箱";
              }
              return '<span title="'+source_value+'">'+source_value+'</span>';
          }}
          ,{field:'msg_type', title: '类型', align: 'center', sort: true, templet: function (d) {
                return '<span title="'+d.msg_type+'">'+d.msg_type+'</span>';
          }}
          ]]
	  });
	  form.render();
	  table.on('tool(ljTabF)', function (obj) {
		  if(obj.event === 'edit'){
	    	  window.location.href = getRootPath()+"/pageJump/toDustbinDetailPage?msgId="+ encodeURIComponent(obj.data.msg_id)+"&downloadServerIp="+ encodeURIComponent(downloadServerIp);
	      }
	  });
	  table.on('toolbar(ljTabF)', function (obj) {
	      if (obj.event === 'del') {
	    	  var data = table.checkStatus('ljTab').data;
	    	  if(data != ""){
	    		  layer.confirm('确定删除？', {
		    		  skin: "layui-layer-lan",
		    		  title: '系统提示'
		    	  }, function(){
		    		  var dataIds = [];
		    		  $.each(data,function(i,n){
		    			  dataIds.push(n.id);
		    		  });
		    		  var params = {};
		    		  params.id = dataIds.join(",");
		    		  $.ajax({  
		    		  	  type : "POST",
		    		  	  url : getRootPath()+'/trashCan/deleteTrashCan',
		    		  	  dataType : "json",
		    		  	  async : false,
		    		  	  data : params,
		    		  	  success:function(iJson){
		    		  		  if (iJson.code === '200'){
		    		  			layer.alert(iJson.msg, {"skin": "layui-layer-lan","title": '系统提示'});
                                  table.reload('ljTab',{
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
	      } else if(obj.event === 'rec') {
              var data = table.checkStatus('ljTab').data;
              if(data != "" && data.length === 1){
                  layer.confirm('确定恢复？', {
                      skin: "layui-layer-lan",
                      title: '系统提示'
                  }, function(){
                      var id = data[0].id;
                      var type = data[0].type1;
                      var params = {};
                      params.id = id;
                      params.type = type;
                      $.ajax({
                          type : "POST",
                          url : getRootPath()+'/trashCan/removeTrashCan',
                          dataType : "json",
                          async : false,
                          data : params,
                          success:function(iJson){
                              if (iJson.code === '200'){
                                  layer.alert(iJson.msg, {"skin": "layui-layer-lan","title": '系统提示'});
                                  table.reload('ljTab',{
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
                  layer.alert('请选择一条恢复的消息！', {"skin": "layui-layer-lan","title": '系统提示'});
              }
          }
	  });
	  //搜索
	  form.on('submit(filterCondition)', function(data){
          var msgTitle = $.trim(data.field.msgTitle);
          var sDate = $.trim(data.field.sDate);
          var eDate = $.trim(data.field.eDate);
		  table.reload("ljTab", {
              page: {
                  curr: 1 //重新从第 1 页开始
              },
			  where:{
                  msg_title:msgTitle,
                  sdate:sDate,
                  edate:eDate
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