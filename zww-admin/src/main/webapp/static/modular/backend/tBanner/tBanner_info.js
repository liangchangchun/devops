/**
 * 初始化banner详情对话框
 */
var TBannerInfoDlg = {
    tBannerInfoData : {},
    validateFields: {
    	description: {
            validators: {
                notEmpty: {
                    message: '描述不能为空'
                }
            }
        },
        hyperlink: {
            validators: {
                notEmpty: {
                    message: '超链接不能为空'
                }
            }
        },
        validStartDate: {
            validators: {
                notEmpty: {
                    message: '过期开始时间不能为空'
                }
            }
        },
        validEndDate: {
            validators: {
                notEmpty: {
                    message: '过期结束时间不能为空'
                }
            }
        },
        sorts: {
            validators: {
                notEmpty: {
                    message: '排序不能为空'
                }
            }
        }
    }
};

/**
 * 验证数据是否为空
 */
TBannerInfoDlg.validate = function () {
    $('#tBannerInfoForm').data("bootstrapValidator").resetForm();
    $('#tBannerInfoForm').bootstrapValidator('validate');
    return $("#tBannerInfoForm").data('bootstrapValidator').isValid();
}

/**
 * 清除数据
 */
TBannerInfoDlg.clearData = function() {
    this.tBannerInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TBannerInfoDlg.set = function(key, val) {
    this.tBannerInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TBannerInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TBannerInfoDlg.close = function() {
    parent.layer.close(window.parent.TBanner.layerIndex);
}

/**
 * 收集数据
 */
TBannerInfoDlg.collectData = function() {
	   this
	    .set('id')
	    .set('description')
	    .set('imageUrl')
	    .set('hyperlink')
	    .set('activeFlg')
	    .set('type')
	    .set('sorts')
	    .set('payIndex')
	    .set('validStartDate')
	    .set('validEndDate')
	    .set('linkType')
	    .set('qqGroupNum')
	    .set('qqGroupKey')
	    .set('packageName')
	    ;
}

/**
 * 提交添加
 */
TBannerInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tBanner/add", function(data){
        Feng.success("添加成功!");
        window.parent.TBanner.table.refresh();
        TBannerInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tBannerInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TBannerInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tBanner/update", function(data){
        Feng.success("修改成功!");
        window.parent.TBanner.table.refresh();
        TBannerInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tBannerInfoData);
    ajax.start();
}
/**
 * 加载默认值
 */
TBannerInfoDlg.init = function(column,value){
	if($("#"+column+"Value").val() == undefined){
	       $("#"+column).val(0);
	   }else{
	       $("#"+column).val($("#"+column+"Value").val());
	}
}
/**
 * 修改初始化表单
 */
TBannerInfoDlg.editInit = function() {
	 //初始化banner类型
	TBannerInfoDlg.init("type");
    TBannerInfoDlg.init("packageName");
    if($("#packageNameValue").val() == undefined){
        $("#packageName").val(1);//初始化默认值
    }else{
        var flg =$("#packageNameValue").val()=="1"?1:2;
        $("#packageName").val(flg);
    }
   if($("#activeFlgValue").val() == undefined){
       $("#activeFlg").val(1);//初始化默认值
   }else{
	   var flg =$("#activeFlgValue").val()=="false"?0:1;
       $("#activeFlg").val(flg);
   }
   TBannerInfoDlg.init("linkType");
   if($("#payIndexValue").val() == undefined){
	   $("#payIndex").attr("value","");
   }else{
	   //alert($("#payIndexValue").val());
	   //alert($("#payIndex").parent().html());
	   //$("#payIndex").attr("ckvalue",$("#payIndexValue").val());
       $("#payIndex").attr("value",$("#payIndexValue").val());
      // alert($("#payIndex").val());
   }
   //日期回显示
   if($("#validStartDateValue").val() == undefined){
   		}else{
       $("#validStartDate").val(formatDate($("#validStartDateValue").val(),'yyyy-MM-dd HH:mm:ss'));
   }
   if($("#validEndDateValue").val() == undefined){
   		}else{
       $("#validEndDate").val(formatDate($("#validEndDateValue").val(),'yyyy-MM-dd HH:mm:ss'));
   }
}

$(function() {
	Feng.initValidator("tBannerInfoForm", TBannerInfoDlg.validateFields);
	TBannerInfoDlg.editInit();
});

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        if($("#operation").val()=='add'){
            // alert(1)
            TBannerInfoDlg.addSubmit();
        }
        if($("#operation").val()=='update'){
            // alert(2)
            TBannerInfoDlg.editSubmit();
        }
    }
});