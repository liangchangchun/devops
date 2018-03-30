/**
 * 用户白名单列表管理初始化
 */
var MemberWhiteList = {
    id: "MemberWhiteListTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MemberWhiteList.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户序号', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'memberId', visible: true, align: 'center', valign: 'middle'},
            {title: '用户名', field: 'userName', visible: true, align: 'center', valign: 'middle'},
            {title: '用户IP', field: 'userIP', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'states', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createdDate', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'modifiedDate', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MemberWhiteList.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MemberWhiteList.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户白名单列表
 */
MemberWhiteList.openAddMemberWhiteList = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户白名单列表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/memberWhiteList/memberWhiteList_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户白名单列表详情
 */
MemberWhiteList.openMemberWhiteListDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户白名单列表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/memberWhiteList/memberWhiteList_update/' + MemberWhiteList.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户白名单列表
 */
MemberWhiteList.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/memberWhiteList/delete", function (data) {
            Feng.success("删除成功!");
            MemberWhiteList.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("memberWhiteListId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询用户白名单列表列表
 */
MemberWhiteList.search = function () {
    var queryData = {};
    queryData['memberId'] = $("#memberId").val();
    queryData['userName'] = $("#userName").val();
    MemberWhiteList.table.refresh({query: queryData});
};

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        MemberWhiteList.search();
    }
});

$(function () {
    var defaultColunms = MemberWhiteList.initColumn();
    var table = new BSTable(MemberWhiteList.id, "/memberWhiteList/list", defaultColunms);
    table.setPaginationType("server");
    MemberWhiteList.table = table.init();
});
