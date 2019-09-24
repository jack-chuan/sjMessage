//表单回填
$(function () {
    var fileIdArr = [];
    var attachment_type = "";
    //获取系统参数
    $.ajax({
        type : "POST",
        url : getRootPath()+'/config/getConfig',
        dataType : "json",
        async : false,
        success : function (ipJson) {
            if(ipJson.code === '200'){
                attachment_type = ipJson.data.attachment_type;
            } else {
                layer.alert(ipJson.msg, {"skin": "layui-layer-lan","title": '系统提示'});
            }
        }
    });
    //参数
    var msgId = Utils.getUrlParam("msgId");
    // var downloadServerIp = Utils.getUrlParam("downloadServerIp");

    //表单回填
    layui.use(['form'],function(){
        var form = layui.form;
        CKEDITOR.replace( 'msgContent');
        form.render();
        $.ajax({
            type: "POST",
            url: getRootPath()+"/receiveMsg/getMsgDetail/"+ msgId,
            dataType: "json",
            contentType: "application/json",
            success: function(data) {
                $("input[name='msgTitle']").val(data.data.msgTitle);  //文章标题
                $("#receiveName").text(data.data.sendName);
                $("#receiver").val(data.data.sender);
                $("#sendScopeName").text(data.data.sendScopeName);
                $("#sendScopeId").val(data.data.sendScopeId);
            }
        });

        //消息保存
        form.on('submit(saveReceiveMsg)', function (data) {
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
            // data.field.sender = 'ef067723-383d-4192-8679-23f58a266f39';//发送人
            // data.field.deptId = '6be2e71c-1253-405b-a84e-ab62e4973e5d';//发送人部门
            // data.field.orgId = '000000000000000000000000000000000876';//发送人机构
            data.field.state = 'A';//A有效，B撤回
            messageWithBLOBsStr.sendScope = $("#sendScopeId").val();
            messageWithBLOBsStr.sendScopeName = $("#sendScopeName").text();
            data.field.msgContent = CKEDITOR.instances.msgContent.getData();
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
            params.sendType = sendType;
            params.messageWithBLOBsStr = JSON.stringify(messageWithBLOBsStr);
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
                                window.location.href = getRootPath()+"/pageJump/receiveMsg";
                            }
                        }, function(index){
                            window.location.href = getRootPath()+"/pageJump/receiveMsg";
                        });
                    }else{
                        layer.alert(iJson.msg, {"skin": "layui-layer-lan","title": '系统提示'});
                    }
                }
            });
            return false;
        });

        form.on('submit(sendReceiveMsg)', function(data){
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
            // data.field.sender = 'ef067723-383d-4192-8679-23f58a266f39';//发送人
            // data.field.deptId = '6be2e71c-1253-405b-a84e-ab62e4973e5d';//发送人部门
            // data.field.orgId = '000000000000000000000000000000000876';//发送人机构
            data.field.state = 'A';//A有效，B撤回
            messageWithBLOBsStr.sendScope = $("#sendScopeId").val();
            messageWithBLOBsStr.sendScopeName = $("#sendScopeName").text();
            data.field.msgContent = CKEDITOR.instances.msgContent.getData();
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
                        layer.confirm("回复成功！",{
                            skin: "layui-layer-lan",
                            title: '系统提示',
                            btn: ['确定'], //按钮
                            cancel: function(){
                                window.location.href = getRootPath()+"/pageJump/receiveMsg";
                            }
                        }, function(index){
                            window.location.href = getRootPath()+"/pageJump/receiveMsg";
                        });
                    }else{
                        layer.alert(iJson.msg, {"skin": "layui-layer-lan","title": '系统提示'});
                    }
                }
            });
            return false;
        });
        //文件上传
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
                    layer.msg('上传文件失败', {icon: 2});
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
                success:function(data){
                    if (data.code === '200'){
                        for(var i=0;i<fileIdArr.length;i++){
                            if(fileId == fileIdArr[i]){
                                fileIdArr.splice(i,1);
                            }
                        }
                        o.parent().remove();
                        layer.alert("删除成功！", {"skin": "layui-layer-lan","title": '系统提示'});
                    }else{
                        layer.alert(data.msg, {"skin": "layui-layer-lan","title": '系统提示'});
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












