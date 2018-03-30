/**
 * 邀请人列表管理初始化
 */
var ShareInvite = {
    id: "ShareInviteTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ShareInvite.initColumn = function () {
    return [
            {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户ID', field: 'inviteCode', visible: true, align: 'center', valign: 'middle'},
            {title: '昵称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '电话', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
            {title: '性别', field: 'genderName', visible: true, align: 'center', valign: 'middle'},
            {title: '邀请人数', field: 'inviteNum', visible: true, align: 'center', valign: 'middle'},
            {title: '注册时间', field: 'registerDate', visible: true, align: 'center', valign: 'middle'},
            {title: '被邀请时间', field: 'invitedTime', visible: true, align: 'center', valign: 'middle'},
            {title: '最近登录时间', field: 'lastLoginDate', visible: true, align: 'center', valign: 'middle'},
            {title: '是否在线', field: 'onlineFlg', visible: true, align: 'center', valign: 'middle',
            	formatter:function (value,row,index) {  
                    if (value) {
                    	return "在线";
                    }
                    else {
                    	return "不在线";
                    }
                }
            }
    ];
};

/**
 * 检查是否选中
 */
ShareInvite.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ShareInvite.seItem = selected[0];
        return true;
    }
};

/**
 * 打开查看充值记录详情
 */
ShareInvite.openMemberChargeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '充值记录详情',
            area: ['900px', '520px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/member/chargeDetail/' + ShareInvite.seItem.id

        });
        this.layerIndex = index;
    }
};

/**
 * 查询邀请人列表列表
 */
ShareInvite.search = function () {
    var queryData = {};
    queryData['memberid'] = $("#memberid").val();
    queryData['name'] = $("#name").val();
    queryData['startDate'] = $("#startDate").val();
    queryData['endDate'] = $("#endDate").val();
    queryData['inviteType'] = $("#inviteType").val();
    ShareInvite.table.refresh({query: queryData});
};

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        ShareInvite.search();
    }
});


$(function () {
    var defaultColunms = ShareInvite.initColumn();
    var table = new BSTable(ShareInvite.id, "/shareInvite/list", defaultColunms);
    table.setPaginationType("server");
    ShareInvite.table = table.init();
});
