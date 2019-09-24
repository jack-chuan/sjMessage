$(function(){
    var isRead = Utils.getUrlParam("isRead");
    if(null == isRead || "" == isRead){
        isRead = $('input[name="IF_READ"]:checked ').val();
    } else {
        layui.use('form',function () {
            var form = layui.form;
            $(":radio").removeAttr('checked');
            $("input[type='radio']").each(function () {
                $("input[name=IF_READ][value='2']").prop("checked", isRead == '2' ? true : false);
                $("input[name=IF_READ][value='0']").prop("checked", isRead == '0' ? true : false);
                $("input[name=IF_READ][value='1']").prop("checked", isRead == '1' ? true : false);
            });
            form.render();
        });
    }
    var sdate = "";
    var edate = "";

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

    //日期
    layui.use(['laydate'], function() {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#sdate'
            ,theme: '#1e9fff'
            ,done: function (value, date) {
                if($.trim(edate) != "" && $.trim(value) != "" && value > edate){
                    layer.alert("起始时间不能大于结束时间！", {"skin": "layui-layer-lan","title": '系统提示'});
                    $("#sdate").val("");
                } else {
                    sdate = value;
                }
            }
            ,trigger: 'click'
        });
        laydate.render({
            elem: '#edate'
            ,theme: '#1e9fff'
            ,done: function (value, date) {
                if($.trim(sdate) != "" && $.trim(value) != "" && value < sdate){
                    layer.alert("结束时间不能小于开始时间！", {"skin": "layui-layer-lan","title": '系统提示'});
                    $("#edate").val("");
                } else {
                    edate = value;
                }
            }
            ,trigger: 'click'
        });
    });

    layui.use(['table','form'], function () {
        var table = layui.table,form = layui.form;
        table.render({
            elem: '#RMsgTable'
            , toolbar: '#toolbar'
            ,defaultToolbar: ['filter', 'print']
            , url: getRootPath()+'/message/queryReceiveMessage'
            , title: '用户表'
            ,page: {theme: '#1E9FFF'} //开启分页
            , method: 'POST'
            , xhrFields: {withCredentials: true}
            , limit: 15 //每页默认显示的数量
            ,limits:[10,15,20,25,30]
            // , height: $("body").height() - $(".toolbar").outerHeight(true) - 40
            , where: {
                mstate: 'A',
                rstate: 'A',
                if_read: isRead
            }
            , response: {
                statusCode: 200
            }
            , cols: [[
                {type: 'checkbox'}
                ,{type:'numbers', title: '序号'}
                ,{title: '标题', width:'30%', align: 'left',templet: function(d){
                    var msg_title_value = d.msg_title;
                    if(msg_title_value.indexOf("span") != -1){
                        var start = msg_title_value.indexOf(">");
                        var end = msg_title_value.lastIndexOf("<");
                        msg_title_value = msg_title_value.substring(start+1,end);
                    }
                    if(d.if_read == 0){
                        if(d.file_count > 0){
                            return '<i class="iconfont unRead">&#xe601;</i><i class="iconfont">&#xe63e;</i><a href="javascript:;" data-id="'+ d.msg_id +'" lay-event="edit" class="layui-table-link" title="'+msg_title_value+'">'+ d.msg_title +'</a>';
                        } else {
                            return '<i class="iconfont unRead">&#xe601;</i><a href="javascript:;" data-id="'+ d.msg_id +'" lay-event="edit" class="layui-table-link" title="'+msg_title_value+'">'+ d.msg_title +'</a>';
                        }
                    } else {
                        if(d.file_count > 0){
                            return '<i class="iconfont">&#xe63e;</i><a href="javascript:;" data-id="'+ d.msg_id +'" lay-event="edit" class="layui-table-link" title="'+msg_title_value+'">'+ d.msg_title +'</a>';
                        } else {
                            return '<a href="javascript:;" data-id="'+ d.msg_id +'" lay-event="edit" class="layui-table-link" title="'+msg_title_value+'">'+ d.msg_title +'</a>';
                        }
                    }
                }}
                , {field: 'orgname', title: '机构', width: '15%', align: 'center', templet: function (d) {
                    return '<span title="'+d.orgname+'">'+d.orgname+'</span>';
                }}
                , {field: 'deptname', title: '部门', width: '10%', align: 'center', templet: function (d) {
                    return '<span title="'+d.deptname+'">'+d.deptname+'</span>';
                }}
                , {title: '发送人', align: 'center', templet: function (d) {
                    var sender_value = d.sender;
                    if(sender_value.indexOf("span") != -1){
                        var start = sender_value.indexOf(">");
                        var end = sender_value.lastIndexOf("<");
                        sender_value = sender_value.substring(start+1,end);
                    }
                    return '<span title="'+sender_value+'">'+d.sender+'</span>';
                }}
                , {field: 'create_date', title: '时间', align: 'center', sort: true, templet: function (d) {
                    return '<span title="'+d.create_date+'">'+d.create_date+'</span>';
                }}
                , {field: 'msg_type', title: '类型', align: 'center', sort: true, templet: function (d) {
                    return '<span title="'+d.msg_type+'">'+d.msg_type+'</span>';
                }}
            ]]
        });
        form.render();
        ifRead(isRead);//判断是否显示“标记为已读”按钮
        table.on('tool(RMsgTable)', function (obj) {
            if(obj.event === 'edit'){
                var id = obj.data.id;
                if(obj.data.if_read == '0'){
                    var read = '1';
                    $.ajax({
                        type: "POST",
                        url: getRootPath()+"/receiveMsg/signRead/"+ id +"/" +read,
                        dataType: "json",
                        contentType: "application/json",
                        success: function(iJson) {
                            if (iJson.code === '200'){
                                table.reload('RMsgTable',{
                                    page: {
                                        curr: 1 //重新从第 1 页开始
                                    }
                                });
                                window.location.href = getRootPath()+"/pageJump/msgDetail?msgId="+ encodeURIComponent(obj.data.msg_id)+"&downloadServerIp="+ encodeURIComponent(downloadServerIp);
                            }else{
                                layer.alert(iJson.msg, {"skin": "layui-layer-lan","title": '系统提示'});
                            }
                        }
                    });
                } else {
                    window.location.href = getRootPath()+"/pageJump/msgDetail?msgId="+ encodeURIComponent(obj.data.msg_id)+"&downloadServerIp="+ encodeURIComponent(downloadServerIp);
                }
            }
        });
        table.on('toolbar(RMsgTable)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            var data = checkStatus.data;
            switch(obj.event){
                case 'read':
                    if(data != ""){
                        layer.confirm('确定标记为已读？', {
                            skin: "layui-layer-lan",
                            title: '系统提示'
                        }, function (index) {
                            // read(data);
                            var id = [];
                            var read = 1;
                            for (var i = 0; i < data.length;i++){
                                id.push(data[i].id);
                            }
                            $.ajax({
                                type: "POST",
                                url: getRootPath()+"/receiveMsg/signRead/"+ id +"/" +read,
                                dataType: "json",
                                contentType: "application/json",
                                success: function(iJson) {
                                    if (iJson.code === '200'){
                                        table.reload('RMsgTable',{
                                            page: {
                                                curr: 1 //重新从第 1 页开始
                                            }
                                        });
                                    }
                                    layer.alert(iJson.msg, {"skin": "layui-layer-lan","title": '系统提示'});
                                }
                            });
                            layer.close(index)
                        })
                    }else {
                        layer.alert('请至少选择一条消息！', {"skin": "layui-layer-lan","title": '系统提示'});
                    }
                    break;
                case 'deleteMsg':
                    if (data != "") {
                        layer.confirm('确定删除？', {
                            skin: "layui-layer-lan",
                            title: '系统提示'
                        }, function (index) {
                            var msgId = [];
                            for (var i = 0; i < data.length;i++){
                                msgId.push(data[i].id)
                            }
                            $.ajax({
                                type: "POST",
                                url: getRootPath()+"/receiveMsg/delInBoxMsg/"+ msgId,
                                dataType: "json",
                                contentType: "application/json",
                                success: function(iJson) {
                                    if (iJson.code === '200'){
                                        layer.alert(iJson.msg, {"skin": "layui-layer-lan","title": '系统提示'});
                                        table.reload('RMsgTable',{
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
                    } else {
                        layer.alert('请至少选择一条消息！', {"skin": "layui-layer-lan", "title": '系统提示'});
                    }
                    break;
                case 'forwardMsg':
                    if (data != "" && data.length === 1) {
                        var msgId = data[0].msg_id
                        window.location.href = getRootPath()+"/pageJump/msgForward?msgId="+ encodeURIComponent(msgId)+"&downloadServerIp="+ encodeURIComponent(downloadServerIp);
                    } else {
                        layer.alert('请选择一条转发的消息！', {"skin": "layui-layer-lan", "title": '系统提示'});
                    }
                    break;
                case 'replyMsg':
                    if (data != "" && data.length === 1) {
                        var msgId = data[0].msg_id
                        window.location.href = getRootPath()+"/pageJump/msgReply?msgId="+ encodeURIComponent(msgId)+"&downloadServerIp="+ encodeURIComponent(downloadServerIp);
                    } else {
                        layer.alert('请选择一条回复的消息！', {"skin": "layui-layer-lan", "title": '系统提示'});
                    }
                    break;
            };
        });
        //搜索
        form.on('submit(filterCondition)', function(data){
            var msg_title = $.trim(data.field.msg_title);
            var sender = $.trim(data.field.sender);
            var sdateTemp1 = $.trim(data.field.sdate);
            var edateTemp1 = $.trim(data.field.edate);
            table.reload("RMsgTable", {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where:{
                    msg_title:msg_title,
                    sender:sender,
                    sdate:sdateTemp1,
                    edate:edateTemp1,
                    if_read:isRead
                }
            });
            ifRead(isRead);
        });

        form.on('radio(IF_READ)', function(data){
            isRead = data.value;
            var msg_title = $("#msg_title").val();
            var sender = $("#sender").val();
            var sdateTemp2 = $("#sdate").val();
            var edateTemp2 = $("#edate").val();
            table.reload('RMsgTable',{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where:{
                    msg_title:msg_title,
                    sender:sender,
                    sdate:sdateTemp2,
                    edate:edateTemp2,
                    if_read:isRead
                }
            });
            ifRead(isRead);
        });

        $("#reset-btn").click(function(){
            table.reload('RMsgTable',{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where:{
                    msg_title:"",
                    sender:"",
                    sdate:"",
                    edate:"",
                    if_read:2//全部
                }
            });
            ifRead(isRead);
        });
    });
});

//判断已读未读
function ifRead(ifRead){
    if (ifRead == 1 || ifRead == 2) {
        $("#read").hide();
    }
    if (ifRead == 0) {
        $("#read").show()
    }
}



