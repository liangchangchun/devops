/**
 * orderManage管理初始化
 */
var ChargeOrder = {
    id: "ChargeOrderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ChargeOrder.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '订单编号', field: 'orderNo', visible: true, align: 'center', valign: 'middle'},
            // {title: '充值规则id', field: 'chargeruleid', visible: true, align: 'center', valign: 'middle'},
            {title: '充值规则名称', field: 'chargeName', visible: true, align: 'center', valign: 'middle'},
            {title: '充值金额', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'memberId', visible: false, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'memberIDs', visible: true, align: 'center', valign: 'middle'},
            {title: '用户名称', field: 'memberName', visible: true, align: 'center', valign: 'middle'},
            // {title: '充值类型', field: 'chargeType', visible: true, align: 'center', valign: 'middle'},
            {title: '订单状态', field: 'chargeState', visible: false, align: 'center', valign: 'middle'},
            {title: '订单状态', field: 'chargeStates', visible: true, align: 'center', valign: 'middle'},
            {title: '充值前', field: 'coinsBefore', visible: true, align: 'center', valign: 'middle'},
            {title: '充值后', field: 'coinsAfter', visible: true, align: 'center', valign: 'middle'},
            {title: '充值', field: 'coinsCharge', visible: true, align: 'center', valign: 'middle'},
            {title: '赠送', field: 'coinsOffer', visible: true, align: 'center', valign: 'middle'},
            {title: '充值时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'},
            // {title: '订单更新时间', field: 'updateDate', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ChargeOrder.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ChargeOrder.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加orderManage
 */
ChargeOrder.openAddChargeOrder = function () {
    var index = layer.open({
        type: 2,
        title: '添加orderManage',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/chargeOrder/chargeOrder_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看orderManage详情
 */
ChargeOrder.openChargeOrderDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'orderManage详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/chargeOrder/chargeOrder_update/' + ChargeOrder.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除orderManage
 */
ChargeOrder.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/chargeOrder/delete", function (data) {
            Feng.success("删除成功!");
            ChargeOrder.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("chargeOrderId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 总金额
 */
ChargeOrder.loadMoney = function(){
    var ajax = new $ax(Feng.ctxPath + "/chargeOrder/allMoney", function (data) {
        $("#allMoney").val(data);
        // ChargeOrder.table.refresh();
    }, function (data) {
        Feng.error("失败!" + data.responseJSON.message + "!");
    });
    ajax.set("memberName",$("#memberName").val());
    ajax.set("memberId",$("#memberId").val());
    ajax.set("comboNames",$("#comboNames").val());
    ajax.set("chargeState",$("#chargeState").val());
    ajax.set("registeDate",$("#registeDate").val());
    ajax.set("endtime",$("#endtime").val());
    ajax.start();
};



/**
 * 总人数
 */
ChargeOrder.loadPerson = function(){
    var ajax = new $ax(Feng.ctxPath + "/chargeOrder/allPerson", function (data) {
        $("#allPerson").val(data);
        // ChargeOrder.table.refresh();
    }, function (data) {
        Feng.error("失败!" + data.responseJSON.message + "!");
    });
    ajax.set("memberName",$("#memberName").val());
    ajax.set("memberId",$("#memberId").val());
    ajax.set("comboNames",$("#comboNames").val());
    ajax.set("chargeState",$("#chargeState").val());
    ajax.set("registeDate",$("#registeDate").val());
    ajax.set("endtime",$("#endtime").val());
    ajax.start();
};
/**
 * 查询orderManage列表
 */
ChargeOrder.search = function () {
    var queryData = {};
    queryData['memberName'] = $("#memberName").val();
    queryData['memberId'] = $("#memberId").val();
    queryData['chargeruleid'] = $("#comboNames").val();
    queryData['chargeState'] = $("#chargeState").val();
    queryData['registeDate'] = $("#registeDate").val();
    queryData['endtime'] = $("#endtime").val();
    ChargeOrder.loadMoney();
    ChargeOrder.loadPerson();
    ChargeOrder.table.refresh({query: queryData});
};

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        ChargeOrder.search();
    }
});

$(function () {

    var defaultColunms = ChargeOrder.initColumn();
    var table = new BSTable(ChargeOrder.id, "/chargeOrder/list", defaultColunms);
    table.setPaginationType("server");
    var queryData = {};
    queryData['chargeState'] = 1;
    table.setQueryParams(queryData);
    ChargeOrder.loadMoney();
    ChargeOrder.loadPerson();
    ChargeOrder.table = table.init();
});
