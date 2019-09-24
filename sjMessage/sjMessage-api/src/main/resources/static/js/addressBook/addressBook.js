$(function(){
    var orgId = "";
    var deptId = "";
    var loginUserOrgId = "";
    //获取当前登录人组织ID
    $.ajax({
        url:getRootPath()+"/ruser/queryUser",
        type:"post",
        dataType: "json",
        async : false,
        success:function (data){
            if(null != data && "" != data){
                orgId = data[2];
                loginUserOrgId = data[2];
            } else{
                layer.alert("获取当前登录人信息失败", {"skin": "layui-layer-lan","title": '系统提示'});
            }
        }
    });
    //渲染通讯录列表
    layui.use(['table','form'], function () {
        var table = layui.table,form = layui.form;
        table.render({
            elem: '#ABTable'
            , toolbar: '#toolbar'
            ,defaultToolbar: ['filter', 'print']
            , url: getRootPath()+'/contact/personListByOrgId'
            , title: '用户表'
            ,page: {theme: '#1E9FFF'} //开启分页
            , method: 'POST'
            , xhrFields: {withCredentials: true}
            , limit: 15 //每页默认显示的数量
            ,limits:[10,15,20,25,30]
            , response: {
                statusCode: 200
            }
            , cols: [[
                {type: 'checkbox'}
                ,{type:'numbers', title: '序号'}
                ,{field: 'ORG_NAME', title: '机构', width:'30%', align: 'left',templet: function(d){
                    return '<span title="'+d.ORG_NAME+'">'+d.ORG_NAME+'</span>';
                }}
                , {field: 'DEPT_NAME', title: '部门', width: '30%', align: 'left', templet: function (d) {
                    return '<span title="'+d.DEPT_NAME+'">'+d.DEPT_NAME+'</span>';
                }}
                , {field: 'PERSON_NAME', title: '姓名', align: 'center', templet: function (d) {
                    return '<span title="'+d.PERSON_NAME+'">'+d.PERSON_NAME+'</span>';
                }}
                , {field: 'PERSON_DUTY', title: '职位', align: 'center', sort: true, templet: function (d) {
                    if(undefined == d.PERSON_DUTY){
                        return '';
                    } else {
                        return '<span title="'+d.PERSON_DUTY+'">'+d.PERSON_DUTY+'</span>';
                    }
                }}
            ]]
        });

        //根据当前登录人的组织ID渲染机构和部门下拉框
        $.ajax({
            type : "POST",
            url : getRootPath()+'/contact/orgList',
            dataType : "json",
            async : false,
            success:function(iJson){
                if (iJson.code === '200'){
                    if(null != iJson.data && "" != iJson.data){
                        $.each(iJson.data, function (i, item) {
                            $("#org").append("<option value="+ item.ORG_ID +">"+ item.ORG_NAME +"</option>");
                        });
                        $("#org").val(orgId);
                        // form.render('select');
                    }
                    $.ajax({
                        type : "POST",
                        url : getRootPath()+'/contact/deptList',
                        dataType : "json",
                        async : false,
                        data : {orgId : orgId},
                        success:function (dJson) {
                            if(dJson.code === '200'){
                                if(null != dJson.data && "" != dJson.data){
                                    $("#dept").append("<option value=''>请选择</option>");
                                    $.each(dJson.data, function (j, jtem) {
                                        $("#dept").append("<option value="+ jtem.DEPT_ID +">"+ jtem.DEPT_NAME +"</option>");
                                    });
                                }
                                form.render('select');
                            } else {
                                layer.alert(dJson.msg, {"skin": "layui-layer-lan","title": '系统提示'});
                            }
                        }
                    });
                }else{
                    layer.alert(iJson.msg, {"skin": "layui-layer-lan","title": '系统提示'});
                }
            }
        });

        form.render();

        form.on('select(org)',function (data) {
            $("#dept").empty();
            orgId = data.value;
            deptId = "";
            $("#personName").val("");
            $("#personDuty").val("");
            table.reload("ABTable", {
                where:{
                    orgId:orgId,
                    deptId:deptId,
                    personName:"",
                    personDuty:""
                }
            });
            $.ajax({
                type : "POST",
                url : getRootPath()+'/contact/deptList',
                dataType : "json",
                async : false,
                data : {orgId : orgId},
                success:function (dJson) {
                    if(dJson.code === '200'){
                        if(null != dJson.data && "" != dJson.data){
                            $("#dept").append("<option value=''>请选择</option>");
                            $.each(dJson.data, function (j, jtem) {
                                $("#dept").append("<option value="+ jtem.DEPT_ID +">"+ jtem.DEPT_NAME +"</option>");
                            });
                        }
                        form.render('select');
                    } else {
                        layer.alert(dJson.msg, {"skin": "layui-layer-lan","title": '系统提示'});
                    }
                }
            });
        });

        form.on('select(dept)',function (data) {
            deptId = data.value;
            $("#personName").val("");
            $("#personDuty").val("");
            table.reload("ABTable", {
                where:{
                    orgId:orgId,
                    deptId:deptId,
                    personName:"",
                    personDuty:""
                }
            });
        });

        //搜索
        form.on('submit(filterCondition)', function(data){
            var personName = $.trim(data.field.personName);
            var personDuty = $.trim(data.field.personDuty);
            table.reload("ABTable", {
                where:{
                    orgId:orgId,
                    deptId:deptId,
                    personName:personName,
                    personDuty:personDuty
                }
            });
        });

        //重置
        $("#reset-btn").click(function(){
            $("#org").empty();
            $("#dept").empty();
            orgId = loginUserOrgId;
            deptId = "";
            table.reload("ABTable", {
                where:{
                    orgId:loginUserOrgId,
                    deptId:deptId,
                    personName:"",
                    personDuty:""
                }
            });
            $.ajax({
                type : "POST",
                url : getRootPath()+'/contact/orgList',
                dataType : "json",
                async : false,
                success:function(iJson){
                    if (iJson.code === '200'){
                        if(null != iJson.data && "" != iJson.data){
                            $.each(iJson.data, function (i, item) {
                                $("#org").append("<option value="+ item.ORG_ID +">"+ item.ORG_NAME +"</option>");
                            });
                            $("#org").val(orgId);
                            // form.render('select');
                        }
                        $.ajax({
                            type : "POST",
                            url : getRootPath()+'/contact/deptList',
                            dataType : "json",
                            async : false,
                            data : {orgId : orgId},
                            success:function (dJson) {
                                if(dJson.code === '200'){
                                    if(null != dJson.data && "" != dJson.data){
                                        $("#dept").append("<option value=''>请选择</option>");
                                        $.each(dJson.data, function (j, jtem) {
                                            $("#dept").append("<option value="+ jtem.DEPT_ID +">"+ jtem.DEPT_NAME +"</option>");
                                        });
                                    }
                                    form.render('select');
                                } else {
                                    layer.alert(dJson.msg, {"skin": "layui-layer-lan","title": '系统提示'});
                                }
                            }
                        });
                    }else{
                        layer.alert(iJson.msg, {"skin": "layui-layer-lan","title": '系统提示'});
                    }
                }
            });
        });
    });
});



