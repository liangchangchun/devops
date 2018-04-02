/**
 * 初始化设置vip等级权限详情对话框
 */
var MemberVipInfoDlg = {
    memberVipInfoData : {}
};

/**
 * 清除数据
 */
MemberVipInfoDlg.clearData = function() {
    this.memberVipInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberVipInfoDlg.set = function(key, val) {
    this.memberVipInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberVipInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MemberVipInfoDlg.close = function() {
    parent.layer.close(window.parent.MemberVip.layerIndex);
}

/**
 * 收集数据
 */
MemberVipInfoDlg.collectData = function() {
    this
    .set('id')
    .set('level')
    .set('name')
    .set('leastAllowed')
    .set('exemptionPostageNumber')
    .set('deliveryTime')
    .set('checkTime')
    .set('flashAppeal')
    .set('discount')
    ;
}

/**
 * 提交添加
 */
MemberVipInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/memberVip/add", function(data){
        Feng.success("添加成功!");
        window.parent.MemberVip.table.refresh();
        MemberVipInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberVipInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MemberVipInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/memberVip/update", function(data){
        Feng.success("修改成功!");
        window.parent.MemberVip.table.refresh();
        MemberVipInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberVipInfoData);
    ajax.start();
}

/**
 * 加载默认值
 */
MemberVipInfoDlg.init = function(column,value){
    if($("#"+column+"Value").val() == undefined){
        $("#"+column).val(0);
    }else{
        $("#"+column).val($("#"+column+"Value").val());
    }
}
/**
 * 修改初始化表单
 */
MemberVipInfoDlg.editInit = function() {
    MemberVipInfoDlg.init("flashAppeal");
}

$(function() {
    Feng.initValidator("memberVipInfoForm", MemberVipInfoDlg.validateFields);
    MemberVipInfoDlg.editInit();
});


//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        if($("#operation").val()=='add'){
            MemberVipInfoDlg.addSubmit();
        }
        if($("#operation").val()=='update'){
            MemberVipInfoDlg.editSubmit();
        }
    }
});