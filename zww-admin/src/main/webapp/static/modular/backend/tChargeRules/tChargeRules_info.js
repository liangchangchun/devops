/**
 * 初始化充值规则列表详情对话框
 */
var TChargeRulesInfoDlg = {
    tChargeRulesInfoData : {},
    validateFields: {
        chargeName: {
            validators: {
                notEmpty: {
                    message: '名称不能为空'
                }
            }
        },
        coinsCharge: {
            validators: {
                notEmpty: {
                    message: '金币不能为空'
                }
            }
        },
        orderby: {
            validators: {
                notEmpty: {
                    message: '排序不能为空'
                }
            }
        },
        chargeTimesLimit: {
            validators: {
                notEmpty: {
                    message: '限购次数不能为空'
                }
            }
        },
        superTicketCharge: {
            validators: {
                notEmpty: {
                    message: '钻石不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
TChargeRulesInfoDlg.clearData = function() {
    this.tChargeRulesInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TChargeRulesInfoDlg.set = function(key, val) {
    this.tChargeRulesInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TChargeRulesInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TChargeRulesInfoDlg.close = function() {
    parent.layer.close(window.parent.TChargeRules.layerIndex);
}
//
// /**
//  * 控制显隐
//  */
// TChargeRulesInfoDlg.showAndHidden = function() {
//     // this.hiddenInput();
//     $("#chargeName").hide();
//     alert(99);
// }
// /**
//  * 显示控件
//  */
// TChargeRulesInfoDlg.showInput = function() {
//     $("#chargeName").show();
// }
// /**
//  * 隐藏控件
//  */
// TChargeRulesInfoDlg.hiddenInput = function() {
//     $("#chargeName").hide();
// }

/**
 * 收集数据
 */
TChargeRulesInfoDlg.collectData = function() {
    this
    .set('id')
    .set('chargePrice')
    .set('coinsCharge')
    .set('coinsOffer')
    .set('discount')
    .set('description')
    .set('createdDate')
    .set('createdBy')
    .set('modifiedDate')
    .set('modifiedBy')
    .set('chargeType')
    .set('chargeName')
    .set('orderby')
    .set('cionsFirst')
    .set('chargeTimesLimit')
    .set('chargeDateLimit')
    .set('rulesStatus')
    .set('superTicketCharge')
    ;
}

/**
 * 提交添加
 */
TChargeRulesInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tChargeRules/add", function(data){
        Feng.success("添加成功!");
        window.parent.TChargeRules.table.refresh();
        TChargeRulesInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tChargeRulesInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TChargeRulesInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tChargeRules/update", function(data){
        Feng.success("修改成功!");
        window.parent.TChargeRules.table.refresh();
        TChargeRulesInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tChargeRulesInfoData);
    ajax.start();
}



/**
 * 加载默认值
 */
TChargeRulesInfoDlg.init = function(column,value){
    if($("#"+column+"Value").val() == undefined){
        $("#"+column).val(0);
    }else{
        $("#"+column).val($("#"+column+"Value").val());
    }
}

/**
 * 修改初始化表单
 */
TChargeRulesInfoDlg.editInit = function() {
    //初始化banner类型
    TChargeRulesInfoDlg.init("chargeType");
    TChargeRulesInfoDlg.init("rulesStatus");
}

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        if($("#operation").val()=='add'){
            TChargeRulesInfoDlg.addSubmit();
        }
        if($("#operation").val()=='update'){
            TChargeRulesInfoDlg.editSubmit();
        }
    }
});

$(function() {
    Feng.initValidator("TChargeRulesInfoForm", TChargeRulesInfoDlg.validateFields);
    TChargeRulesInfoDlg.editInit();
});
