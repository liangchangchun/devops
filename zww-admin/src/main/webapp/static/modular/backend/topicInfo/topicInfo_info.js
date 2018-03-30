/**
 * 初始化娃娃机主题详情列表详情对话框
 */
var TopicInfoInfoDlg = {
    topicInfoInfoData : {}
};

/**
 * 清除数据
 */
TopicInfoInfoDlg.clearData = function() {
    this.topicInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TopicInfoInfoDlg.set = function(key, val) {
    this.topicInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TopicInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TopicInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.TopicInfo.layerIndex);
}

/**
 * 收集数据
 */
TopicInfoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('topicType')
    .set('topicName')
    .set('details')
    .set('orderBy')
    ;
}

/**
 * 提交添加
 */
TopicInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/topicInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.TopicInfo.table.refresh();
        TopicInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.topicInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TopicInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/topicInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.TopicInfo.table.refresh();
        TopicInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.topicInfoInfoData);
    ajax.start();
}


/**
 * 加载默认值
 */
TopicInfoInfoDlg.init = function(column,value){
    if($("#"+column+"Value").val() == undefined){
        $("#"+column).val(0);
    }else{
        $("#"+column).val($("#"+column+"Value").val());
    }
}
/**
 * 修改初始化表单
 */
TopicInfoInfoDlg.editInit = function() {

    if($("#topicTypeValue").val() == undefined){
        $("#topicType").attr("value","");
    }else{
        $("#topicType").attr("value",$("#topicTypeValue").val());
    }

}

$(function() {
    Feng.initValidator("tBannerInfoForm", TopicInfoInfoDlg.validateFields);
    TopicInfoInfoDlg.editInit();
});
