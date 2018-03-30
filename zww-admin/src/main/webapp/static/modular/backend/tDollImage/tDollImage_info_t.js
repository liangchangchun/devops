/**
 * 初始化娃娃机背景图片列表详情对话框
 */
var TDollImageInfoDlg = {
    tDollImageInfoData : {},
    validateFields: {
        imgRealPath: {
            validators: {
                notEmpty: {
                    message: '请先上传娃娃机背景图片'
                }
            }
        }
     }
};

/**
 * 清除数据
 */
TDollImageInfoDlg.clearData = function() {
    this.tDollImageInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TDollImageInfoDlg.set = function(key, val) {
    this.tDollImageInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TDollImageInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TDollImageInfoDlg.close = function() {
    parent.layer.close(window.parent.TDollImage.layerIndex);
}

/**
 * 收集数据
 */
TDollImageInfoDlg.collectData = function() {
    this
    .set('id')
    .set('dollId')
    .set('imgRealPath')
    ;
}

/**
 * 验证数据是否为空
 */
TDollImageInfoDlg.validate = function(formId){
    $('#'+formId).data("bootstrapValidator").resetForm();
    $('#'+formId).bootstrapValidator('validate');
    return $('#'+formId).data('bootstrapValidator').isValid();
}

/**
 * 提交添加
 */
TDollImageInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate('tDollImageInfoForm')) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tDollImage/add?temp=1", function(data){
        Feng.success("添加成功!");
        window.parent.TDollImage.table.refresh();
        TDollImageInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tDollImageInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TDollImageInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate('tDollImageInfoForm')) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tDollImage/update", function(data){
        Feng.success("修改成功!");
        window.parent.TDollImage.table.refresh();
        TDollImageInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tDollImageInfoData);
    ajax.start();
}

/**
 * 加载默认值
 */
TDollImageInfoDlg.init = function(column,value){
    if($("#"+column+"Value").val() == undefined){
        $("#"+column).val(0);
    }else{
        $("#"+column).val($("#"+column+"Value").val());
    }
}
/**
 * 修改初始化表单
 */
TDollImageInfoDlg.editInit = function() {

    TDollImageInfoDlg.init("dollId");
    if($("#dollIdValue").val() == undefined){
        $("#dollId").attr("value","");
    }else{
        $("#dollId").attr("value",$("#dollIdValue").val());
    }

}


$(function() {
	Feng.initValidator("tDollImageInfoForm", TDollImageInfoDlg.validateFields);
    TDollImageInfoDlg.editInit();
	// 初始化头像上传
    var avatarUp = new $WebUpload("imgRealPath");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.setUploadUrl(Feng.ctxPath + '/tDollImage/uploads/'+TDollImageInfoDlg.get('dollId'));
    avatarUp.init();
});
