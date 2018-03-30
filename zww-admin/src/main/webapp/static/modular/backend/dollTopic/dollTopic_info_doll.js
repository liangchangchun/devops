/**
 * 初始化娃娃机主题列表详情对话框
 */
var DollTopicInfoDlg = {
    dollTopicInfoData : {},
    validateFields: {
    	topicName: {
            validators: {
                notEmpty: {
                    message: '主题名称不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
DollTopicInfoDlg.clearData = function() {
    this.dollTopicInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DollTopicInfoDlg.set = function(key, val) {
    this.dollTopicInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DollTopicInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DollTopicInfoDlg.close = function() {
    parent.layer.close(window.parent.TDoll.layerIndex);
}

/**
 * 收集数据
 */
DollTopicInfoDlg.collectData = function() {
    this
    .set('id')
    .set('dollId')
    .set('dollName')
    .set('roomType')
    .set('topicType')
    .set('topicName')
    .set('groupid')
    ;
}

/**
 * 验证数据是否为空
 */
DollTopicInfoDlg.validate = function(formId){
    $('#'+formId).data("bootstrapValidator").resetForm();
    $('#'+formId).bootstrapValidator('validate');
    return $('#'+formId).data('bootstrapValidator').isValid();
}

/**
 * 提交添加
 */
DollTopicInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate('dollTopicInfoForm')) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dollTopic/add", function(data){
        Feng.success("添加成功!");
        window.parent.TDoll.table.refresh();
        DollTopicInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.dollTopicInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DollTopicInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate('dollTopicInfoForm')) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dollTopic/update", function(data){
        Feng.success("修改成功!");
        window.parent.TDoll.table.refresh();
        DollTopicInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.dollTopicInfoData);
    ajax.start();
}

/**
 * 加载默认值
 */
DollTopicInfoDlg.init = function(column,value){
	if($("#"+column+"Value").val() == undefined){
		if(value){$("#"+column).val(value);}
		else{ $("#"+column).val(0);}
	   }else{
	       $("#"+column).val($("#"+column+"Value").val());
	}
}
/**
 * 修改初始化表单
 */
DollTopicInfoDlg.editInit = function() {
	 if($("#dollIdValue").val() == undefined){
		   $("#dollId").attr("value","0");
	   }else{
		   //alert($("#payIndexValue").val());
		   //alert($("#payIndex").parent().html());
		   //$("#payIndex").attr("ckvalue",$("#payIndexValue").val());
	       $("#dollId").attr("value",$("#dollIdValue").val());
	      // alert($("#payIndex").val());
	   }
	 DollTopicInfoDlg.init("groupid",1);
}

$(function() {
	Feng.initValidator("dollTopicInfoForm", DollTopicInfoDlg.validateFields);
	DollTopicInfoDlg.editInit();
});



//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        if($("#operation").val()=='add'){
            // alert(1)
            DollTopicInfoDlg.addSubmit();
        }
        if($("#operation").val()=='update'){
            // alert(2)
            DollTopicInfoDlg.editSubmit();
        }
    }
});