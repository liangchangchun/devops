/**
 * 初始化占卜图片详情对话框
 */
var DivinationImageInfoDlg = {
    divinationImageInfoData : {}
};

/**
 * 清除数据
 */
DivinationImageInfoDlg.clearData = function() {
    this.divinationImageInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DivinationImageInfoDlg.set = function(key, val) {
    this.divinationImageInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DivinationImageInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DivinationImageInfoDlg.close = function() {
    parent.layer.close(window.parent.DivinationImage.layerIndex);
}

/**
 * 收集数据
 */
DivinationImageInfoDlg.collectData = function() {
    this
    .set('id')
    .set('divinationTopicImg')
    .set('divinationTopicId')
    .set('divinationName')
    .set('createdDate')
    .set('createdBy')
    .set('modifiedDate')
    ;
}

/**
 * 提交添加
 */
DivinationImageInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    // if (!this.validate('divinationTopicInfoForm')) {
    //     return;
    // }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/divinationImage/add?temp=0", function(data){
        Feng.success("添加成功!");
        window.parent.DivinationImage.table.refresh();
        DivinationImageInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.divinationImageInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DivinationImageInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    // if (!this.validate('divinationTopicInfoForm')) {
    //     return;
    // }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/divinationImage/update", function(data){
        Feng.success("修改成功!");
        window.parent.DivinationImage.table.refresh();
        DivinationImageInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.divinationImageInfoData);
    ajax.start();
}



$(function() {

    Feng.initValidator("divinationTopicInfoForm", DivinationImageInfoDlg.validateFields);
    // 初始化头像上传
    var avatarUp = new $WebUpload("divinationTopicImg");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.setUploadUrl(Feng.ctxPath + '/divinationImage/upload/-1');
    avatarUp.init();
});
