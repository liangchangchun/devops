/**
 * 初始化占卜主题详情对话框
 */
var DivinationTopicInfoDlg = {
    divinationTopicInfoData : {}
};

/**
 * 清除数据
 */
DivinationTopicInfoDlg.clearData = function() {
    this.divinationTopicInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DivinationTopicInfoDlg.set = function(key, val) {
    this.divinationTopicInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DivinationTopicInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DivinationTopicInfoDlg.close = function() {
    parent.layer.close(window.parent.DivinationTopic.layerIndex);
}

/**
 * 收集数据
 */
DivinationTopicInfoDlg.collectData = function() {
    this
    .set('id')
    .set('divinationName')
    .set('createdDate')
    .set('createdBy')
    .set('modifiedDate')
    .set('resultStatus')
    .set('wxpireTime')
    ;
}

/**
 * 提交添加
 */
DivinationTopicInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/divinationTopic/add", function(data){
        Feng.success("添加成功!");
        window.parent.DivinationTopic.table.refresh();
        DivinationTopicInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.divinationTopicInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DivinationTopicInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/divinationTopic/update", function(data){
        Feng.success("修改成功!");
        window.parent.DivinationTopic.table.refresh();
        DivinationTopicInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.divinationTopicInfoData);
    ajax.start();
}


/**
 * 加载默认值
 */
DivinationTopicInfoDlg.init = function(column,value){
    if($("#"+column+"Value").val() == undefined){
        $("#"+column).val(0);
    }else{
        $("#"+column).val($("#"+column+"Value").val());
    }
}
/**
 * 修改初始化表单
 */
DivinationTopicInfoDlg.editInit = function() {

    // DivinationTopicInfoDlg.init("wxpireTime");
    // DivinationTopicInfoDlg.init("resultStatus");

}

$(function() {
    Feng.initValidator("tBannerInfoForm", DivinationTopicInfoDlg.validateFields);
    DivinationTopicInfoDlg.editInit();
});


//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        if($("#operation").val()=='add'){
            // alert(1)
            DivinationTopicInfoDlg.addSubmit();
        }
        if($("#operation").val()=='update'){
            // alert(2)
            DivinationTopicInfoDlg.editSubmit();
        }
    }
});