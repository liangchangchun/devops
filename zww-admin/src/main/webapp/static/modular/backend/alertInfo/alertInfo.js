/**
 * 抓取弹窗列表管理初始化
 */
var AlertInfo = {
    id: "AlertInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
AlertInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '弹窗图片', field: 'alertImgUrl', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    return '<a class="maincolor" href="javascript:;" onclick="AlertInfo.upload(\'抓取结果图片\',\'alertInfo\/toUpload\','+row.id+',\'\',\'350\')"><img  src="'+value+'" width="80" class="img-rounded" /></a>';
                }
            },
            {title: '描述', field: 'description', visible: true, align: 'center', valign: 'middle'},
            {title: '超链接', field: 'hyperlink', visible: true, align: 'center', valign: 'middle'},
            {title: '链接类型', field: 'linkType', visible: true, align: 'center', valign: 'middle'},
            {title: '排序', field: 'sorts', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createdDate', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createdBy', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'modifiedDate', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人', field: 'modifiedBy', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
AlertInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        AlertInfo.seItem = selected[0];
        return true;
    }
};



/**
 * 图片上传
 */
AlertInfo.upload = function (title,url,id,w,h) {
    //alert("upload:"+title+",url="+url+",id="+id);
    url = url+"?id="+id;
    layer_show(title,url,w,h);
}


/**
 * 点击添加抓取弹窗列表
 */
AlertInfo.openAddAlertInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加抓取弹窗列表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/alertInfo/alertInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看抓取弹窗列表详情
 */
AlertInfo.openAlertInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '抓取弹窗列表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/alertInfo/alertInfo_update/' + AlertInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除抓取弹窗列表
 */
AlertInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/alertInfo/delete", function (data) {
            Feng.success("删除成功!");
            AlertInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("alertInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询抓取弹窗列表列表
 */
AlertInfo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    AlertInfo.table.refresh({query: queryData});
};

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        AlertInfo.search();
    }
});

$(function () {
    var defaultColunms = AlertInfo.initColumn();
    var table = new BSTable(AlertInfo.id, "/alertInfo/list", defaultColunms);
    table.setPaginationType("client");
    AlertInfo.table = table.init();
});
