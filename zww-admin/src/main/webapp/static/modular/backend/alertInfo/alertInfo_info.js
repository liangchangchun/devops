/**
 * 初始化抓取弹窗列表详情对话框
 */
var AlertInfoInfoDlg = {
    alertInfoInfoData : {}
};

/**
 * 清除数据
 */
AlertInfoInfoDlg.clearData = function() {
    this.alertInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AlertInfoInfoDlg.set = function(key, val) {
    this.alertInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AlertInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AlertInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.AlertInfo.layerIndex);
}

/**
 * 收集数据
 */
AlertInfoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('alertImgUrl')
    .set('description')
    .set('hyperlink')
    .set('linkType')
    .set('sorts')
    .set('createdDate')
    .set('createdBy')
    .set('modifiedDate')
    ;
}

/**
 * 提交添加
 */
AlertInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/alertInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.AlertInfo.table.refresh();
        AlertInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.alertInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AlertInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/alertInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.AlertInfo.table.refresh();
        AlertInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.alertInfoInfoData);
    ajax.start();
}

$(function() {

});

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        if($("#operation").val()=='add'){
            // alert(1)
            AlertInfoInfoDlg.addSubmit();
        }
        if($("#operation").val()=='update'){
            // alert(2)
            AlertInfoInfoDlg.editSubmit();
        }
    }
});