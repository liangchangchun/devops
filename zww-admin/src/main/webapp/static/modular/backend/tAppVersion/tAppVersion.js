/**
 * 版本管理管理初始化
 */
var TAppVersion = {
    id: "TAppVersionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TAppVersion.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '系统', field: 'appKey', visible: true, align: 'center', valign: 'middle'},
            {title: '版本号', field: 'version', visible: true, align: 'center', valign: 'middle'},
            {title: '链接', field: 'upgradeUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '更新描述', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '是否强制更新', field: 'forceUpdate', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    if(value == 1){
                        return '强制更新';
                    }else {
                        return '建议更新';
                    }
                }
            },
            {title: '是否启用更新', field: 'openUpdate', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    if(value == 2){
                        return '关闭';
                    }else {
                        return '开启';
                    }
                }
            },
            // {title: '生效', field: 'hideFlg', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
TAppVersion.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TAppVersion.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加版本管理
 */
TAppVersion.openAddTAppVersion = function () {
    var index = layer.open({
        type: 2,
        title: '添加版本管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tAppVersion/tAppVersion_add'
    });
    this.layerIndex = index;
};

/**
 * 点击上传apk
 */
TAppVersion.uploadAPK = function () {
    if (this.check()) {
        if(TAppVersion.seItem.appKey == 'android' || TAppVersion.seItem.appKey == 'androidxyj'){
            var index = layer.open({
                type: 2,
                title: '上传apk',
                area: ['800px', '420px'], //宽高
                fix: false, //不固定
                maxmin: true,
                content: Feng.ctxPath + '/tAppVersion/uploadAPK/' + TAppVersion.seItem.appKey
            });
            this.layerIndex = index;
        } else {
            alert('请选择安卓系统');
        }

    }

};

/**
 * 打开查看版本管理详情
 */
TAppVersion.openTAppVersionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '版本管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/tAppVersion/tAppVersion_update/' + TAppVersion.seItem.appKey
        });
        this.layerIndex = index;
    }
};

/**
 * 删除版本管理
 */
TAppVersion.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/tAppVersion/delete", function (data) {
            Feng.success("删除成功!");
            TAppVersion.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("tAppVersionId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询版本管理列表
 */
TAppVersion.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    TAppVersion.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = TAppVersion.initColumn();
    var table = new BSTable(TAppVersion.id, "/tAppVersion/list", defaultColunms);
    table.setPaginationType("client");
    TAppVersion.table = table.init();
});
