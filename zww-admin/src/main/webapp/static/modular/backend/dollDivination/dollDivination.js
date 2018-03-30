/**
 * 机器占卜主题管理初始化
 */
var DollDivination = {
    id: "DollDivinationTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
DollDivination.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '机器id', field: 'dollId', visible: true, align: 'center', valign: 'middle'},
            {title: '机器名', field: 'dollName', visible: true, align: 'center', valign: 'middle'},
            {title: '机器号', field: 'dollCode', visible: true, align: 'center', valign: 'middle'},
            {title: '机器图', field: 'dollUrl', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    return '<img  src="'+value+'" width="100" class="picture-thumb" />';
                }
            },
            {title: '占卜主题id', field: 'divinationId', visible: true, align: 'center', valign: 'middle'},
            {title: '占卜主题名', field: 'divinationName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
DollDivination.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        DollDivination.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加机器占卜主题
 */
DollDivination.openAddDollDivination = function () {
    var index = layer.open({
        type: 2,
        title: '添加机器占卜主题',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/dollDivination/dollDivination_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看机器占卜主题详情
 */
DollDivination.openDollDivinationDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '机器占卜主题详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/dollDivination/dollDivination_update/' + DollDivination.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除机器占卜主题
 */
DollDivination.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/dollDivination/delete", function (data) {
            Feng.success("删除成功!");
            DollDivination.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("dollDivinationId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询机器占卜主题列表
 */
DollDivination.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    DollDivination.table.refresh({query: queryData});
};


//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        DollDivination.search();
    }
});

$(function () {
    var defaultColunms = DollDivination.initColumn();
    var table = new BSTable(DollDivination.id, "/dollDivination/list", defaultColunms);
    table.setPaginationType("server");
    DollDivination.table = table.init();
});
