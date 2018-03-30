var TAppVersionDlg = {
	InfoData: {}
};
/**
 * 清除数据
 */
TAppVersionDlg.clearData = function () {
    this.InfoData = {};
};
/**
 * 收集数据
 */
TAppVersionDlg.collectData = function () {
    this.set('id').set('upgradeUrl');
};
/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TAppVersionDlg.set = function (key, val) {
    this.InfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};
/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TAppVersionDlg.get = function (key) {
    return $("#" + key).val();
};
/**
 * 关闭此对话框
 */
TAppVersionDlg.close = function () {
    parent.layer.close(window.parent.TAppVersion.layerIndex);
};


$(function() {
    // 初始化头像上传
    var avatarUp = new $WebUpload("upgradeUrl");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.setUploadUrl(Feng.ctxPath + '/tAppVersion/upload/'+TAppVersionDlg.get("id"));
    avatarUp.setSuccessEvent(function(){
    	 if (window.parent.TAppVersion != undefined) {
             window.parent.TAppVersion.table.refresh();
         }
    });
    avatarUp.init();
});