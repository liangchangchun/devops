/**
 * 初始化第三方接入详情对话框
 */
var ThirdpartyInfoDlg = {
    thirdpartyInfoData : {}
};

/**
 * 清除数据
 */
ThirdpartyInfoDlg.clearData = function() {
    this.thirdpartyInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ThirdpartyInfoDlg.set = function(key, val) {
    this.thirdpartyInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ThirdpartyInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ThirdpartyInfoDlg.close = function() {
    parent.layer.close(window.parent.Thirdparty.layerIndex);
}

/**
 * 收集数据
 */
ThirdpartyInfoDlg.collectData = function() {
    this
    .set('id')
    .set('appkey')
    .set('passwordKey')
    .set('content')
    .set('type')
    .set('createdDate')
    .set('modifiedDate')
    .set('description')
    ;
}

/**
 * 提交添加
 */
ThirdpartyInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/thirdparty/add", function(data){
        Feng.success("添加成功!");
        window.parent.Thirdparty.table.refresh();
        ThirdpartyInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.thirdpartyInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ThirdpartyInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/thirdparty/update", function(data){
        Feng.success("修改成功!");
        window.parent.Thirdparty.table.refresh();
        ThirdpartyInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.thirdpartyInfoData);
    ajax.start();
}



/**
 * 加载默认值
 */
ThirdpartyInfoDlg.init = function(column,value){
    if($("#"+column+"Value").val() == undefined){
        $("#"+column).val(0);
    }else{
        $("#"+column).val($("#"+column+"Value").val());
    }
}
/**
 * 修改初始化表单
 */
ThirdpartyInfoDlg.editInit = function() {
    ThirdpartyInfoDlg.init("machineType");
}

$(function() {
    ThirdpartyInfoDlg.editInit();
});


//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        if($("#operation").val()=='add'){
            ThirdpartyInfoDlg.addSubmit();
        }
        if($("#operation").val()=='update'){
            ThirdpartyInfoDlg.editSubmit();
        }
    }
});