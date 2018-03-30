var DivinationTopicDlg = {
	InfoData: {}
};
/**
 * 清除数据
 */
DivinationTopicDlg.clearData = function () {
    this.InfoData = {};
};
/**
 * 收集数据
 */
DivinationTopicDlg.collectData = function () {
    this.set('id')
        .set('modeUrl');
};
/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DivinationTopicDlg.set = function (key, val) {
    this.InfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};
/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DivinationTopicDlg.get = function (key) {
    return $("#" + key).val();
};
/**
 * 关闭此对话框
 */
DivinationTopicDlg.close = function () {
    parent.layer.close(window.parent.DivinationTopic.layerIndex);
};


$(function() {
    // 初始化头像上传
    var avatarUp = new $WebUpload("modeUrl");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.setUploadUrl(Feng.ctxPath + '/divinationTopic/upload/'+DivinationTopicDlg.get("id"));
    avatarUp.setSuccessEvent(function(){
    	 if (window.parent.DivinationTopic != undefined) {
             window.parent.DivinationTopic.table.refresh();
         }
    });
    avatarUp.init();
});