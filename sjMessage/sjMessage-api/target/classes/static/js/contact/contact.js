var currentPage=1; //当前页数
var pageLimit=10;  //每页条数
var orgId = "000000000000000000000000000000000876";


window.onload=function(){
    sjDeptByOrgId(orgId);
    fenyeByOrgId(orgId,currentPage,pageLimit);
}


var zTreeObj;
// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
var zSetting = {
    async: {
        enable: true,
        dataType: "json",
        type:"get",
        url: "/contact/contactList/orgTree",
        dataFilter: filter
    },
    view: {
        dblClickExpand: true, //双击节点时，是否自动展开父节点的标识
        nameIsHTML: false, //设置 name 属性是否支持 HTML 脚本
        showLine: true, //是否显示节点之间的连线
        fontCss:{'color':'black'}, //字体样式函数
        selectedMulti: false, //设置是否允许同时选中多个节点
        txtSelectedEnable: true,
        addDiyDom: addDiyDom
    },
    edit:{
        enable: false //设置 zTree 是否处于编辑状态
    },
    data: {
        //简单数据模式
        simpleData: {
            enable:true,
            idKey: "id", //节点数据中保存唯一标识的属性名称。
            pIdKey: "pId", //节点数据中保存其父节点唯一标识的属性名称。
            rootPId: null
        }
    },
    callback: {
        onCheck: onCheck,
        onClick: zTreeOnClick,
        onAsyncSuccess: zTreeOnAsyncSuccess
    }
};

var clearFlag = false;
function onCheck(e, treeId, treeNode) {
    if (clearFlag) {
        clearCheckedOldNodes();
    }
}
function clearCheckedOldNodes() {
    var zTree = $.fn.zTree.getZTreeObj("orgList"),
        nodes = zTree.getChangeCheckedNodes();
    for (var i=0, l=nodes.length; i<l; i++) {
        nodes[i].checkedOld = nodes[i].checked;
    }
}

function createTree() {
    $.fn.zTree.init($("#orgList"), zSetting);
    clearFlag = $("#last").attr("checked");
}
function filter(treeId, parentNode, childNodes) {
    if (!childNodes) return null;
    for (var i=0, l=childNodes.length; i<l; i++) {
        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
    }
    return childNodes;
}

function zTreeOnAsyncSuccess(event, treeId, treeNode, msg){
    var treeObj = $.fn.zTree.getZTreeObj("orgList");
    var nodes = treeObj.getNodes();
    if (nodes.length>0) {
        for(var i=0;i<nodes.length;i++){
            treeObj.expandNode(nodes[i], true, false, false);//默认展开第一级节点
        }
    }
}

//ztree中标题过长，以省略号处理
function addDiyDom(treeId, treeNode) {
    var spaceWidth = 5;
    var switchObj = $("#" + treeNode.tId + "_switch"),
        checkObj = $("#" + treeNode.tId + "_check"),
        icoObj = $("#" + treeNode.tId + "_ico");
    switchObj.remove();
    checkObj.remove();
    icoObj.parent().before(switchObj);
    icoObj.parent().before(checkObj);

    var spantxt = $("#" + treeNode.tId + "_span").html();
    if (spantxt.length > 15) {
        spantxt = spantxt.substring(0, 15) + "...";
        $("#" + treeNode.tId + "_span").html(spantxt);
    }
}

$(document).ready(function(){
    createTree();
    $("#init").bind("change", createTree);
    $("#last").bind("change", createTree);
});

//ztree点击事件，返回orgId
function zTreeOnClick(event, treeId, treeNode){
    $(".choosedept").empty();
    $("#jqGrid").empty()
    var orgId = treeNode.id;
    sjDeptByOrgId(orgId);
    fenyeByOrgId(orgId,currentPage,pageLimit);
    $("#search").click(function () {
    })
}

//下拉框选择事件
$("select.choosedept").change(function(){
    var deptId = $(this).val();
    sjPersonByDeptId(deptId,currentPage,pageLimit);
    fenyeByDeptId(deptId,currentPage,pageLimit);
});

//遍历json，展示人员
function personJson(data) {
    $.each(data, function (i, e) {
        $.each(e, function (m, n) {
            $.each(n,function (j,k) {
                if(k.PERSON_NAME != undefined){
                    var $tr = $("<tr></tr>")
                    $tr.appendTo("#jqGrid")
                    var $td_deptname = $("<td>"+k.DEPT_NAME+"</td>")
                    var $td_name = $("<td>"+k.PERSON_NAME+"</td>")
                    if (k.PERSON_DUTY == undefined){
                        var $td_personDuty = $("<td></td>")
                    } else {
                        var $td_personDuty = $("<td>"+k.PERSON_DUTY+"</td>")
                    }
                    var $td_orgName = $("<td>"+k.ORG_NAME+"</td>")
                    $tr.append($td_deptname).append($td_name).append($td_personDuty).append($td_orgName)
                }
            })
        })
    })
}

//根据部门名查询人员
function sjPersonByDeptId(deptId,currentPage,pageLimit) {
    $.ajax({
        type: "GET",
        url: "/contact/contactList/personListByDeptId",
        dataType: "json",
        data:{
            deptId: deptId,
            pageNum: currentPage,
            pageSize:pageLimit
        },
        contentType: "application/json",
        success: function(data) {
            $("#jqGrid").empty();
            personJson(data)
        }
    });
}

