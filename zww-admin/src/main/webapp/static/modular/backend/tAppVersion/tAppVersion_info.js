/**
 * 初始化版本管理详情对话框
 */
var TAppVersionInfoDlg = {
    tAppVersionInfoData : {}
};

/**
 * 清除数据
 */
TAppVersionInfoDlg.clearData = function() {
    this.tAppVersionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TAppVersionInfoDlg.set = function(key, val) {
    this.tAppVersionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TAppVersionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TAppVersionInfoDlg.close = function() {
    parent.layer.close(window.parent.TAppVersion.layerIndex);
}

/**
 * 收集数据
 */
TAppVersionInfoDlg.collectData = function() {
    this
    .set('appKey')
    .set('version')
    .set('upgradeUrl')
    .set('content')
    .set('hideFlg')
    .set('forceUpdate')
    .set('openUpdate')
    ;
}

// /**
//  * 提交添加
//  */
// TAppVersionInfoDlg.addSubmit = function() {
//
//     this.clearData();
//     this.collectData();
//
//     //提交信息
//     var ajax = new $ax(Feng.ctxPath + "/tAppVersion/add", function(data){
//         Feng.success("添加成功!");
//         window.parent.TAppVersion.table.refresh();
//         TAppVersionInfoDlg.close();
//     },function(data){
//         Feng.error("添加失败!" + data.responseJSON.message + "!");
//     });
//     ajax.set(this.tAppVersionInfoData);
//     ajax.start();
// }

/**
 * 提交修改
 */
TAppVersionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tAppVersion/update", function(data){
        Feng.success("修改成功!");
        window.parent.TAppVersion.table.refresh();
        TAppVersionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tAppVersionInfoData);
    ajax.start();
}



/**
 * 加载默认值
 */
TAppVersionInfoDlg.init = function(column,value){
    if($("#"+column+"Value").val() == undefined){
        $("#"+column).val(0);
    }else{
        $("#"+column).val($("#"+column+"Value").val());
    }
}
/**
 * 修改初始化表单
 */
TAppVersionInfoDlg.editInit = function() {
    //初始化banner类型
    TAppVersionInfoDlg.init("forceUpdate");
    TAppVersionInfoDlg.init("openUpdate");

}


$(function() {
    Feng.initValidator("tAppVersionInfoForm", TAppVersionInfoDlg.validateFields);
    TAppVersionInfoDlg.editInit();
});


//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
            TDollInfoDlg.editSubmit();
    }
});