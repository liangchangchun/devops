/**
 * 待发货列表管理初始化
 */
var TDollOrderOut = {
    id: "TDollOrderOutTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TDollOrderOut.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '订单号', field: 'orderNumber', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    return '<a href="javascript:;" onclick="TDollOrderOut.goodsDetails(\'已发货订单详情\',\'/tDollOrder\/goodsDetail\',\''+value+'\',\'300\',\'450\')">'+value+'</a>';
                }
            },
            // {title: '创建时间', field: 'orderDate', visible: true, align: 'center', valign: 'middle'},
            // {title: '用户序号', field: 'orderBy', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'memberIDs', visible: true, align: 'center', valign: 'middle'},
            // {title: '订单状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
            // {title: '寄存有效期', field: 'stockValidDate', visible: true, align: 'center', valign: 'middle'},
            {title: '姓名', field: 'addrName', visible: true, align: 'center', valign: 'middle'},
            {title: '电话', field: 'addrPhone', visible: true, align: 'center', valign: 'middle'},
            {title: '收货地址', field: 'address', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    return row.province+row.city+row.county+row.street;
                }
            },
            {title: '快递公司', field: 'deliverMethod', visible: true, align: 'center', valign: 'middle'},
            {title: '快递单号', field: 'deliverNumber', visible: true, align: 'center', valign: 'middle'},
            {title: '发货时间', field: 'deliverDate', visible: true, align: 'center', valign: 'middle'},
            // {title: '邮费金额，暂不用，预留字段', field: 'deliverAmount', visible: true, align: 'center', valign: 'middle'},
            // {title: '邮费等值的游戏币', field: 'deliverCoins', visible: true, align: 'center', valign: 'middle'},
            // {title: '发货地址id', field: 'addressId', visible: true, align: 'center', valign: 'middle'},
            {title: '申请发货时间', field: 'modifiedDate', visible: true, align: 'center', valign: 'middle'},
            // {title: '发货人', field: 'modifiedBy', visible: true, align: 'center', valign: 'middle'},
            // {title: '', field: 'comment', visible: true, align: 'center', valign: 'middle'}
    ];
};


/**
 * 检查是否选中
 */
TDollOrderOut.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TDollOrderOut.seItem = selected[0];
        return true;
    }
};

/**
 * 订单详情
 */
TDollOrderOut.goodsDetails = function (title,url,orderUrl,w,h) {
    url = Feng.ctxPath + url+"?orderNum="+orderUrl;
    layer_show(title,url,800,400);

}




/**
 * 查询待发货列表列表
 */
TDollOrderOut.search = function () {
    var queryData = {};
    queryData['phone'] = $("#phone").val();
    queryData['memberId'] = $("#memberId").val();
    TDollOrderOut.table.refresh({query: queryData});
};

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        TDollOrderOut.search();
    }
});


$(function () {
    var defaultColunms = TDollOrderOut.initColumn();
    var table = new BSTable(TDollOrderOut.id, "/tDollOrder/outList", defaultColunms);
    table.setPaginationType("server");
    TDollOrderOut.table = table.init();
});
