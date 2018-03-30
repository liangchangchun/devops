var AlertInfoDlg = {
	InfoData: {}
};
/**
 * 清除数据
 */
AlertInfoDlg.clearData = function () {
    this.InfoData = {};
};
/**
 * 收集数据
 */
AlertInfoDlg.collectData = function () {
    this.set('id').set('alertImgUrl');
};
/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AlertInfoDlg.set = function (key, val) {
    this.InfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};
/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AlertInfoDlg.get = function (key) {
    return $("#" + key).val();
};
/**
 * 关闭此对话框
 */
AlertInfoDlg.close = function () {
    parent.layer.close(window.parent.AlertInfo.layerIndex);
};

AlertInfoDlg.editSubmit= function () {
    this.clearData();
    this.collectData();
   // alert(this.get('bannerUrl'));
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/alertInfo/editUrl", function (data) {
        Feng.success("修改成功!");
        if (window.parent.AlertInfo != undefined) {
            window.parent.AlertInfo.table.refresh();
            AlertInfoDlg.close();
        }
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.InfoData);
    ajax.start();
};

$(function() {
    // 初始化头像上传
    var avatarUp = new $WebUpload("alertImgUrl");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.setUploadUrl(Feng.ctxPath + '/alertInfo/upload/'+AlertInfoDlg.get("id"));
    avatarUp.setSuccessEvent(function(){
    	 if (window.parent.AlertInfo != undefined) {
             window.parent.AlertInfo.table.refresh();
         }
    });
    avatarUp.init();
});