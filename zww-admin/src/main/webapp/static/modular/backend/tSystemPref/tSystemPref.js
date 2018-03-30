/**
 * 设置参数列表管理初始化
 */
var TSystemPref = {
    id: "TSystemPrefTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TSystemPref.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'code', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '参数值', field: 'value', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'modifiedDate', visible: true, align: 'center', valign: 'middle'},
            // {title: '', field: 'modifiedBy', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
TSystemPref.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TSystemPref.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加设置参数列表
 */
TSystemPref.openAddTSystemPref = function () {
    var index = layer.open({
        type: 2,
        title: '添加设置参数列表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tSystemPref/tSystemPref_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看设置参数列表详情
 */
TSystemPref.openTSystemPrefDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '设置参数列表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/tSystemPref/tSystemPref_update/' + TSystemPref.seItem.code
        });
        this.layerIndex = index;
    }
};

/**
 * 删除设置参数列表
 */
TSystemPref.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/tSystemPref/delete", function (data) {
            Feng.success("删除成功!");
            TSystemPref.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("tSystemPrefId",this.seItem.code);
        ajax.start();
    }
};

/**
 * 查询设置参数列表列表
 */
TSystemPref.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    TSystemPref.table.refresh({query: queryData});
};


//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        TSystemPref.search();
    }
});


$(function () {
    var defaultColunms = TSystemPref.initColumn();
    var table = new BSTable(TSystemPref.id, "/tSystemPref/list", defaultColunms);
    table.setPaginationType("client");
    TSystemPref.table = table.init();
});
