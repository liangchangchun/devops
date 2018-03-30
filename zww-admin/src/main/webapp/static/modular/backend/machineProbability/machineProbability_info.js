/**
 * 初始化娃娃机概率列表详情对话框
 */
var MachineProbabilityInfoDlg = {
    machineProbabilityInfoData : {}
};

/**
 * 清除数据
 */
MachineProbabilityInfoDlg.clearData = function() {
    this.machineProbabilityInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MachineProbabilityInfoDlg.set = function(key, val) {
    this.machineProbabilityInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MachineProbabilityInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MachineProbabilityInfoDlg.close = function() {
    parent.layer.close(window.parent.MachineProbability.layerIndex);
}

/**
 * 收集数据
 */
MachineProbabilityInfoDlg.collectData = function() {
    this
    .set('id')
    .set('probabilityRulesId')
    .set('dollId')
    .set('probability1')
    .set('probability2')
    .set('probability3')
    .set('createdDate')
    .set('modifiedDate')
    .set('createdBy')
    .set('baseNum')
    ;
}

/**
 * 提交添加
 */
MachineProbabilityInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/machineProbability/add", function(data){
        Feng.success("添加成功!");
        window.parent.MachineProbability.table.refresh();
        MachineProbabilityInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.machineProbabilityInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MachineProbabilityInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/machineProbability/update", function(data){
        Feng.success("修改成功!");
        window.parent.MachineProbability.table.refresh();
        MachineProbabilityInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.machineProbabilityInfoData);
    ajax.start();
}

/**
 * 加载默认值
 */
MachineProbabilityInfoDlg.init = function(column,value){
    if($("#"+column+"Value").val() == undefined){
        $("#"+column).val(0);
    }else{
        $("#"+column).val($("#"+column+"Value").val());
    }
}
/**
 * 修改初始化表单
 */
MachineProbabilityInfoDlg.editInit = function() {
    //初始化
    if($("#dollIdValue").val() == undefined){
        $("#dollId").attr("value","");
    }else{
        $("#dollId").attr("value",$("#dollIdValue").val());
    }

}


$(function() {
    Feng.initValidator("machineProbabilityInfoForm", MachineProbabilityInfoDlg.validateFields);
    MachineProbabilityInfoDlg.editInit();
});

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        if($("#operation").val()=='add'){
            // alert(1)
            MachineProbabilityInfoDlg.addSubmit();
        }
        if($("#operation").val()=='update'){
            // alert(2)
            MachineProbabilityInfoDlg.editSubmit();
        }
    }
});