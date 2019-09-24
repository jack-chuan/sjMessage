$(function () {
    var msgId = Utils.getUrlParam("msgId");
    var downloadServerIp = Utils.getUrlParam("downloadServerIp");
    $.ajax({
        type : "POST",
        url : getRootPath()+'/receiveMsg/getMsgDetail/'+ msgId,
        dataType : "json",
        async : false,
        // data : params,
        success:function(iJson){
            if (iJson.code === '200'){
                for (var key in iJson.data) {
                    $(".result").each(function () {
                        if(key == $(this).attr("name")){
                            if(null != iJson.data[key]){
                                if("msgContent" == key){
                                    $(this).html(iJson.data[key]);
                                } else {
                                    $(this).text(iJson.data[key]);
                                }
                            }
                        }
                    });
                    var msgType = $("#msgType").text();
                    if(msgType == "一般"){
                        $("#msgType").addClass("commonly");
                    } else if(msgType == "紧急"){
                        $("#msgType").addClass("Emergency");
                    }
                    if(key == "fileList"){
                        $.each(iJson.data[key], function(idx, obj) {
                            $(".uploadFileName ul").append('<li>' +
                                '<span class="uploadName download" data-address="'+obj.FILE_FDFS_ADDRESS+'" data-name="'+obj.FILE_TITLE+'">'+obj.FILE_TITLE+'</span>' +
                                // '<input type="button" class="layui-btn layui-btn-normal layui-btn-xs dlBtn" value="下载" data-address="'+obj.FILE_FDFS_ADDRESS+'" data-name="'+obj.FILE_TITLE+'">' +
                                '</li>')
                        });
                    }
                }
            }else{
                layer.alert(iJson.msg, {"skin": "layui-layer-lan","title": '系统提示'});
            }
        }
    });
    //下载
    $(".uploadFileName").delegate(".uploadName","click",function(){
        var fileName = $(this).attr("data-name");
        var dataAddress = $(this).attr("data-address");
        fileName = fileName.substr(0,fileName.indexOf('.'));
        downloadFile(downloadServerIp + "/" + dataAddress+ "?n=" + encodeURI(fileName));
    });
    function downloadFile(url) {
        try{
            var elemIF = document.createElement("iframe");
            elemIF.src = url;
            elemIF.style.display = "none";
            document.body.appendChild(elemIF);
        }catch(e){

        }
    }
});


