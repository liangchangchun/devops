var BannerDlg = {
	InfoData: {}
};
/**
 * 清除数据
 */
BannerDlg.clearData = function () {
    this.InfoData = {};
};
/**
 * 收集数据
 */
BannerDlg.collectData = function () {
    this.set('id').set('bannerUrl');
};
/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BannerDlg.set = function (key, val) {
    this.InfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};
/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BannerDlg.get = function (key) {
    return $("#" + key).val();
};
/**
 * 关闭此对话框
 */
BannerDlg.close = function () {
    parent.layer.close(window.parent.TBanner.layerIndex);
};

BannerDlg.editSubmit= function () {
    this.clearData();
    this.collectData();
   // alert(this.get('bannerUrl'));
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tBanner/editUrl", function (data) {
        Feng.success("修改成功!");
        if (window.parent.TBanner != undefined) {
            window.parent.TBanner.table.refresh();
            BannerDlg.close();
        }
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.InfoData);
    ajax.start();
};

$(function() {
    // 初始化头像上传
    var avatarUp = new $WebUpload("bannerUrl");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.setUploadUrl(Feng.ctxPath + '/tBanner/upload/'+BannerDlg.get("id"));
    avatarUp.setSuccessEvent(function(){
    	 if (window.parent.TBanner != undefined) {
             window.parent.TBanner.table.refresh();
         }
    });
    avatarUp.init();
});