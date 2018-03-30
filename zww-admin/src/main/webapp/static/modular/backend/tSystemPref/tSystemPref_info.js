/**
 * 初始化设置参数列表详情对话框
 */
var TSystemPrefInfoDlg = {
    tSystemPrefInfoData : {}
};

/**
 * 清除数据
 */
TSystemPrefInfoDlg.clearData = function() {
    this.tSystemPrefInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TSystemPrefInfoDlg.set = function(key, val) {
    this.tSystemPrefInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TSystemPrefInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TSystemPrefInfoDlg.close = function() {
    parent.layer.close(window.parent.TSystemPref.layerIndex);
}

/**
 * 收集数据
 */
TSystemPrefInfoDlg.collectData = function() {
    this
    .set('code')
    .set('name')
    .set('value')
    ;
}

/**
 * 提交添加
 */
TSystemPrefInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tSystemPref/add", function(data){
        Feng.success("添加成功!");
        window.parent.TSystemPref.table.refresh();
        TSystemPrefInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tSystemPrefInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TSystemPrefInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tSystemPref/update", function(data){
        Feng.success("修改成功!");
        window.parent.TSystemPref.table.refresh();
        TSystemPrefInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tSystemPrefInfoData);
    ajax.start();
}


//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        if($("#operation").val()=='add'){
            // alert(1)
            TSystemPrefInfoDlg.addSubmit();
        }
        if($("#operation").val()=='update'){
            // alert(2)
            TSystemPrefInfoDlg.editSubmit();
        }
    }
});


$(function() {

});
