/**
 * 初始化报修列表详情对话框
 */
var DollRepairsInfoDlg = {
    dollRepairsInfoData : {}
};

/**
 * 清除数据
 */
DollRepairsInfoDlg.clearData = function() {
    this.dollRepairsInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DollRepairsInfoDlg.set = function(key, val) {
    this.dollRepairsInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DollRepairsInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DollRepairsInfoDlg.close = function() {
    parent.layer.close(window.parent.DollRepairs.layerIndex);
}

/**
 * 收集数据
 */
DollRepairsInfoDlg.collectData = function() {
    this
    .set('id')
    .set('userId')
    .set('dollId')
    .set('repairsReason')
    .set('createDate')
    ;
}

/**
 * 提交添加
 */
DollRepairsInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dollRepairs/add", function(data){
        Feng.success("添加成功!");
        window.parent.DollRepairs.table.refresh();
        DollRepairsInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.dollRepairsInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DollRepairsInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dollRepairs/update", function(data){
        Feng.success("修改成功!");
        window.parent.DollRepairs.table.refresh();
        DollRepairsInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.dollRepairsInfoData);
    ajax.start();
}

$(function() {

});
