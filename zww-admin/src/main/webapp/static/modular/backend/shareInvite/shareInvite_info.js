/**
 * 初始化邀请人列表详情对话框
 */
var ShareInviteInfoDlg = {
    shareInviteInfoData : {}
};

/**
 * 清除数据
 */
ShareInviteInfoDlg.clearData = function() {
    this.shareInviteInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ShareInviteInfoDlg.set = function(key, val) {
    this.shareInviteInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ShareInviteInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ShareInviteInfoDlg.close = function() {
    parent.layer.close(window.parent.ShareInvite.layerIndex);
}

/**
 * 收集数据
 */
ShareInviteInfoDlg.collectData = function() {
    this
    .set('id')
    .set('inviteCode')
    .set('inviteMemberId')
    .set('invitedId')
    .set('createDate')
    ;
}

/**
 * 提交添加
 */
ShareInviteInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/shareInvite/add", function(data){
        Feng.success("添加成功!");
        window.parent.ShareInvite.table.refresh();
        ShareInviteInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.shareInviteInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ShareInviteInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/shareInvite/update", function(data){
        Feng.success("修改成功!");
        window.parent.ShareInvite.table.refresh();
        ShareInviteInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.shareInviteInfoData);
    ajax.start();
}

$(function() {

});