//根据机构名查询人员
function sjAllPersonByOrgId(orgId,currentPage,pageLimit) {
    $.ajax({
        type: "GET",
        url: "/contact/contactList/personListByOrgId",
        dataType: "json",
        data:{
            orgId:orgId,
            pageNum:currentPage,
            pageSize:pageLimit
        },
        contentType: "application/json",
        success: function(data) {
            $("#jqGrid").empty();
            personJson(data);
        }
    });
}

//根据机构查询部门
function sjDeptByOrgId(orgId){
    $.ajax({
        type: "GET",
        url: "/contact/contactList/deptList",
        dataType: "json",
        data:{
            orgId:orgId
        },
        contentType: "application/json",
        success: function(data) {
            $(".choosedept").append("<option value='0'>请选择部门</option>")
            $.each(data, function (i, e) {
                $.each(e, function (m, n) {
                    if (n.DEPT_NAME != undefined) {
                        $(".choosedept").append("<option value='" + n.DEPT_ID + "'>" + n.DEPT_NAME + "</option>")
                    }
                })
            })
            sjAllPersonByOrgId(orgId,currentPage,pageLimit);
        }
    });
}



//根据机构对人员进行分页
function fenyeByOrgId(orgId,currentPage,pageLimit) {
    $.ajax({
        type: "GET",
        url: "/contact/contactList/personListByOrgId",
        dataType: "json",
        data:{
            orgId: orgId,
            pageNum: currentPage,
            pageSize:pageLimit,
        },
        contentType: "application/json",
        success: function(data) {
            total = data.data.total;
            currentPage = data.data.pageNum;
            pageLimit = data.data.pageSize
            layui.use('laypage', function(){
                var laypage = layui.laypage;
                laypage.render({
                    elem: 'paging' //注意，这里的 page 是 ID，不用加 # 号
                    ,//数据总数，从服务端得到
                    limits:[10,15,20,25,30],
                    prev:"<<",
                    next:">>",
                    theme:"#0099ff",
                    layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'],
                    count:total,
                    curr:currentPage,
                    limit:pageLimit,
                    jump:function(data, first){
                        currentPage =data.curr;
                        pageLimit = data.limit;
                        if(!first){ //点击右下角分页时调用
                            sjAllPersonByOrgId(orgId,currentPage,pageLimit);
                        }
                    }
                });
            })
        }
    });
}

//根据部门对人员进行分页
function fenyeByDeptId(deptId,currentPage,pageLimit) {
    $.ajax({
        type: "GET",
        url: "/contact/contactList/personListByDeptId",
        dataType: "json",
        data:{
            deptId: deptId,
            pageNum: currentPage,
            pageSize:pageLimit,
        },
        contentType: "application/json",
        success: function(data) {
            total = data.data.total;
            currentPage = data.data.pageNum;
            pageLimit = data.data.pageSize
            layui.use('laypage', function(){
                var laypage = layui.laypage;
                laypage.render({
                    elem: 'paging' //注意，这里的 page 是 ID，不用加 # 号
                    ,//数据总数，从服务端得到
                    limits:[10,15,20,25,30],
                    prev:"<<",
                    next:">>",
                    theme:"#0099ff",
                    layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'],
                    count:total,
                    curr:currentPage,
                    limit:pageLimit,
                    jump:function(data, first){
                        currentPage =data.curr;
                        pageLimit = data.limit;
                        if(!first){ //点击右下角分页时调用
                            sjPersonByDeptId(deptId,currentPage,pageLimit);
                        }
                    }
                });
            })
        }
    });
}

//搜索全局
function selectAllPerson(personName,personDuty,currentPage,pageLimit) {
    $.ajax({
        type: "GET",
        url: "/contact/contactList/personList/all",
        dataType: "json",
        data:{
            personName:personName,
            personDuty:personDuty,
            pageNum:currentPage,
            pageSize:pageLimit
        },
        contentType: "application/json",
        success: function(data) {
            $("#jqGrid").empty();
            personJson(data);
        }
    });
}

//搜索时的分页
function fenyeByAllPerson(personName,personDuty,currentPage,pageLimit) {
    $.ajax({
        type: "GET",
        url: "/contact/contactList/personList/all",
        dataType: "json",
        data:{
            pageNum: currentPage,
            pageSize:pageLimit,
            personName:personName,
            personDuty:personDuty
        },
        contentType: "application/json",
        success: function(data) {
            total = data.data.total;
            currentPage = data.data.pageNum;
            pageLimit = data.data.pageSize;
            layui.use('laypage', function(){
                var laypage = layui.laypage;
                laypage.render({
                    elem: 'paging' //注意，这里的 page 是 ID，不用加 # 号
                    ,//数据总数，从服务端得到
                    limits:[10,15,20,25,30],
                    prev:"<<",
                    next:">>",
                    theme:"#0099ff",
                    layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'],
                    count:total,
                    curr:currentPage,
                    limit:pageLimit,
                    jump:function(data, first){
                        currentPage =data.curr;
                        pageLimit = data.limit;
                        if(!first){ //点击右下角分页时调用
                            selectAllPerson(personName,personDuty,currentPage,pageLimit);
                        }
                    }
                });
            })
        }
    });
}

// 模糊查询
function searchPerson() {
    var personName = $("#searchContext").val();
    var personDuty = $("#searchContext").val();
    if(personName != null && personDuty !=null && personName != "" && personDuty !=""){
        selectAllPerson(personName,personDuty,currentPage,pageLimit);
        fenyeByAllPerson(personName,personDuty,currentPage,pageLimit);
    }
}