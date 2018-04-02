/**
 * 第三方接入管理初始化
 */
var Thirdparty = {
    id: "ThirdpartyTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Thirdparty.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: 'appkey', field: 'appkey', visible: true, align: 'center', valign: 'middle'},
            {title: '秘钥', field: 'passwordKey', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '类型', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createdDate', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'modifiedDate', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'description', visible: true, align: 'center', valign: 'middle'},
            {title: '是否启用', field: 'startUsing', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    if (value==1){
                        return "启用";
                    } else {
                        return "未启用";
                    }
                }
            }
    ];
};

/**
 * 检查是否选中
 */
Thirdparty.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Thirdparty.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加第三方接入
 */
Thirdparty.openAddThirdparty = function () {
    var index = layer.open({
        type: 2,
        title: '添加第三方接入',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/thirdparty/thirdparty_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看第三方接入详情
 */
Thirdparty.openThirdpartyDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '第三方接入详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/thirdparty/thirdparty_update/' + Thirdparty.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除第三方接入
 */
Thirdparty.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/thirdparty/delete", function (data) {
            Feng.success("删除成功!");
            Thirdparty.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("thirdpartyId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询第三方接入列表
 */
Thirdparty.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Thirdparty.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Thirdparty.initColumn();
    var table = new BSTable(Thirdparty.id, "/thirdparty/list", defaultColunms);
    table.setPaginationType("server");
    Thirdparty.table = table.init();
});
