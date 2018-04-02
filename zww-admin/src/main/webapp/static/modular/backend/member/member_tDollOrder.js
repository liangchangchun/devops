/**
 * 待发货列表管理初始化
 */
var TDollOrder = {
    id: "MemberTDollOrderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TDollOrder.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '订单号', field: 'orderNumber', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'orderDate', visible: true, align: 'center', valign: 'middle'},
            // {title: '用户序号', field: 'orderBy', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'memberIDs', visible: true, align: 'center', valign: 'middle'},
            {title: '娃娃名', field: 'dollName', visible: true, align: 'center', valign: 'middle'},
            {title: '娃娃编号', field: 'dollCodes', visible: true, align: 'center', valign: 'middle'},
            {title: '数量', field: 'quantity', visible: true, align: 'center', valign: 'middle'},
            {title: '娃娃图片', field: 'imgUrl', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    if(value == null){
                        return '<img  src="1" width="80" class="img-rounded" />'
                    }else {
                        return '<img  src="'+value+'" width="80" class="img-rounded" />';
                    }
                }
            },
            {title: '订单状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
            // {title: '寄存有效期', field: 'stockValidDate', visible: true, align: 'center', valign: 'middle'},
            // {title: '发货时间', field: 'deliverDate', visible: true, align: 'center', valign: 'middle'},
            // {title: '姓名', field: 'addrName', visible: true, align: 'center', valign: 'middle'},
            // {title: '电话', field: 'addrPhone', visible: true, align: 'center', valign: 'middle'},
            // {title: '收货地址', field: 'address', visible: true, align: 'center', valign: 'middle',
            //     formatter:function (value,row,index) {
            //         return row.province+row.city+row.county+row.street;
            //     }
            // },
            // {title: '发货方式（快递公司名称）', field: 'deliverMethod', visible: true, align: 'center', valign: 'middle'},
            // {title: '快递单号', field: 'deliverNumber', visible: true, align: 'center', valign: 'middle'},
            // {title: '邮费金额，暂不用，预留字段', field: 'deliverAmount', visible: true, align: 'center', valign: 'middle'},
            // {title: '邮费等值的游戏币', field: 'deliverCoins', visible: true, align: 'center', valign: 'middle'},
            // {title: '发货地址id', field: 'addressId', visible: true, align: 'center', valign: 'middle'},
            // {title: '申请发货时间', field: 'modifiedDate', visible: true, align: 'center', valign: 'middle'},
            // {title: '', field: 'modifiedBy', visible: true, align: 'center', valign: 'middle'},
            // {title: '', field: 'comment', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
TDollOrder.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TDollOrder.seItem = selected[0];
        return true;
    }
};


/**
 * 违规娃娃收回
 */
TDollOrder.callInDoll = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/tDollOrder/callInDoll", function (data) {
            Feng.success("操作成功!");
            TDollOrder.table.refresh();
        }, function (data) {
            Feng.error("操作失败!" + data.responseJSON.message + "!");
        });
        ajax.set("tDollOrderId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 违规娃娃兑换币
 */
TDollOrder.dollBackCoins = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/tDollOrder/dollBackCoins", function (data) {
            Feng.success("操作成功!");
            TDollOrder.table.refresh();
        }, function (data) {
            Feng.error("操作失败!" + data.responseJSON.message + "!");
        });
        ajax.set("tDollOrderId",this.seItem.id);
        ajax.set("memberId",$("#memberId").val());
        ajax.start();
    }
};

/**
 * 添加娃娃
 */
TDollOrder.backDoll = function () {
    var index = layer.open({
        type: 2,
        title: '添加娃娃',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tDollOrder/toAddDoll/'+ $("#memberId").val()
    });
    this.layerIndex = index;
};

//
// /**
//  * 删除待发货列表
//  */
// TDollOrder.delete = function () {
//     if (this.check()) {
//         var ajax = new $ax(Feng.ctxPath + "/tDollOrder/delete", function (data) {
//             Feng.success("删除成功!");
//             TDollOrder.table.refresh();
//         }, function (data) {
//             Feng.error("删除失败!" + data.responseJSON.message + "!");
//         });
//         ajax.set("tDollOrderId",this.seItem.id);
//         ajax.start();
//     }
// };

/**
 * 查询寄存中列表列表
 */
TDollOrder.search = function () {
    var queryData = {};
    queryData['phone'] = $("#phone").val();
    queryData['memberId'] = $("#memberId").val();
    TDollOrder.table.refresh({query: queryData});
};

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        TDollOrder.search();
    }
});


$(function () {
    var defaultColunms = TDollOrder.initColumn();
    var table = new BSTable(TDollOrder.id, "/member/goodsDetail/list", defaultColunms);
    table.setPaginationType("server");
    var queryData = {};
    queryData['memberId'] = $("#memberId").val();
    table.setQueryParams(queryData);
    TDollOrder.table = table.init();
});
