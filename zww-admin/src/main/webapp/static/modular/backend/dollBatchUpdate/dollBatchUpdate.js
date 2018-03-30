/**
 * 娃娃机批量上下线管理初始化
 */
var DollBatchUpdate = {
    id: "DollBatchUpdateTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
DollBatchUpdate.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '机器id', field: 'dollId', visible: true, align: 'center', valign: 'middle'},
            {title: '机器名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '机器号', field: 'machineCode', visible: true, align: 'center', valign: 'middle'},
            {title: '封面', field: 'tbimgRealPath', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    return '<a class="maincolor" href="javascript:;" onclick="TDoll.upload(\'广告头像\',\'tDoll\/toUpload\','+row.id+',\'\',\'350\')"><img  src="'+value+'" width="80" class="img-rounded" /></a>';
                }
            },
            {title: '状态', field: 'machineStatus', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    if(value == '空闲中'){
                        return '<span class="label label-success radius">'+value+'</span>';
                    }
                    if(value == '游戏中'){
                        return '<span class="label label-warning radius">'+value+'</span>';
                    }
                    if(value != '游戏中' && value != '空闲中'){
                        return '<span class="label label-danger radius">'+value+'</span>';
                    }
                }
            },
            {title: '机器类型', field: 'machineType', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    if (value=="0")return '普通房';
                    if (value=="1")return '练习房';
                    if (value=="2")return '钻石房';
                    if (value=="3")return '占卜房';
                }
            },
            {title: '机器地址', field: 'adress', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    return row.province+row.city+row.county+row.street;
                }
            },
    ];
};

/**
 * 检查是否选中
 */
DollBatchUpdate.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        DollBatchUpdate.seItem = selected[0];//单个
        DollBatchUpdate.seItems = selected;//多个
        return true;
    }
};

/**
 * 点击添加娃娃机批量上下线
 */
DollBatchUpdate.openAddDollBatchUpdate = function () {
    var index = layer.open({
        type: 2,
        title: '添加娃娃机批量上下线',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/dollBatchUpdate/dollBatchUpdate_add'
    });
    this.layerIndex = index;
};

/**
 * 批量空闲中
 */
DollBatchUpdate.kongXian = function () {
    if (this.check()) {
        var ids = '';
        for (var sitem in this.seItems){
            var val = this.seItems[sitem].id;
            ids += val + ',';
            // alert(ids)
        }
        var ajax = new $ax(Feng.ctxPath + "/dollBatchUpdate/kongXian", function (data) {
            Feng.success("操作成功!");
            DollBatchUpdate.table.refresh();
        }, function (data) {
            Feng.error("操作失败!" + data.responseJSON.message + "!");
        });
        ajax.set("dollBatchUpdateId",ids);
        ajax.start();
    }
};

/**
 * 批量未上线
 */
DollBatchUpdate.weiShangXian = function () {
    if (this.check()) {
        var ids = '';
        for (var sitem in this.seItems){
            var val = this.seItems[sitem].id;
            ids += val + ',';
            // alert(ids)
        }
        var ajax = new $ax(Feng.ctxPath + "/dollBatchUpdate/weiShangXian", function (data) {
            Feng.success("操作成功!");
            DollBatchUpdate.table.refresh();
        }, function (data) {
            Feng.error("操作失败!" + data.responseJSON.message + "!");
        });
        ajax.set("dollBatchUpdateId",ids);
        ajax.start();
    }
};


/**
 * 打开查看娃娃机批量上下线详情
 */
// DollBatchUpdate.openDollBatchUpdateDetail = function () {
//     if (this.check()) {
//         var index = layer.open({
//             type: 2,
//             title: '娃娃机批量上下线详情',
//             area: ['800px', '420px'], //宽高
//             fix: false, //不固定
//             maxmin: true,
//             content: Feng.ctxPath + '/dollBatchUpdate/dollBatchUpdate_update/' + DollBatchUpdate.seItem.id
//         });
//         this.layerIndex = index;
//     }
// };

/**
 * 删除娃娃机批量上下线
 */
DollBatchUpdate.delete = function () {
    if (this.check()) {
        var ids = '';
        for (var sitem in this.seItems){
            var val = this.seItems[sitem].id;
            ids += val + ',';
            // alert(ids)
        }
        layer.confirm('确认要删除吗？', {
            btn : [ '确定', '取消' ]//按钮
        }, function(index) {
            var ajax = new $ax(Feng.ctxPath + "/dollBatchUpdate/delete", function (data) {
                Feng.success("删除成功!");
                DollBatchUpdate.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("dollBatchUpdateId",ids);
            ajax.start();
        });
    }
};

/**
 * 查询娃娃机批量上下线列表
 */
DollBatchUpdate.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['machineCode'] = $("#machineCode").val();
    DollBatchUpdate.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = DollBatchUpdate.initColumn();
    var table = new BSTable(DollBatchUpdate.id, "/dollBatchUpdate/list", defaultColunms);
    table.setPaginationType("server");
    DollBatchUpdate.table = table.init();
});

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        DollBatchUpdate.search();
    }
});
