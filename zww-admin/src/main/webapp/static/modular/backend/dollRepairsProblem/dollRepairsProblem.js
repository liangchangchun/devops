/**
 * 设置申诉问题列表管理初始化
 */
var DollRepairsProblem = {
    id: "DollRepairsProblemTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
DollRepairsProblem.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '报修问题', field: 'problem', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
DollRepairsProblem.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        DollRepairsProblem.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加设置申诉问题列表
 */
DollRepairsProblem.openAddDollRepairsProblem = function () {
    var index = layer.open({
        type: 2,
        title: '添加设置申诉问题列表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/dollRepairsProblem/dollRepairsProblem_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看设置申诉问题列表详情
 */
DollRepairsProblem.openDollRepairsProblemDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '设置申诉问题列表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/dollRepairsProblem/dollRepairsProblem_update/' + DollRepairsProblem.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除设置申诉问题列表
 */
DollRepairsProblem.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/dollRepairsProblem/delete", function (data) {
            Feng.success("删除成功!");
            DollRepairsProblem.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("dollRepairsProblemId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询设置申诉问题列表列表
 */
DollRepairsProblem.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    DollRepairsProblem.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = DollRepairsProblem.initColumn();
    var table = new BSTable(DollRepairsProblem.id, "/dollRepairsProblem/list", defaultColunms);
    table.setPaginationType("client");
    DollRepairsProblem.table = table.init();
});
