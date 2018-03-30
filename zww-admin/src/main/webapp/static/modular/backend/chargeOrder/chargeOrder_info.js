/**
 * 初始化orderManage详情对话框
 */
var ChargeOrderInfoDlg = {
    chargeOrderInfoData : {}
};

/**
 * 清除数据
 */
ChargeOrderInfoDlg.clearData = function() {
    this.chargeOrderInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ChargeOrderInfoDlg.set = function(key, val) {
    this.chargeOrderInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ChargeOrderInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ChargeOrderInfoDlg.close = function() {
    parent.layer.close(window.parent.ChargeOrder.layerIndex);
}

/**
 * 收集数据
 */
ChargeOrderInfoDlg.collectData = function() {
    this
    .set('id')
    .set('orderNo')
    .set('chargeruleid')
    .set('chargeName')
    .set('price')
    .set('memberId')
    .set('memberName')
    .set('chargeType')
    .set('chargeState')
    .set('coinsBefore')
    .set('coinsAfter')
    .set('coinsCharge')
    .set('coinsOffer')
    .set('createDate')
    ;
}

/**
 * 提交添加
 */
ChargeOrderInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/chargeOrder/add", function(data){
        Feng.success("添加成功!");
        window.parent.ChargeOrder.table.refresh();
        ChargeOrderInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.chargeOrderInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ChargeOrderInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/chargeOrder/update", function(data){
        Feng.success("修改成功!");
        window.parent.ChargeOrder.table.refresh();
        ChargeOrderInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.chargeOrderInfoData);
    ajax.start();
}

$(function() {

});
