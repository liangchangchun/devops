var DivinationImageDlg = {
	InfoData: {}
};
/**
 * 清除数据
 */
DivinationImageDlg.clearData = function () {
    this.InfoData = {};
};
/**
 * 收集数据
 */
DivinationImageDlg.collectData = function () {
    this.set('id')
        .set('divinationTopicImg');
};
/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DivinationImageDlg.set = function (key, val) {
    this.InfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};
/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DivinationImageDlg.get = function (key) {
    return $("#" + key).val();
};
/**
 * 关闭此对话框
 */
DivinationImageDlg.close = function () {
    parent.layer.close(window.parent.DivinationImage.layerIndex);
};


$(function() {
    // 初始化头像上传
    var avatarUp = new $WebUpload("divinationTopicImg");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.setUploadUrl(Feng.ctxPath + '/divinationImage/upload/'+DivinationImageDlg.get("id"));
    avatarUp.setSuccessEvent(function(){
    	 if (window.parent.DivinationImage != undefined) {
             window.parent.DivinationImage.table.refresh();
         }
    });
    avatarUp.init();
});