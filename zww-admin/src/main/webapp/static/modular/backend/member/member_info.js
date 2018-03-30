/**
 * 初始化member详情对话框
 */
var MemberInfoDlg = {
    memberInfoData : {},
    validateFields: {
        addReason: {
            validators: {
                notEmpty: {
                    message: '请先填写原因'
                }
            }
        }
    }
};

/**
 * 验证数据是否为空
 */
MemberInfoDlg.validate = function(formId){
    $('#'+formId).data("bootstrapValidator").resetForm();
    $('#'+formId).bootstrapValidator('validate');
    return $('#'+formId).data('bootstrapValidator').isValid();
}

/**
 * 清除数据
 */
MemberInfoDlg.clearData = function() {
    this.memberInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberInfoDlg.set = function(key, val) {
    this.memberInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MemberInfoDlg.close = function() {
    parent.layer.close(window.parent.Member.layerIndex);
}

/**
 * 收集数据
 */
MemberInfoDlg.collectData = function() {
    this
    .set('id')
    .set('coins')
    .set('superTicket')
    .set('addReason')
    ;
}

/**
 * 提交添加
 */
MemberInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    // if (!this.validate('memberInfoForm')) {
    //     return;
    // }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/member/add", function(data){
        Feng.success("添加成功!");
        window.parent.Member.table.refresh();
        MemberInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MemberInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate('memberInfoForm')) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/member/update", function(data){
        Feng.success("修改成功!");
        window.parent.Member.table.refresh();
        MemberInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberInfoData);
    ajax.start();
}

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        MemberInfoDlg.editSubmit();
    }
});


$(function() {
    //为空验证
    Feng.initValidator("memberInfoForm", MemberInfoDlg.validateFields);
});
