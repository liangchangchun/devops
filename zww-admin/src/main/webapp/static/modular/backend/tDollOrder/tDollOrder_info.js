/**
 * 初始化待发货列表详情对话框
 */
var TDollOrderInfoDlg = {
    tDollOrderInfoData : {}
};

/**
 * 清除数据
 */
TDollOrderInfoDlg.clearData = function() {
    this.tDollOrderInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TDollOrderInfoDlg.set = function(key, val) {
    this.tDollOrderInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TDollOrderInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TDollOrderInfoDlg.close = function() {
    parent.layer.close(window.parent.TDollOrder.layerIndex);
}

/**
 * 收集数据
 */
TDollOrderInfoDlg.collectData = function() {
    this
    .set('id')
    .set('orderNumber')
    .set('orderDate')
    .set('orderBy')
    .set('status')
    .set('stockValidDate')
    .set('deliverDate')
    .set('deliverMethod')
    .set('deliverNumber')
    .set('deliverAmount')
    .set('deliverCoins')
    .set('addressId')
    .set('modifiedDate')
    .set('modifiedBy')
    .set('dollCodes')
    .set('quantity')
    .set('memberIDs')
    ;
}

/**
 * 提交添加
 */
TDollOrderInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tDollOrder/add", function(data){
        Feng.success("添加成功!");
        window.parent.TDollOrder.table.refresh();
        TDollOrderInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tDollOrderInfoData);
    ajax.start();
}


/**
 * 提交修改
 */
TDollOrderInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tDollOrder/update", function(data){
        Feng.success("修改成功!");
        window.parent.TDollOrder.table.refresh();
        TDollOrderInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tDollOrderInfoData);
    ajax.start();
}

/**
 * 提交添加娃娃
 */
TDollOrderInfoDlg.backDollSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tDollOrder/backDoll", function(data){
        Feng.success("添加成功!");
        window.parent.TDollOrder.table.refresh();
        TDollOrderInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tDollOrderInfoData);
    ajax.start();
}


$(function() {

});

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        TDollOrderInfoDlg.editSubmit();
    }
});