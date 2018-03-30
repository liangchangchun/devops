/**
 * 初始化机器占卜主题详情对话框
 */
var DollDivinationInfoDlg = {
    dollDivinationInfoData : {}
};

/**
 * 清除数据
 */
DollDivinationInfoDlg.clearData = function() {
    this.dollDivinationInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DollDivinationInfoDlg.set = function(key, val) {
    this.dollDivinationInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DollDivinationInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DollDivinationInfoDlg.close = function() {
    parent.layer.close(window.parent.DollDivination.layerIndex);
}

/**
 * 收集数据
 */
DollDivinationInfoDlg.collectData = function() {
    this
    .set('id')
    .set('dollId')
    .set('divinationId')
    ;
}

/**
 * 提交添加
 */
DollDivinationInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dollDivination/add", function(data){
        Feng.success("添加成功!");
        window.parent.DollDivination.table.refresh();
        DollDivinationInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.dollDivinationInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DollDivinationInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dollDivination/update", function(data){
        Feng.success("修改成功!");
        window.parent.DollDivination.table.refresh();
        DollDivinationInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.dollDivinationInfoData);
    ajax.start();
}

/**
 * 加载默认值
 */
DollDivinationInfoDlg.init = function(column,value){
    if($("#"+column+"Value").val() == undefined){
        if(value){$("#"+column).val(value);}
        else{ $("#"+column).val(0);}
    }else{
        $("#"+column).val($("#"+column+"Value").val());
    }
}
/**
 * 修改初始化表单
 */
DollDivinationInfoDlg.editInit = function() {

    DollDivinationInfoDlg.init('dollId');
    DollDivinationInfoDlg.init('divinationId');
    if($("#dollIdValue").val() == undefined){
    }else{
        $("#dollId").attr("value",$("#dollIdValue").val());
    }

    if($("#divinationIdValue").val() == undefined){
    }else{
        $("#divinationId").attr("value",$("#divinationIdValue").val());
    }

}

$(function() {
    Feng.initValidator("dollTopicInfoForm", DollDivinationInfoDlg.validateFields);
    DollDivinationInfoDlg.editInit();
});


//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        if($("#operation").val()=='add'){
            DollDivinationInfoDlg.addSubmit();
        }
        if($("#operation").val()=='update'){
            DollDivinationInfoDlg.editSubmit();
        }
    }
});