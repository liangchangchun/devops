/**
 * 娃娃机概率列表管理初始化
 */
var MachineProbability = {
    id: "MachineProbabilityTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MachineProbability.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '充值金额阈值', field: 'probabilityRulesId', visible: true, align: 'center', valign: 'middle'},
            {title: '机器id', field: 'dollId', visible: true, align: 'center', valign: 'middle'},
            {title: '机器号', field: 'machainCode', visible: true, align: 'center', valign: 'middle'},
            {title: '机器名', field: 'dollName', visible: true, align: 'center', valign: 'middle'},
            {title: '未充值概率', field: 'probability1', visible: true, align: 'center', valign: 'middle'},
            {title: '充值小于阈值概率', field: 'probability2', visible: true, align: 'center', valign: 'middle'},
            {title: '充值大于阈值概率', field: 'probability3', visible: true, align: 'center', valign: 'middle'},
            {title: '概率基数', field: 'baseNum', visible: true, align: 'center', valign: 'middle'},
            // {title: '创建时间', field: 'createdDate', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'modifiedDate', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    if(value == null){
                        return '--';
                    }else {
                        return value;
                    }

                }
            },
            // {title: '创建人', field: 'createdBy', visible: true, align: 'center', valign: 'middle'},
            // {title: '修改人', field: 'modifiedBy', visible: true, align: 'center', valign: 'middle'}
             {title: '操作人', field: 'person', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MachineProbability.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MachineProbability.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加娃娃机概率列表
 */
MachineProbability.openAddMachineProbability = function () {
    var index = layer.open({
        type: 2,
        title: '添加娃娃机概率列表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/machineProbability/machineProbability_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看娃娃机概率列表详情
 */
MachineProbability.openMachineProbabilityDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '娃娃机概率列表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/machineProbability/machineProbability_update/' + MachineProbability.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除娃娃机概率列表
 */
MachineProbability.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/machineProbability/delete", function (data) {
            Feng.success("删除成功!");
            MachineProbability.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("machineProbabilityId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询娃娃机概率列表列表
 */
MachineProbability.search = function () {
    var queryData = {};
    queryData['dollId'] = $("#dollId").val();
    queryData['machainCode'] = $("#machainCode").val();
    queryData['name'] = $("#name").val();
    MachineProbability.table.refresh({query: queryData});
};

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        MachineProbability.search();
    }
});

$(function () {
    var defaultColunms = MachineProbability.initColumn();
    var table = new BSTable(MachineProbability.id, "/machineProbability/list", defaultColunms);
    table.setPaginationType("server");
    MachineProbability.table = table.init();
});
