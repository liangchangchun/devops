/**
 * 娃娃机主题列表管理初始化
 */
var DollTopic = {
    id: "DollTopicTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
DollTopic.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '机器号', field: 'machineCode', visible: true, align: 'center', valign: 'middle'},
            {title: '主题名称', field: 'topicName', visible: true, align: 'center', valign: 'middle'},
            {title: '娃娃机名称', field: 'dollName', visible: true, align: 'center', valign: 'middle'},
            {title: '主题类型', field: 'topicType', visible: true, align: 'center', valign: 'middle'},
            {title: '主题排序', field: 'roomType', visible: true, align: 'center', valign: 'middle'},
            {title: '房间分组', field: 'groupid', visible: true, align: 'center', valign: 'middle',
            	formatter:function (value,row,index) {  
                    if (value==0) {
                    	return "首页显示";
                    }
                    if (value==1) {
                    	return "首页不显示";
                    }
                } 
            }
    ];
};

/**
 * 检查是否选中
 */
DollTopic.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        DollTopic.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加娃娃机主题列表
 */
DollTopic.openAddDollTopic = function () {
    var index = layer.open({
        type: 2,
        title: '添加娃娃机主题列表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/dollTopic/dollTopic_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看娃娃机主题列表详情
 */
DollTopic.openDollTopicDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '娃娃机主题列表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/dollTopic/dollTopic_update/' + DollTopic.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除娃娃机主题列表
 */
DollTopic.delete = function () {
    if (this.check()) {
     	var dollTopicId = this.seItem.id;
     		layer.confirm('确认要删除吗？', {
            btn : [ '确定', '取消' ]//按钮
        }, function(index) {
       	 layer.close(index);
        var ajax = new $ax(Feng.ctxPath + "/dollTopic/delete", function (data) {
            Feng.success("删除成功!");
            DollTopic.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("dollTopicId",dollTopicId);
        ajax.start();
        }); 
    }
};

/**
 * 查询娃娃机主题列表列表
 */
DollTopic.search = function () {
    var queryData = {};
    queryData['machineCode'] = $("#machineCode").val();
    queryData['topicName'] = $("#topicName").val();
    DollTopic.table.refresh({query: queryData});
};

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        DollTopic.search();
    }
});

$(function () {
    var defaultColunms = DollTopic.initColumn();
    var table = new BSTable(DollTopic.id, "/dollTopic/list", defaultColunms);
    table.setPaginationType("server");
    DollTopic.table = table.init();
});
