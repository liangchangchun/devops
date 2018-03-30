/**
 * 初始化娃娃机批量上下线详情对话框
 */
var DollBatchUpdateInfoDlg = {
    dollBatchUpdateInfoData : {}
};

/**
 * 清除数据
 */
DollBatchUpdateInfoDlg.clearData = function() {
    this.dollBatchUpdateInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DollBatchUpdateInfoDlg.set = function(key, val) {
    this.dollBatchUpdateInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DollBatchUpdateInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DollBatchUpdateInfoDlg.close = function() {
    parent.layer.close(window.parent.DollBatchUpdate.layerIndex);
}

/**
 * 收集数据
 */
DollBatchUpdateInfoDlg.collectData = function() {
    this
    .set('id')
    .set('dollId')
    ;
}

/**
 * 提交添加
 */
DollBatchUpdateInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dollBatchUpdate/add", function(data){
        Feng.success("添加成功!");
        window.parent.DollBatchUpdate.table.refresh();
        DollBatchUpdateInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.dollBatchUpdateInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DollBatchUpdateInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dollBatchUpdate/update", function(data){
        Feng.success("修改成功!");
        window.parent.DollBatchUpdate.table.refresh();
        DollBatchUpdateInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.dollBatchUpdateInfoData);
    ajax.start();
}

$(function() {

});


//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        if($("#operation").val()=='add'){
            DollBatchUpdateInfoDlg.addSubmit();
        }
        if($("#operation").val()=='update'){
            DollBatchUpdateInfoDlg.editSubmit();
        }
    }
});