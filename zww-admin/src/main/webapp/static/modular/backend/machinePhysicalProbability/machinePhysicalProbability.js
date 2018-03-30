/**
 * 机器物理概率管理初始化
 */
var MachinePhysicalProbability = {
    id: "MachinePhysicalProbabilityTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MachinePhysicalProbability.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '机器id', field: 'dollId', visible: true, align: 'center', valign: 'middle'},
            {title: '机器名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '机器号', field: 'machineCode', visible: true, align: 'center', valign: 'middle'},
            {title: '强力电压', field: 'strongVoltage', visible: true, align: 'center', valign: 'middle'},
            {title: '弱一电压', field: 'weakOneVoltage', visible: true, align: 'center', valign: 'middle'},
            {title: '弱二电压', field: 'weakTwoVoltage', visible: true, align: 'center', valign: 'middle'},
            {title: '强力时间', field: 'strongTime', visible: true, align: 'center', valign: 'middle'},
            {title: '弱一时间', field: 'weakTime', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createdDate', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createdBy', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'modifiedDate', visible: true, align: 'center', valign: 'middle'},
            {title: '操作人', field: 'person', visible: true, align: 'center', valign: 'middle'}
            // {title: '修改人', field: 'modifiedBy', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MachinePhysicalProbability.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MachinePhysicalProbability.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加机器物理概率
 */
MachinePhysicalProbability.openAddMachinePhysicalProbability = function () {
    var index = layer.open({
        type: 2,
        title: '添加机器物理概率',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/machinePhysicalProbability/machinePhysicalProbability_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看机器物理概率详情
 */
MachinePhysicalProbability.openMachinePhysicalProbabilityDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '机器物理概率详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/machinePhysicalProbability/machinePhysicalProbability_update/' + MachinePhysicalProbability.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除机器物理概率
 */
MachinePhysicalProbability.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/machinePhysicalProbability/delete", function (data) {
            Feng.success("删除成功!");
            MachinePhysicalProbability.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("machinePhysicalProbabilityId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询机器物理概率列表
 */
MachinePhysicalProbability.search = function () {
    var queryData = {};
    queryData['dollId'] = $("#dollId").val();
    queryData['machainCode'] = $("#machainCode").val();
    queryData['name'] = $("#name").val();
    MachinePhysicalProbability.table.refresh({query: queryData});
};

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        MachinePhysicalProbability.search();
    }
});

$(function () {
    var defaultColunms = MachinePhysicalProbability.initColumn();
    var table = new BSTable(MachinePhysicalProbability.id, "/machinePhysicalProbability/list", defaultColunms);
    table.setPaginationType("server");
    MachinePhysicalProbability.table = table.init();
});
