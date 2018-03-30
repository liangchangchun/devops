/**
 * 待发货列表管理初始化
 */
var GoodsDetail = {
    id: "GoodsDetailTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
GoodsDetail.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '娃娃名称', field: 'dollName', visible: true, align: 'center', valign: 'middle'},
            {title: '数量', field: 'quantity', visible: true, align: 'center', valign: 'middle'},
            {title: '娃娃编号', field: 'dollCode', visible: true, align: 'center', valign: 'middle'},
            {title: '娃娃头像', field: 'dollImg', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    return '<img src="'+value+'" width="80" class="img-rounded" />';
                }
            }

    ];
};

/**
 * 检查是否选中
 */
GoodsDetail.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        GoodsDetail.seItem = selected[0];
        return true;
    }
};



/**
 * 查询待发货列表列表
 */
GoodsDetail.search = function () {
    var queryData = {};
    queryData['orderNum'] = $("#orderNum").val();
    GoodsDetail.table.refresh({query: queryData});
};

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        GoodsDetail.search();
    }
});

$(function () {
    var defaultColunms = GoodsDetail.initColumn();
    var table = new BSTable(GoodsDetail.id, "/tDollOrder/goodsDetailLists", defaultColunms);
    table.setPaginationType("server");
    var queryData = {};
    queryData['orderNum'] = $("#orderNum").val();
    table.setQueryParams(queryData);
    GoodsDetail.table = table.init();
});
