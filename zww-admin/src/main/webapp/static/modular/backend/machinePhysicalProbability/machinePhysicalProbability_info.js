/**
 * 初始化机器物理概率详情对话框
 */
var MachinePhysicalProbabilityInfoDlg = {
    machinePhysicalProbabilityInfoData : {},
    validateFields: {
        strongVoltage: {
            validators: {
                notEmpty: {
                    message: '请填写强力电压'
                },
                regexp: {
                      regexp: /^(?:[1-9]|([1-3][0-9])?|4[0-8])$/,
                      message: '输入范围0~48'
                }
            }
        },
        weakOneVoltage: {
            validators: {
                notEmpty: {
                    message: '请填写弱一电压'
                },
                regexp: {
                    regexp: /^(?:[1-9]|([1-3][0-9])?|4[0-8])$/,
                    message: '输入范围0~48'
                }
            }
        },
        weakTwoVoltage: {
            validators: {
                notEmpty: {
                    message: '请填写弱二电压'
                },
                regexp: {
                    regexp: /^(?:[1-9]|([1-3][0-9])?|4[0-8])$/,
                    message: '输入范围0~48'
                }
            }
        },
        strongTime: {
            validators: {
                notEmpty: {
                    message: '请填写强力时间'
                },
                regexp: {
                    regexp: /^(?:0|[1-9][0-9]?|100)$/,
                    message: '输入范围0~100'
                }
            }
        },
        weakTime: {
            validators: {
                notEmpty: {
                    message: '请填写弱一时间'
                },
                regexp: {
                    regexp: /^(?:0|[1-9][0-9]?|100)$/,
                    message: '输入范围0~100'
                }
            }
        }
    }
};


/**
 * 验证数据是否为空
 */
MachinePhysicalProbabilityInfoDlg.validate = function(formId){
    $('#'+formId).data("bootstrapValidator").resetForm();
    $('#'+formId).bootstrapValidator('validate');
    return $('#'+formId).data('bootstrapValidator').isValid();
}


/**
 * 清除数据
 */
MachinePhysicalProbabilityInfoDlg.clearData = function() {
    this.machinePhysicalProbabilityInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MachinePhysicalProbabilityInfoDlg.set = function(key, val) {
    this.machinePhysicalProbabilityInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MachinePhysicalProbabilityInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MachinePhysicalProbabilityInfoDlg.close = function() {
    parent.layer.close(window.parent.MachinePhysicalProbability.layerIndex);
}

/**
 * 收集数据
 */
MachinePhysicalProbabilityInfoDlg.collectData = function() {
    this
    .set('id')
    .set('dollId')
    .set('strongVoltage')
    .set('weakOneVoltage')
    .set('weakTwoVoltage')
    .set('strongTime')
    .set('weakTime')
    .set('createdDate')
    .set('createdBy')
    .set('modifiedDate')
    ;
}

/**
 * 提交添加
 */
MachinePhysicalProbabilityInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate('machinePhysicalProbabilityInfoForm')) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/machinePhysicalProbability/add", function(data){
        Feng.success("添加成功!");
        window.parent.MachinePhysicalProbability.table.refresh();
        MachinePhysicalProbabilityInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.machinePhysicalProbabilityInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MachinePhysicalProbabilityInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate('machinePhysicalProbabilityInfoForm')) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/machinePhysicalProbability/update", function(data){
        Feng.success("修改成功!");
        window.parent.MachinePhysicalProbability.table.refresh();
        MachinePhysicalProbabilityInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.machinePhysicalProbabilityInfoData);
    ajax.start();
}

/**
 * 加载默认值
 */
MachinePhysicalProbabilityInfoDlg.init = function(column,value){
    if($("#"+column+"Value").val() == undefined){
        $("#"+column).val(0);
    }else{
        $("#"+column).val($("#"+column+"Value").val());
    }
}
/**
 * 修改初始化表单
 */
MachinePhysicalProbabilityInfoDlg.editInit = function() {
    //初始化
    if($("#dollIdValue").val() == undefined){
        $("#dollId").attr("value","");
    }else{
        $("#dollId").attr("value",$("#dollIdValue").val());
    }

}

$(function() {
    Feng.initValidator("machinePhysicalProbabilityInfoForm", MachinePhysicalProbabilityInfoDlg.validateFields);
    MachinePhysicalProbabilityInfoDlg.editInit();
});

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        if($("#operation").val()=='add'){
            // alert(1)
            MachinePhysicalProbabilityInfoDlg.addSubmit();
        }
        if($("#operation").val()=='update'){
            // alert(2)
            MachinePhysicalProbabilityInfoDlg.editSubmit();
        }
    }
});