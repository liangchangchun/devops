/**
 * 初始化待处理申诉详情对话框
 */
var MemberComplaintInfoDlg = {
    memberComplaintInfoData : {}
};

/**
 * 清除数据
 */
MemberComplaintInfoDlg.clearData = function() {
    this.memberComplaintInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberComplaintInfoDlg.set = function(key, val) {
    this.memberComplaintInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberComplaintInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MemberComplaintInfoDlg.close = function() {
    parent.layer.close(window.parent.MemberComplaint.layerIndex);
}

/**
 * 收集数据
 */
MemberComplaintInfoDlg.collectData = function() {
    this
    .set('id')
    .set('gameNum')
    .set('memberId')
    .set('memberNum')
    .set('dollId')
    .set('memberCatchId')
    .set('complaintReason')
    .set('checkState')
    .set('checkReason')
    .set('creatDate')
    .set('creatBy')
    .set('modifyDate')
    ;
}

/**
 * 提交通过申诉返娃娃
 */
MemberComplaintInfoDlg.backDollSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/memberComplaint/add?backStatus=2", function(data){
        Feng.success("操作成功!");
        window.parent.MemberComplaint.table.refresh();
        MemberComplaintInfoDlg.close();
    },function(data){
        Feng.error("操作失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberComplaintInfoData);
    ajax.start();
}

/**
 * 提交通过申诉返币，钻
 */
MemberComplaintInfoDlg.backCoinsSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/memberComplaint/add?backStatus=1", function(data){
        Feng.success("操作成功!");
        window.parent.MemberComplaint.table.refresh();
        MemberComplaintInfoDlg.close();
    },function(data){
        Feng.error("操作失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberComplaintInfoData);
    ajax.start();
}

/**
 * 提交驳回申诉
 */
MemberComplaintInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/memberComplaint/update?backStatus=-1", function(data){
        Feng.success("驳回成功!");
        window.parent.MemberComplaint.table.refresh();
        MemberComplaintInfoDlg.close();
    },function(data){
        Feng.error("驳回失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberComplaintInfoData);
    ajax.start();
}

$(function() {

});

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        if($("#operation").val()=='nopass'){
            MemberComplaintInfoDlg.editSubmit();
        }
    }
});