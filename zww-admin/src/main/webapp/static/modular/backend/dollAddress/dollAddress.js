/**
 * 娃娃机地址列表管理初始化
 */
var DollAddress = {
    id: "DollAddressTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
DollAddress.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '机器地址', field: 'city', visible: true, align: 'center', valign: 'middle',
        	formatter:function (value,row,index) {
        		return row.province+row.city+row.county+row.street;
        	}
        },
            {title: '创建时间', field: 'createdDate', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'modifiedDate', visible: true, align: 'center', valign: 'middle'},
            {title: '是否可用', field: 'defaultFlg', visible: true, align: 'center', valign: 'middle',
            	formatter:function (value,row,index) {
            		if(value == '0'){
              			return '<span class="label label-warning radius">不可用</span>';
              		}
              		if(value == '1'){
              			return '<span class="label label-success radius">可用</span>';
              		}
            	} 
            }
    ];
};

/**
 * 检查是否选中
 */
DollAddress.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        DollAddress.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加娃娃机地址列表
 */
DollAddress.openAddDollAddress = function () {
    var index = layer.open({
        type: 2,
        title: '添加娃娃机地址列表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/dollAddress/dollAddress_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看娃娃机地址列表详情
 */
DollAddress.openDollAddressDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '娃娃机地址列表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/dollAddress/dollAddress_update/' + DollAddress.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除娃娃机地址列表
 */
DollAddress.delete = function () {
    if (this.check()) {
    	var dollAddressId = this.seItem.id;
   	 layer.confirm('确认要删除吗？', {
            btn : [ '确定', '取消' ]//按钮
        }, function(index) {
       	 layer.close(index);
        var ajax = new $ax(Feng.ctxPath + "/dollAddress/delete", function (data) {
            Feng.success("删除成功!");
            DollAddress.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("dollAddressId",dollAddressId);
        ajax.start();
        });
    }
};

/**
 * 查询娃娃机地址列表列表
 */
DollAddress.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    DollAddress.table.refresh({query: queryData});
};

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        DollAddress.search();
    }
});


$(function () {
    var defaultColunms = DollAddress.initColumn();
    var table = new BSTable(DollAddress.id, "/dollAddress/list", defaultColunms);
    table.setPaginationType("client");
    DollAddress.table = table.init();
});
