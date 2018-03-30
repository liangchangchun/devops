/**
 * 初始化设置申诉问题列表详情对话框
 */
var MemberComplaintProblemInfoDlg = {
    memberComplaintProblemInfoData : {}
};

/**
 * 清除数据
 */
MemberComplaintProblemInfoDlg.clearData = function() {
    this.memberComplaintProblemInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberComplaintProblemInfoDlg.set = function(key, val) {
    this.memberComplaintProblemInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MemberComplaintProblemInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MemberComplaintProblemInfoDlg.close = function() {
    parent.layer.close(window.parent.MemberComplaintProblem.layerIndex);
}

/**
 * 收集数据
 */
MemberComplaintProblemInfoDlg.collectData = function() {
    this
    .set('id')
    .set('problem')
    ;
}

/**
 * 提交添加
 */
MemberComplaintProblemInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/memberComplaintProblem/add", function(data){
        Feng.success("添加成功!");
        window.parent.MemberComplaintProblem.table.refresh();
        MemberComplaintProblemInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberComplaintProblemInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MemberComplaintProblemInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/memberComplaintProblem/update", function(data){
        Feng.success("修改成功!");
        window.parent.MemberComplaintProblem.table.refresh();
        MemberComplaintProblemInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.memberComplaintProblemInfoData);
    ajax.start();
}

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        if($("#operation").val()=='add'){
            // alert(1)
            MemberComplaintProblemInfoDlg.addSubmit();
        }
        if($("#operation").val()=='update'){
            // alert(2)
            MemberComplaintProblemInfoDlg.editSubmit();
        }
    }
});

$(function() {

});
