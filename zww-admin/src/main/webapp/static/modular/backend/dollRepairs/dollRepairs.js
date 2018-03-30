/**
 * 报修列表管理初始化
 */
var DollRepairs = {
    id: "DollRepairsTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
DollRepairs.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            // {title: '用户id', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'memberID', visible: true, align: 'center', valign: 'middle'},
            {title: '用户名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '机器id', field: 'dollId', visible: true, align: 'center', valign: 'middle'},
            {title: '机器名', field: 'dollName', visible: true, align: 'center', valign: 'middle'},
            {title: '机器图片', field: 'tbimgRealPath', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    return '<img  src="'+value+'" width="80" class="img-rounded" />';
                }
            },
            {title: '机器号', field: 'machineCode', visible: true, align: 'center', valign: 'middle'},
            {title: '机器状态', field: 'machineStatus', visible: true, align: 'center', valign: 'middle'},
            {title: '机器地址', field: 'dollAddress', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    return row.province+row.city+row.county+row.street;
                }
            },
            {title: '报修原因', field: 'repairsReason', visible: true, align: 'center', valign: 'middle'},
            {title: '报修时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'},
            // {title: '修改时间', field: 'modifiedDate', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
DollRepairs.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        DollRepairs.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加报修列表
 */
DollRepairs.openAddDollRepairs = function () {
    var index = layer.open({
        type: 2,
        title: '添加报修列表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/dollRepairs/dollRepairs_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看报修列表详情
 */
DollRepairs.openDollRepairsDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '报修列表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/dollRepairs/dollRepairs_update/' + DollRepairs.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除报修列表
 */
DollRepairs.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/dollRepairs/delete", function (data) {
            Feng.success("删除成功!");
            DollRepairs.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("dollRepairsId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询报修列表列表
 */
DollRepairs.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    DollRepairs.table.refresh({query: queryData});
};

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        DollRepairs.search();
    }
});


$(function () {
    var defaultColunms = DollRepairs.initColumn();
    var table = new BSTable(DollRepairs.id, "/dollRepairs/list", defaultColunms);
    table.setPaginationType("server");
    DollRepairs.table = table.init();
});
