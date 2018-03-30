/**
 * 初始化用户白名单列表详情对话框
 */
var MemberWhiteListInfoDlg = {
    memberWhiteListInfoData : {}
};

/**
 * 清除数据
 */
MemberWhiteListInfoDlg.clearData = function() {
    this.memberWhiteListInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberWhiteListInfoDlg.set = function(key, val) {
    this.memberWhiteListInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberWhiteListInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MemberWhiteListInfoDlg.close = function() {
    parent.layer.close(window.parent.MemberWhiteList.layerIndex);
}

/**
 * 收集数据
 */
MemberWhiteListInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userId')
    .set('memberId')
    .set('userName')
    .set('userIP')
    .set('states')
    .set('createdDate')
    ;
}

/**
 * 提交添加
 */
MemberWhiteListInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/memberWhiteList/add", function(data){
        Feng.success("添加成功!");
        window.parent.MemberWhiteList.table.refresh();
        MemberWhiteListInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberWhiteListInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MemberWhiteListInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/memberWhiteList/update", function(data){
        Feng.success("修改成功!");
        window.parent.MemberWhiteList.table.refresh();
        MemberWhiteListInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberWhiteListInfoData);
    ajax.start();
}

$(function() {

});
