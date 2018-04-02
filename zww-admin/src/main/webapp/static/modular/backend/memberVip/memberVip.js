/**
 * 设置vip等级权限管理初始化
 */
var MemberVip = {
    id: "MemberVipTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MemberVip.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '等级', field: 'level', visible: true, align: 'center', valign: 'middle'},
            {title: 'VIP名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '最低额度', field: 'leastAllowed', visible: true, align: 'center', valign: 'middle'},
            {title: '包邮个数', field: 'exemptionPostageNumber', visible: true, align: 'center', valign: 'middle'},
            {title: '发货时效', field: 'deliveryTime', visible: true, align: 'center', valign: 'middle'},
            {title: '寄存时效', field: 'checkTime', visible: true, align: 'center', valign: 'middle'},
            {title: '闪电申诉', field: 'flashAppeal', visible: true, align: 'center', valign: 'middle'},
            {title: '充值折扣', field: 'discount', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MemberVip.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MemberVip.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加设置vip等级权限
 */
MemberVip.openAddMemberVip = function () {
    var index = layer.open({
        type: 2,
        title: '添加设置vip等级权限',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/memberVip/memberVip_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看设置vip等级权限详情
 */
MemberVip.openMemberVipDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '设置vip等级权限详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/memberVip/memberVip_update/' + MemberVip.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除设置vip等级权限
 */
MemberVip.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/memberVip/delete", function (data) {
            Feng.success("删除成功!");
            MemberVip.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("memberVipId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询设置vip等级权限列表
 */
MemberVip.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    MemberVip.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MemberVip.initColumn();
    var table = new BSTable(MemberVip.id, "/memberVip/list", defaultColunms);
    table.setPaginationType("client");
    MemberVip.table = table.init();
});
