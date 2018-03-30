/**
 * 日志管理管理初始化
 */
var BusinessLog = {
    id: "BusinessLogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BusinessLog.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '日志id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '日志类型', field: 'logType', visible: true, align: 'center', valign: 'middle'},
            {title: '日志名称', field: 'logName', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '类名', field: 'className', visible: true, align: 'center', valign: 'middle'},
            {title: '方法名', field: 'method', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '是否成功', field: 'succeed', visible: true, align: 'center', valign: 'middle'},
            {title: '日志详情', field: 'message', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BusinessLog.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BusinessLog.seItem = selected[0];
        return true;
    }
};

/**
 * 查看日志详情
 */
BusinessLog.detail = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/businessLog/detail/" + this.seItem.id, function (data) {
            Feng.infoDetail("日志详情", data.regularMessage);
        }, function (data) {
            Feng.error("获取详情失败!");
        });
        ajax.start();
    }
};

// /**
//  * 点击添加日志管理
//  */
// BusinessLog.openAddBusinessLog = function () {
//     var index = layer.open({
//         type: 2,
//         title: '添加日志管理',
//         area: ['800px', '420px'], //宽高
//         fix: false, //不固定
//         maxmin: true,
//         content: Feng.ctxPath + '/businessLog/businessLog_add'
//     });
//     this.layerIndex = index;
// };
//
// /**
//  * 打开查看日志管理详情
//  */
// BusinessLog.openBusinessLogDetail = function () {
//     if (this.check()) {
//         var index = layer.open({
//             type: 2,
//             title: '日志管理详情',
//             area: ['800px', '420px'], //宽高
//             fix: false, //不固定
//             maxmin: true,
//             content: Feng.ctxPath + '/businessLog/businessLog_update/' + BusinessLog.seItem.id
//         });
//         this.layerIndex = index;
//     }
// };

/**
 * 删除日志管理
 */
BusinessLog.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/businessLog/delete", function (data) {
            Feng.success("删除成功!");
            BusinessLog.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("businessLogId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询日志管理列表
 */
BusinessLog.search = function () {
    var queryData = {};
    queryData['logName'] = $("#logName").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    queryData['logType'] = $("#logType").val();
    BusinessLog.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BusinessLog.initColumn();
    var table = new BSTable(BusinessLog.id, "/businessLog/list", defaultColunms);
    table.setPaginationType("server");
    BusinessLog.table = table.init();
});
