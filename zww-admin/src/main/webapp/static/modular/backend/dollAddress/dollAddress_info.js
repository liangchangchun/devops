/**
 * 初始化娃娃机地址列表详情对话框
 */
var DollAddressInfoDlg = {
    dollAddressInfoData : {}
};

/**
 * 清除数据
 */
DollAddressInfoDlg.clearData = function() {
    this.dollAddressInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DollAddressInfoDlg.set = function(key, val) {
    this.dollAddressInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DollAddressInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DollAddressInfoDlg.close = function() {
    parent.layer.close(window.parent.DollAddress.layerIndex);
}

/**
 * 收集数据
 */
DollAddressInfoDlg.collectData = function() {
    this
    .set('id')
    .set('province')
    .set('city')
    .set('county')
    .set('street')
    .set('defaultFlg')
    ;
}

/**
 * 提交添加
 */
DollAddressInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dollAddress/add", function(data){
        Feng.success("添加成功!");
        window.parent.DollAddress.table.refresh();
        DollAddressInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.dollAddressInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DollAddressInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dollAddress/update", function(data){
        Feng.success("修改成功!");
        window.parent.DollAddress.table.refresh();
        DollAddressInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.dollAddressInfoData);
    ajax.start();
}

/**
 * 加载默认值
 */
DollAddressInfoDlg.init = function(column,value){
	if($("#"+column+"Value").val() == undefined){
	       $("#"+column).val(true);
	   }else{
	       $("#"+column).val($("#"+column+"Value").val());
	}
}

/**
 * 修改初始化表单
 */
DollAddressInfoDlg.editInit = function() {
	TDollInfoDlg.init("defaultFlg");
}

$(function() {
	DollAddressInfoDlg.editInit();
});

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        if($("#operation").val()=='add'){
            // alert(1)
            DollAddressInfoDlg.addSubmit();
        }
        if($("#operation").val()=='update'){
            // alert(2)
            DollAddressInfoDlg.editSubmit();
        }
    }
});