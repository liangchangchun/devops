/**
 * 初始化渠道扣量详情对话框
 */
var MemberChannelDeductInfoDlg = {
    memberChannelDeductInfoData : {}
};

/**
 * 清除数据
 */
MemberChannelDeductInfoDlg.clearData = function() {
    this.memberChannelDeductInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberChannelDeductInfoDlg.set = function(key, val) {
    this.memberChannelDeductInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberChannelDeductInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MemberChannelDeductInfoDlg.close = function() {
    parent.layer.close(window.parent.MemberChannelDeduct.layerIndex);
}

/**
 * 收集数据
 */
MemberChannelDeductInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userId')
    .set('memberID')
    .set('name')
    .set('mobile')
    .set('weixinId')
    .set('gender')
    .set('phoneModel')
    .set('registerDate')
    .set('lastLoginDate')
    .set('registerChannel')
    .set('loginChannel')
    .set('registerFrom')
    .set('lastLoginFrom')
    ;
}

/**
 * 提交添加
 */
MemberChannelDeductInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/memberChannelDeduct/add", function(data){
        Feng.success("添加成功!");
        window.parent.MemberChannelDeduct.table.refresh();
        MemberChannelDeductInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberChannelDeductInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MemberChannelDeductInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/memberChannelDeduct/update", function(data){
        Feng.success("修改成功!");
        window.parent.MemberChannelDeduct.table.refresh();
        MemberChannelDeductInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberChannelDeductInfoData);
    ajax.start();
}

$(function() {

});
