var TDollImageDlg = {
	InfoData: {}
};
/**
 * 清除数据
 */
TDollImageDlg.clearData = function () {
    this.InfoData = {};
};
/**
 * 收集数据
 */
TDollImageDlg.collectData = function () {
    this.set('id').set('TDollImagePath');
};
/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TDollImageDlg.set = function (key, val) {
    this.InfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};
/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TDollImageDlg.get = function (key) {
    return $("#" + key).val();
};
/**
 * 关闭此对话框
 */
TDollImageDlg.close = function () {
    parent.layer.close(window.parent.TDollImage.layerIndex);
};


$(function() {
    // 初始化头像上传
    var avatarUp = new $WebUpload("TDollImagePath");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.setUploadUrl(Feng.ctxPath + '/tDollImage/upload/'+TDollImageDlg.get("id"));
    avatarUp.setSuccessEvent(function(){
    	 if (window.parent.TDollImage != undefined) {
             window.parent.TDollImage.table.refresh();
         }
    });
    avatarUp.init();
});