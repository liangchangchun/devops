/**
 * 初始化设置申诉问题列表详情对话框
 */
var DollRepairsProblemInfoDlg = {
    dollRepairsProblemInfoData : {}
};

/**
 * 清除数据
 */
DollRepairsProblemInfoDlg.clearData = function() {
    this.dollRepairsProblemInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DollRepairsProblemInfoDlg.set = function(key, val) {
    this.dollRepairsProblemInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DollRepairsProblemInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DollRepairsProblemInfoDlg.close = function() {
    parent.layer.close(window.parent.DollRepairsProblem.layerIndex);
}

/**
 * 收集数据
 */
DollRepairsProblemInfoDlg.collectData = function() {
    this
    .set('id')
    .set('problem')
    ;
}

/**
 * 提交添加
 */
DollRepairsProblemInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dollRepairsProblem/add", function(data){
        Feng.success("添加成功!");
        window.parent.DollRepairsProblem.table.refresh();
        DollRepairsProblemInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.dollRepairsProblemInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DollRepairsProblemInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dollRepairsProblem/update", function(data){
        Feng.success("修改成功!");
        window.parent.DollRepairsProblem.table.refresh();
        DollRepairsProblemInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.dollRepairsProblemInfoData);
    ajax.start();
}

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        if($("#operation").val()=='add'){
            // alert(1)
            DollRepairsProblemInfoDlg.addSubmit();
        }
        if($("#operation").val()=='update'){
            // alert(2)
            DollRepairsProblemInfoDlg.editSubmit();
        }
    }
});

$(function() {

});
