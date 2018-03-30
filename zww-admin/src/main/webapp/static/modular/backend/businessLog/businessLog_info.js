/**
 * 初始化日志管理详情对话框
 */
var BusinessLogInfoDlg = {
    businessLogInfoData : {}
};

/**
 * 清除数据
 */
BusinessLogInfoDlg.clearData = function() {
    this.businessLogInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusinessLogInfoDlg.set = function(key, val) {
    this.businessLogInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusinessLogInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BusinessLogInfoDlg.close = function() {
    parent.layer.close(window.parent.BusinessLog.layerIndex);
}

/**
 * 收集数据
 */
BusinessLogInfoDlg.collectData = function() {
    this
    .set('id')
    .set('logType')
    .set('logName')
    .set('userId')
    .set('className')
    .set('method')
    .set('createTime')
    .set('succeed')
    ;
}

/**
 * 提交添加
 */
BusinessLogInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/businessLog/add", function(data){
        Feng.success("添加成功!");
        window.parent.BusinessLog.table.refresh();
        BusinessLogInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.businessLogInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BusinessLogInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/businessLog/update", function(data){
        Feng.success("修改成功!");
        window.parent.BusinessLog.table.refresh();
        BusinessLogInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.businessLogInfoData);
    ajax.start();
}

$(function() {

});
