/**
 * 充值规则列表管理初始化
 */
var TChargeRules = {
    id: "TChargeRulesTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TChargeRules.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            // {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '排序', field: 'orderby', visible: true, align: 'center', valign: 'middle'},
            {title: '规则名称', field: 'chargeName', visible: true, align: 'center', valign: 'middle'},
            {title: '价格', field: 'chargePrice', visible: true, align: 'center', valign: 'middle'},
            {title: '娃娃币', field: 'coinsCharge', visible: true, align: 'center', valign: 'middle'},
            {title: '赠送娃娃币', field: 'coinsOffer', visible: true, align: 'center', valign: 'middle'},
            {title: '钻石', field: 'superTicketCharge', visible: true, align: 'center', valign: 'middle'},
            // {title: '赠送钻石', field: 'superTicketOffer', visible: true, align: 'center', valign: 'middle'}
            {title: '首次娃娃币', field: 'cionsFirst', visible: true, align: 'center', valign: 'middle'},
            {title: '折扣', field: 'discount', visible: true, align: 'center', valign: 'middle'},
            {title: '说明文字', field: 'description', visible: true, align: 'center', valign: 'middle'},
            {title: '期限', field: 'chargeDateLimit', visible: true, align: 'center', valign: 'middle'},
            // {title: '创建日期', field: 'createdDate', visible: true, align: 'center', valign: 'middle'},
            // {title: '创建人id', field: 'createdBy', visible: true, align: 'center', valign: 'middle'},
            // {title: '修改日期', field: 'modifiedDate', visible: true, align: 'center', valign: 'middle'},
            // {title: '修改人id', field: 'modifiedBy', visible: true, align: 'center', valign: 'middle'},
            // {title: '充值类型', field: 'chargeType', visible: true, align: 'center', valign: 'middle'},
            // {title: '限购次数,-1为无限次', field: 'chargeTimesLimit', visible: true, align: 'center', valign: 'middle'},
            {title: '规则状态', field: 'rulesStatus', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    if(value == 1){
                        return '<span class="label label-success radius">上架</span>';
                    }else{
                        return '<span class="label label-danger radius">下架</span>';
                    }
                }
            },
            // {title: '规则状态', field: 'rulesStatuses', visible: true, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
TChargeRules.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TChargeRules.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加充值规则列表
 */
TChargeRules.openAddTChargeRules = function () {
    var index = layer.open({
        type: 2,
        title: '添加充值规则列表',
        area: ['900px', '520px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tChargeRules/tChargeRules_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看充值规则列表详情
 */
TChargeRules.openTChargeRulesDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '充值规则列表详情',
            area: ['900px', '520px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/tChargeRules/tChargeRules_update/' + TChargeRules.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除充值规则列表
 */
TChargeRules.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/tChargeRules/delete", function (data) {
            Feng.success("删除成功!");
            TChargeRules.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("tChargeRulesId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询充值规则列表列表
 */
TChargeRules.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    TChargeRules.table.refresh({query: queryData});
};

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        TChargeRules.search();
    }
});

$(function () {
    var defaultColunms = TChargeRules.initColumn();
    var table = new BSTable(TChargeRules.id, "/tChargeRules/list", defaultColunms);
    table.setPaginationType("server");
    TChargeRules.table = table.init();
});
