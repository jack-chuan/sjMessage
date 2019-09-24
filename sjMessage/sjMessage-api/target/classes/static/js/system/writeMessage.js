$(function(){
    var fileIdArr = [];
    //附件下载服务器地址
    // var downloadServerIp = "";
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
    var orgId = "";
    var orgName = "";
    var download_server = "";
    var attachment_type = "";
    var attachment_maxsize = "";
    //获取系统参数
    $.ajax({
        type : "POST",
        url : getRootPath()+'/config/getConfig',
        dataType : "json",
        async : false,
        success : function (ipJson) {
            if(ipJson.code === '200'){
                orgId = ipJson.data.orgId;
                orgName = ipJson.data.orgName;
                download_server = ipJson.data.download_server;
                attachment_type = ipJson.data.attachment_type;
                attachment_maxsize = ipJson.data.attachment_maxsize;
                if("" == orgId || null == orgId || "" == orgName || null == orgName
                || "" == download_server || null == download_server || "" == attachment_type
                || null == attachment_type || "" == attachment_maxsize || null == attachment_maxsize){
                    layer.alert("配置文件获取失败！", {"skin": "layui-layer-lan","title": '系统提示'});
                }
            } else {
                layer.alert(ipJson.msg, {"skin": "layui-layer-lan","title": '系统提示'});
            }
        }
    });
    $("#sendScopeName").text(orgName);
    $("#sendScopeId").val(orgId);

    layui.use(['form'],function(){
        var form = layui.form/*,layedit = layui.layedit*/;
        CKEDITOR.replace( 'msgContent');
        form.render();
        form.on('submit(sendSubmit)', function(data){
            if(''==$.trim($("#sendScopeName").text())){
                layer.alert('发送范围不能为空！', {"skin": "layui-layer-lan","title": '系统提示'});
                return false;
            }
            if(''==$.trim($("#receiveName").text()) || '请选择收件人'==$.trim($("#receiveName").text())){
                layer.alert('收件人不能为空！', {"skin": "layui-layer-lan","title": '系统提示'});
                return false;
            }
            if(''==$.trim($("#msgTitle").val())){
                layer.alert('主题不能为空！', {"skin": "layui-layer-lan","title": '系统提示'});
                return false;
            }
            if($.trim($("#msgTitle").val()).length > 50){
                layer.alert('主题不能超过50个字符！', {"skin": "layui-layer-lan","title": '系统提示'});
                return false;
            }
            if(!submitCheckImage()){
                return false;
            }
            var sendType = "S";//发送
            var params = {};
            var messageWithBLOBsStr = {};
            // data.field.sender = '1a213ba3-e487-467f-ad9a-453c6cfa6f3e';//发送人
            // data.field.deptId = '6be2e71c-1253-405b-a84e-ab62e4973e5d';//发送人部门
            // data.field.orgId = '000000000000000000000000000000000876';//发送人机构
            data.field.state = 'A';//A有效，B撤回
            // data.field.msgContent = layedit.getContent(index);//正文
            data.field.msgContent = CKEDITOR.instances.msgContent.getData();
            messageWithBLOBsStr.sendScope = $("#sendScopeId").val();
            messageWithBLOBsStr.sendScopeName = $("#sendScopeName").text();
            messageWithBLOBsStr.receiver = $("#receiver").val();
            messageWithBLOBsStr.msgContent = data.field.msgContent;
            messageWithBLOBsStr.receiveName = $("#receiveName").text();
            // messageWithBLOBsStr.msgId = msgId;
            messageWithBLOBsStr.msgType = data.field.msgType;
            messageWithBLOBsStr.msgTitle = data.field.msgTitle;
            // messageWithBLOBsStr.sender = data.field.sender;
            // messageWithBLOBsStr.deptId = data.field.deptId;
            // messageWithBLOBsStr.orgId = data.field.orgId;
            messageWithBLOBsStr.state = data.field.state;
            params.messageWithBLOBsStr = JSON.stringify(messageWithBLOBsStr);
            params.sendType = sendType;
            params.fileIds = fileIdArr.join(",");
            $.ajax({
                type : "POST",
                url : getRootPath()+'/rabbitmq/sendMessage',
                dataType : "json",
                async : false,
                data : params,
                success:function(iJson){
                    if ("200" == iJson.code){
                        layer.confirm("发送成功！",{
                            skin: "layui-layer-lan",
                            title: '系统提示',
                            btn: ['确定'], //按钮
                            cancel: function(){
                                layui.use(['form'],function(){
                                    $("#writeMsgForm")[0].reset();
                                    $("#sendScopeName").text(orgName);
                                    $("#sendScopeId").val(orgId);
                                    $("#receiveName").text("请选择收件人");
                                    CKEDITOR.instances.msgContent.setData("");
                                    $('.uploadFileName ul').html("");
                                    layui.form.render();
                                });
                            }
                        }, function(index){
                            layer.close(index);
                            layui.use(['form'],function(){
                                $("#writeMsgForm")[0].reset();
                                $("#sendScopeName").text(orgName);
                                $("#sendScopeId").val(orgId);
                                $("#receiveName").text("请选择收件人");
                                CKEDITOR.instances.msgContent.setData("");
                                $('.uploadFileName ul').html("");
                                layui.form.render();
                            });
                        });
                    }else{
                        layer.alert(iJson.msg, {"skin": "layui-layer-lan","title": '系统提示'});
                    }
                }
            });
            return false;
        });
        form.on('submit(saveSubmit)', function(data){
            if(''==$.trim($("#sendScopeName").text())){
                layer.alert('发送范围不能为空！', {"skin": "layui-layer-lan","title": '系统提示'});
                return false;
            }
            if(''==$.trim($("#receiveName").text()) || '请选择收件人'==$.trim($("#receiveName").text())){
                layer.alert('收件人不能为空！', {"skin": "layui-layer-lan","title": '系统提示'});
                return false;
            }
            if(''==$.trim($("#msgTitle").val())){
                layer.alert('主题不能为空！', {"skin": "layui-layer-lan","title": '系统提示'});
                return false;
            }
            if($.trim($("#msgTitle").val()).length > 50){
                layer.alert('主题不能超过50个字符！', {"skin": "layui-layer-lan","title": '系统提示'});
                return false;
            }
            if(!submitCheckImage()){
                return false;
            }
            var sendType = "T";//保存
            var params = {};
            var messageWithBLOBsStr = {};
            // data.field.sender = '1a213ba3-e487-467f-ad9a-453c6cfa6f3e';//发送人
            // data.field.deptId = '6be2e71c-1253-405b-a84e-ab62e4973e5d';//发送人部门
            // data.field.orgId = '000000000000000000000000000000000876';//发送人机构
            data.field.state = 'A';//A有效，B撤回
            // data.field.msgContent = layedit.getContent(index);//正文
            data.field.msgContent = CKEDITOR.instances.msgContent.getData();
            messageWithBLOBsStr.sendScope = $("#sendScopeId").val();
            messageWithBLOBsStr.sendScopeName = $("#sendScopeName").text();
            messageWithBLOBsStr.receiver = $("#receiver").val();
            messageWithBLOBsStr.msgContent = data.field.msgContent;
            messageWithBLOBsStr.receiveName = $("#receiveName").text();
            // messageWithBLOBsStr.msgId = msgId;
            messageWithBLOBsStr.msgType = data.field.msgType;
            messageWithBLOBsStr.msgTitle = data.field.msgTitle;
            // messageWithBLOBsStr.sender = data.field.sender;
            // messageWithBLOBsStr.deptId = data.field.deptId;
            // messageWithBLOBsStr.orgId = data.field.orgId;
            messageWithBLOBsStr.state = data.field.state;
            params.messageWithBLOBsStr = JSON.stringify(messageWithBLOBsStr);
            params.sendType = sendType;
            params.fileIds = fileIdArr.join(",");
            $.ajax({
                type : "POST",
                url : getRootPath()+'/rabbitmq/sendMessage',
                dataType : "json",
                async : false,
                data : params,
                success:function(iJson){
                    if ("200" == iJson.code){
                        layer.confirm("保存成功！",{
                            skin: "layui-layer-lan",
                            title: '系统提示',
                            btn: ['确定'], //按钮
                            cancel: function(){
                                layui.use(['form'],function(){
                                    $("#writeMsgForm")[0].reset();
                                    $("#receiveName").text("请选择收件人");
                                    CKEDITOR.instances.msgContent.setData("");
                                    $("#msgContent").val("");
                                    $('.uploadFileName ul').html("");
                                    layui.form.render();
                                });
                            }
                        }, function(index){
                            layer.close(index);
                            layui.use(['form'],function(){
                                $("#writeMsgForm")[0].reset();
                                $("#receiveName").text("请选择收件人");
                                CKEDITOR.instances.msgContent.setData("");
                                $("#msgContent").val("");
                                $('.uploadFileName ul').html("");
                                layui.form.render();
                            });
                        });
                    }else{
                        layer.alert(iJson.msg, {"skin": "layui-layer-lan","title": '系统提示'});
                    }
                }
            });
            return false;
        });
        layui.use('upload', function(){
            var $ = layui.jquery,upload = layui.upload;
            //拖拽上传
            upload.render({
                elem: '#upload-button'
                ,url: getRootPath()+'/attachmentUpload/fileUpload'
                ,accept: 'file'
                ,exts: attachment_type //只允许上传的文件
                ,datatype:'text'
                ,before: function (obj) {
                    layer.load(); //上传loading
                }
                ,done: function(res){
                    layer.closeAll('loading'); //关闭loading
                    if("200" === res.code){
                        fileIdArr.push(res.data.fileId);
                        $(".uploadFileName ul").append(
                            '<li>' +
                                '<span class="uploadName">'+res.data.fileTitle+'</span>' +
                                // '<input type="button" class="layui-btn layui-btn-normal layui-btn-xs dlBtn" value="下载" data-address="'+res.data.fileFdfsAddress+'" data-name="'+res.data.fileTitle+'">' +
                                '<input type="button" class="layui-btn layui-btn-normal layui-btn-xs delBtn" value="删除" data-id="'+res.data.fileId+'">' +
                            '</li>');
                    }else{
                        layer.msg(res.msg, {icon: 2});
                    }
                }
                ,error: function(index, upload){
                    layer.closeAll('loading'); //关闭loading
                }
            });
        });
    });

    //下载
    /*$(".uploadFileName").delegate(".dlBtn","click",function(){
        var fileName = $(this).attr("data-name");
        var dataAddress = $(this).attr("data-address");
        fileName = fileName.substr(0,fileName.indexOf('.'));
        downloadFile(downloadServerIp + "/" + dataAddress+ "?n=" + encodeURI(fileName));
    });*/
    //删除
    $(".uploadFileName").delegate(".delBtn","click",function(){
        var o = $(this);
        layer.confirm('确定删除？', {
            skin: "layui-layer-lan",
            title: '系统提示'
        }, function(){
            var fileId = o.attr("data-id");
            $.ajax({
                type : "POST",
                url : getRootPath()+'/msgFile/updateFileStateByFileId/'+ fileId,
                dataType : "json",
                async : false,
                success:function(iJson){
                    if (iJson.code === '200'){
                        for(var i=0;i<fileIdArr.length;i++){
                            if(fileId == fileIdArr[i]){
                                fileIdArr.splice(i,1);
                            }
                        }
                        o.parent().remove();
                        layer.alert("删除成功！", {"skin": "layui-layer-lan","title": '系统提示'});
                    }else{
                        layer.alert(iJson.msg, {"skin": "layui-layer-lan","title": '系统提示'});
                    }
                }
            });
        }, function(index){
            layer.close(index);
        });
    });
    /*function downloadFile(url) {
        try{
            var elemIF = document.createElement("iframe");
            elemIF.src = url;
            elemIF.style.display = "none";
            document.body.appendChild(elemIF);
        }catch(e){

        }
    }*/
    CKEDITOR.instances.msgContent.on('instanceReady',function (e) {
        this.document.on("paste", function (e) {
            changeCheckImage();
        });
    });
    function changeCheckImage(){
        CKEDITOR.instances.msgContent.on('change',function (e) {
            var a = e.editor.document;
            var b = a.find("img");
            var count = b.count();
            if(count > 0){
                layer.msg('消息内容中不可插入图片', {icon: 2});
            }
        });
    }
    function submitCheckImage() {
        var a = CKEDITOR.instances.msgContent.document;
        var b = a.find("img");
        var count = b.count();
        if(count > 0){
            layer.msg('消息内容中不可插入图片', {icon: 2});
            return false;
        }
        return true;
    }
});