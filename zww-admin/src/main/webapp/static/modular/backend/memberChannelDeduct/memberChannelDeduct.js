/**
 * 渠道扣量管理初始化
 */
var MemberChannelDeduct = {
    id: "MemberChannelDeductTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MemberChannelDeduct.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: '注册渠道', field: 'registerChannel', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    if (value == null || value == ''){
                        return '--';
                    }else {
                        return value;
                    }
                }
            },
            {title: '登陆渠道', field: 'loginChannel', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    if (value == null || value == ''){
                        return '--';
                    }else {
                        return value;
                    }
                }
            },
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户id', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '用户编号', field: 'memberID', visible: true, align: 'center', valign: 'middle'},
            {title: '用户名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
            // {title: '微信id', field: 'weixinId', visible: true, align: 'center', valign: 'middle'},
            {title: '性别', field: 'gender', visible: true, align: 'center', valign: 'middle'},
            {title: '手机机型', field: 'phoneModel', visible: true, align: 'center', valign: 'middle'},
            {title: '注册时间', field: 'registerDate', visible: true, align: 'center', valign: 'middle'},
            {title: '最近登录时间', field: 'lastLoginDate', visible: true, align: 'center', valign: 'middle'},
            {title: '注册设备', field: 'registerFrom', visible: true, align: 'center', valign: 'middle'},
            {title: '登录设备', field: 'lastLoginFrom', visible: true, align: 'center', valign: 'middle'},
            // {title: '0：登录成功 1：登录失败', field: 'onlineFlg', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MemberChannelDeduct.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MemberChannelDeduct.seItem = selected[0];//单个
        MemberChannelDeduct.seItems = selected;//多个
        return true;
    }
};

/**
 * 删除渠道扣量
 */
MemberChannelDeduct.delete = function () {
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
            layer.close(index);
            var ajax = new $ax(Feng.ctxPath + "/memberChannelDeduct/delete", function (data) {
                Feng.success("删除成功!");
                MemberChannelDeduct.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("memberChannelDeductId",ids);
            ajax.start();
        });
    }
};

/**
 * 查询渠道扣量列表
 */
MemberChannelDeduct.search = function () {
    var queryData = {};
    queryData['userId'] = $("#userId").val();
    queryData['name'] = $("#name").val();
    queryData['registeDate'] = $("#registeDate").val();
    queryData['endTime'] = $("#endTime").val();
    queryData['lastLoginFrom'] = $("#lastLoginFrom").val();
    queryData['channelNum'] = $("#channelNum").val();
    MemberChannelDeduct.table.refresh({query: queryData});
};

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        MemberChannelDeduct.search();
    }
});

$(function () {
    var defaultColunms = MemberChannelDeduct.initColumn();
    var table = new BSTable(MemberChannelDeduct.id, "/memberChannelDeduct/list", defaultColunms);
    table.setPaginationType("server");
    MemberChannelDeduct.table = table.init();
});
