var arrName = [];
var arrId = [];
var arrUserId = [];
var arrUserName = [];
var clearFlag = false;

$(document).ready(function(){
    createOrgTree();
    $("#init").bind("change", createOrgTree);
    $("#last").bind("change", createOrgTree);
    var orgId = $("#sendScopeId").val();
    var userIds = $("#receiver").val();
    if(userIds != ""){
        createUserTree(orgId);
    }else {
        createUserTree(orgId);
        arrUserId = [];
        arrUserName = [];
        $(".receiveName").text("");
        $(".receiver").val("");
    }
});

/**
 * orgTree
 */
var zTreeObj;
// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
var zSetting = {
    async: {
        enable: true,
        type: "get",
        dataType: "json",
        url: getRootPath()+'/contact/orgTree',
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
    check: {
        chkboxType: {"Y": "", "N": ""},
        chkStyle: "checkbox", //复选框类型
        enable: true //每个节点上是否显示 CheckBox
    }
};

/**
 * orgTree结束
 * userTree开始
 *
 * */
var Setting = {
    async: {
        enable: true,
        type: "get",
        dataType: "json",
        dataFilter: filter
    },
    view: {
        dblClickExpand: true, //双击节点时，是否自动展开父节点的标识
        nameIsHTML: false, //设置 name 属性是否支持 HTML 脚本
        showLine: true, //是否显示节点之间的连线
        fontCss: {'color': 'black'}, //字体样式函数
        selectedMulti: false, //设置是否允许同时选中多个节点
        txtSelectedEnable: true,
        addDiyDom: addDiyDom
    },
    check: {
        chkboxType: {"Y": "", "N": ""},
        chkStyle: "checkbox", //复选框类型
        enable: true //每个节点上是否显示 CheckBox
    },
    edit: {
        enable: false //设置 zTree 是否处于编辑状态
    },
    data: {
        //简单数据模式
        simpleData: {
            enable: true,
            idKey: "id", //节点数据中保存唯一标识的属性名称。
            pIdKey: "pId", //节点数据中保存其父节点唯一标识的属性名称。
            rootPId: null
        }
    }
}

function createOrgTree() {
    $.fn.zTree.init($("#orgList"), zSetting);
    fuzzySearch('orgList','#org_name',null,true); //初始化模糊搜索方法
}

function createUserTree(orgId) {
    $.ajax({
        type: "GET",
        url: getRootPath()+'/userTree/all',
        dataType: "json",
        data:{
            orgId:orgId
        },
        contentType: "application/json",
        success: function(data) {
            var zNodes = data;
            $.fn.zTree.init($("#userList"), Setting, zNodes);
            fuzzySearch('userList','#user_name',null,true);
            clearFlag = $("#last").attr("checked");//初始化模糊搜索方法
        }
    });
}

function filter(treeId, parentNode, childNodes) {
    if (!childNodes) return null;
    for (var i=0, l=childNodes.length; i<l; i++) {
        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
    }
    return childNodes;
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

//layui弹出框，点击确定回填到input框
layui.use('layer', function() {
    var layer = layui.layer;
    $('#chooseSendScope').on('click', function () {
        var zTreeObj = $.fn.zTree.getZTreeObj("orgList");//获取树对象
        // zTreeObj.reAsyncChildNodes(null, "refresh",true);
        var orgIds = $("#sendScopeId").val().split(",");
        var nodes = zTreeObj.transformToArray(zTreeObj.getNodes());
        for(var i = 0; i < nodes.length; i++){
            for(var j = 0; j < orgIds.length; j++){
                if(nodes[i].id == orgIds[j]){
                    var parent = nodes[i].getParentNode();
                    if(parent !=null && !parent.open){
                        zTreeObj.expandNode(parent,true,true);
                    }
                    zTreeObj.checkNode(nodes[i], true, true);
                }
            }
        }
        $(".checkAllOrg").off("click");
        $(".checkAllOrg").on('click',function () {
            var zTreeObj = $.fn.zTree.getZTreeObj("orgList");//获取树对象
            var selectedNodes = zTreeObj.getCheckedNodes();
            if(selectedNodes.length != 0){
                zTreeObj.checkAllNodes(false);
            }else {
                zTreeObj.checkAllNodes(true);
            }
        })

        layer.open({
            type: 1,
            area: ['400px', '500px'],
            title: '请选择发送范围',
            btn:['确定'],
            shadeClose: false,   //点击遮罩关闭
            resize:false,
            content:$(".orgTree"),
            yes:function (index,layero) {
                 arrId = [];
                 arrName = [];
                var zTreeObj = $.fn.zTree.getZTreeObj("orgList");//获取树对象
                var nodes = zTreeObj.getCheckedNodes();
                for (var i = 0;i < nodes.length;i++){
                    arrId.push(nodes[i].id);
                    arrName.push(nodes[i].name);
                }
                $(".sendScopeName").text(arrName);
                $(".sendScopeId").val(arrId);
                if(arrId.length != 0){
                    var orgId = arrId.toString();
                    createUserTree(orgId);
                    arrUserId = [];
                    arrUserName = [];
                    $(".receiveName").text("");
                    $(".receiver").val("");
                }
                $(".orgTree").hide();
                layer.close(index);
            },
            cancel : function(){
                var zTreeObj = $.fn.zTree.getZTreeObj("orgList");//获取树对象
                var nodes = zTreeObj.transformToArray(zTreeObj.getNodes());
                zTreeObj.checkAllNodes(false);
                var arrOrgId = $(".sendScopeId").val().split(",");
                for(var i = 0; i<arrOrgId.length; i++){
                    for(var j = 0; j <nodes.length; j++){
                        if (arrOrgId[i] === nodes[j].id){
                            zTreeObj.checkNode(nodes[j], true, true);
                        }
                    }
                }
            }
        });
    });

    $('#choose').on('click', function () {
        if("" == $("#sendScopeName").text() || null == $("#sendScopeName").text()){
            layer.alert('请先选择发送范围！', {"skin": "layui-layer-lan","title": '系统提示'});
            return false;
        }
        var zTreeObj = $.fn.zTree.getZTreeObj("userList");//获取树对象
        if(zTreeObj != null){
            var nodes = zTreeObj.transformToArray(zTreeObj.getNodes());
            var userIds = $("#receiver").val().split(",");
            if(userIds != ""){
                for(var i = 0; i < nodes.length; i++){
                    for(var j = 0; j < userIds.length; j++){
                        if(nodes[i].id == userIds[j]){
                            var parent = nodes[i].getParentNode();
                            if(parent !=null && !parent.open){
                                zTreeObj.expandNode(parent,true,true);
                            }
                            zTreeObj.checkNode(nodes[i], true, true);
                        }
                    }
                }
            }

            layer.open({
                type: 1,
                area: ['400px', '500px'],
                title: '请选择收件人',
                btn:['确定'],
                shadeClose: false,   //点击遮罩关闭
                resize:false,
                content:$(".userTree"),
                yes:function (index,layero) {
                    arrUserId = [];
                    arrUserName = [];
                    // var zTreeObj = $.fn.zTree.getZTreeObj("userList");//获取树对象
                    var nodes = zTreeObj.getCheckedNodes();
                    for (var i = 0;i < nodes.length;i++){
                        arrUserId.push(nodes[i].id);
                        arrUserName.push(nodes[i].name);
                    }
                    $(".receiveName").text(arrUserName);
                    $(".receiver").val(arrUserId);
                    $(".userTree").hide();
                    layer.close(index)
                },
                cancel : function(){
                    var zTreeObj = $.fn.zTree.getZTreeObj("userList");//获取树对象
                    if (zTreeObj != undefined){
                        var nodes = zTreeObj.transformToArray(zTreeObj.getNodes());
                        zTreeObj.checkAllNodes(false);
                        var arrOrgId = $(".receiver").val().split(",");
                        for(var i = 0; i<arrOrgId.length; i++){
                            for(var j = 0; j <nodes.length; j++){
                                if (arrOrgId[i] === nodes[j].id){
                                    zTreeObj.checkNode(nodes[j], true, true);
                                }
                            }
                        }
                    }
                }
            });
        }
        $(".checkAllUser").off("click");
        $(".checkAllUser").on('click',function () {
            var zTreeObj = $.fn.zTree.getZTreeObj("userList");//获取树对象
            var selectedNodes = zTreeObj.getCheckedNodes();
            if(selectedNodes.length != 0){
                zTreeObj.checkAllNodes(false);
            }else {
                zTreeObj.checkAllNodes(true);
            }
        })
    });

    $("#clearSendScope").on("click",function () {
        arrId = [];
        arrName = [];
        $(".sendScopeName").text("");
        $(".sendScopeId").val("");
        arrUserId = [];
        arrUserName = [];
        $(".receiveName").text("");
        $(".receiver").val("");
        var zTreeObj = $.fn.zTree.getZTreeObj("orgList");//获取树对象
        zTreeObj.checkAllNodes(false);
        var zTree = $.fn.zTree.getZTreeObj("userList");//获取树对象
        if (zTree != undefined){
            zTree.destroy();
        }
    })

    $("#clearReceiver").on("click",function () {
        arrUserId = [];
        arrUserName = [];
        $(".receiveName").text("");
        $(".receiver").val("");
        var zTree = $.fn.zTree.getZTreeObj("userList");//获取树对象
        if(zTree != null){
            zTree.checkAllNodes(false);
        }
    })

})

/**
 * ztree org模糊搜索
 * @param zTreeId ztree对象的id,不需要#
 * @param searchField 输入框选择器
 * @param isHighLight 是否高亮,默认高亮,传入false禁用
 * @param isExpand 是否展开,默认合拢,传入true展开
 * @returns
 */
function fuzzySearch(zTreeId, searchField, isHighLight, isExpand){
    var zTreeObj = $.fn.zTree.getZTreeObj(zTreeId);//获取树对象
    if(!zTreeObj){
        alert("获取树对象失败");
    }
    var nameKey = zTreeObj.setting.data.key.name; //获取name属性的key
    isHighLight = isHighLight===false?false:true;//除直接输入false的情况外,都默认为高亮
    isExpand = isExpand?true:false;
    zTreeObj.setting.view.nameIsHTML = isHighLight;//允许在节点名称中使用html,用于处理高亮

    var metaChar = '[\\[\\]\\\\\^\\$\\.\\|\\?\\*\\+\\(\\)]'; //js正则表达式元字符集
    var rexMeta = new RegExp(metaChar, 'gi');//匹配元字符的正则表达式

    // 过滤ztree显示数据
    function ztreeFilter(zTreeObj,_keywords,callBackFunc) {
        if(!_keywords){
            _keywords =''; //如果为空，赋值空字符串
        }

        // 查找符合条件的叶子节点
        function filterFunc(node) {
            if(node && node.oldname && node.oldname.length>0){
                node[nameKey] = node.oldname; //如果存在原始名称则恢复原始名称
            }
            node.highlight = false; //取消高亮
            zTreeObj.updateNode(node); //更新节点让之前对节点所做的修改生效
            if (_keywords.length == 0) {
                //如果关键字为空,返回true,表示每个节点都显示
                zTreeObj.showNode(node);
                // zTreeObj.expandNode(node,isExpand); //关键字为空时是否展开节点
                return true;
            }
            //节点名称和关键字都用toLowerCase()做小写处理
            if (node[nameKey] && node[nameKey].toLowerCase().indexOf(_keywords.toLowerCase())!=-1) {
                /*if(isHighLight){ //如果高亮，对文字进行高亮处理
                    //创建一个新变量newKeywords,不影响_keywords在下一个节点使用
                    //对_keywords中的元字符进行处理,否则无法在replace中使用RegExp
                    var newKeywords = _keywords.replace(rexMeta,function(matchStr){
                        //对元字符做转义处理
                        return '\\' + matchStr;

                    });
                    node.oldname = node[nameKey]; //缓存原有名称用于恢复
                    //为处理过元字符的_keywords创建正则表达式,全局且不分大小写
                    var rexGlobal = new RegExp(newKeywords, 'gi');//'g'代表全局匹配,'i'代表不区分大小写
                    //无法直接使用replace(/substr/g,replacement)方法,所以使用RegExp
                    node[nameKey] = node.oldname.replace(rexGlobal, function(originalText){
                        //将所有匹配的子串加上高亮效果
                        var highLightText  = originalText;
                          /!*  '<span style="color: whitesmoke;background-color: darkred;">'
                            + originalText
                            +'</span>';*!/
                        return  highLightText;
                    });
                    zTreeObj.updateNode(node); //update让更名和高亮生效
                }*/
                zTreeObj.showNode(node);//显示符合条件的节点
                return true; //带有关键字的节点不隐藏
            }

            zTreeObj.hideNode(node); // 隐藏不符合要求的节点
            return false; //不符合返回false
        }
        var nodesShow = zTreeObj.getNodesByFilter(filterFunc); //获取匹配关键字的节点
        processShowNodes(nodesShow, _keywords);//对获取的节点进行二次处理
    }

    /**
     * 对符合条件的节点做二次处理
     */
    function processShowNodes(nodesShow,_keywords){
        if(nodesShow && nodesShow.length>0){
            //关键字不为空时对关键字节点的祖先节点进行二次处理
            if(_keywords.length>0){
                $.each(nodesShow, function(n,obj){
                    var pathOfOne = obj.getPath();//向上追溯,获取节点的所有祖先节点(包括自己)
                    if(pathOfOne && pathOfOne.length>0){
                        // i < pathOfOne.length-1, 对节点本身不再操作
                        for(var i=0;i<pathOfOne.length;i++){
                            addDiyDom(zTreeId,pathOfOne[i]);
                            zTreeObj.expandNode(pathOfOne[i],true); //展开节点
                            zTreeObj.showNode(pathOfOne[i]); //显示节点
                        }
                    }
                });
            }else{ //关键字为空则显示所有节点
                var nodesByParam = zTreeObj.getNodesByParam('level','0');//获得所有根节点
                for(var i=0;i<nodesByParam.length;i++){
                    addDiyDom(zTreeId,nodesByParam[i]);
                    zTreeObj.expandNode(nodesByParam[i],false); //展开节点
                    zTreeObj.showNode(nodesByParam[i]); //显示节点
                }

            }
        }
    }

    //监听关键字input输入框文字变化事件
    $(searchField).bind('input propertychange', function() {
        var _keywords = $(this).val();
        searchNodeLazy(_keywords); //调用延时处理
    });

    var timeoutId = null;
    // 有输入后定时执行一次，如果上次的输入还没有被执行，那么就取消上一次的执行
    function searchNodeLazy(_keywords) {
        if (timeoutId) { //如果不为空,结束任务
            clearTimeout(timeoutId);
        }
        timeoutId = setTimeout(function() {
            ztreeFilter(zTreeObj,_keywords);    //延时执行筛选方法
            $(searchField).focus();//输入框重新获取焦点
        }, 500);
    }
}