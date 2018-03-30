/**
 * 娃娃机主题详情列表管理初始化
 */
var TopicInfo = {
    id: "TopicInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TopicInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主题Id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '主题类型', field: 'topicType', visible: true, align: 'center', valign: 'middle'},
            {title: '主题名称', field: 'topicName', visible: true, align: 'center', valign: 'middle'},
            {title: '主题详情', field: 'details', visible: true, align: 'center', valign: 'middle'},
            {title: '排序', field: 'orderBy', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
TopicInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TopicInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加娃娃机主题详情列表
 */
TopicInfo.openAddTopicInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加娃娃机主题详情列表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/topicInfo/topicInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看娃娃机主题详情列表详情
 */
TopicInfo.openTopicInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '娃娃机主题详情列表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/topicInfo/topicInfo_update/' + TopicInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除娃娃机主题详情列表
 */
TopicInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/topicInfo/delete", function (data) {
            Feng.success("删除成功!");
            TopicInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("topicInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询娃娃机主题详情列表列表
 */
TopicInfo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    TopicInfo.table.refresh({query: queryData});
};


//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        TopicInfo.search();
    }
});

$(function () {
    var defaultColunms = TopicInfo.initColumn();
    var table = new BSTable(TopicInfo.id, "/topicInfo/list", defaultColunms);
    table.setPaginationType("server");
    TopicInfo.table = table.init();
});
