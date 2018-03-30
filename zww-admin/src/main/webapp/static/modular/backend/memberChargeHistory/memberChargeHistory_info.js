/**
 * 初始化用户交易列表详情对话框
 */
var MemberChargeHistoryInfoDlg = {
    memberChargeHistoryInfoData : {}
};

/**
 * 清除数据
 */
MemberChargeHistoryInfoDlg.clearData = function() {
    this.memberChargeHistoryInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberChargeHistoryInfoDlg.set = function(key, val) {
    this.memberChargeHistoryInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberChargeHistoryInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MemberChargeHistoryInfoDlg.close = function() {
    parent.layer.close(window.parent.MemberChargeHistory.layerIndex);
}

/**
 * 收集数据
 */
MemberChargeHistoryInfoDlg.collectData = function() {
    this
    .set('id')
    .set('memberId')
    .set('prepaidAmt')
    .set('coins')
    .set('chargeDate')
    .set('type')
    .set('chargeMethod')
    .set('dollId')
    .set('coinsBefore')
    ;
}

/**
 * 提交添加
 */
MemberChargeHistoryInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/memberChargeHistory/add", function(data){
        Feng.success("添加成功!");
        window.parent.MemberChargeHistory.table.refresh();
        MemberChargeHistoryInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberChargeHistoryInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MemberChargeHistoryInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/memberChargeHistory/update", function(data){
        Feng.success("修改成功!");
        window.parent.MemberChargeHistory.table.refresh();
        MemberChargeHistoryInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberChargeHistoryInfoData);
    ajax.start();
}

$(function() {

});
