/**
 * banner管理初始化
 */
var TBanner = {
    id: "TBannerTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TBanner.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '图片', field: 'imageUrl', visible: true, align: 'center', valign: 'middle',
            	  formatter:function (value,row,index) {  
                      return '<a class="maincolor" href="javascript:;" onclick="TBanner.upload(\'广告头像\',\'tBanner\/toUpload\','+row.id+',\'\',\'350\')"><img  src="'+value+'" width="80" class="img-rounded" /></a>';  
                  }  
            },
            {title: '描述', field: 'description', visible: true, align: 'center', valign: 'middle'},
            {title: 'banner类型', field: 'type', visible: true, align: 'center', valign: 'middle',
            	formatter:function (value,row,index) {  
                    if (value==0) return "banner";
                    if (value==1) return "悬浮窗";
                    if (value==2) return "启动页";
                    if (value==3) return "广告弹窗";
                } 
            },
          //  {title: '超链接', field: 'hyperlink', width:'20' , visible: true, align: 'center', valign: 'middle'},
            {title: '是否生效', field: 'activeFlg', visible: true, align: 'center', valign: 'middle',
            	formatter:function (value,row,index) {  
                    if (value) {
                    	return "是";
                    }
                    else{
                    	return "否";
                    }
                } 
            },
            //超链接状态(0链接，1支付页，2.原生，3，支付, 4.身份验证， 5.qq群，6.小说登录)
            {title: '超链接类型', field: 'linkType', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    if (value == 0)  return "跳转链接";
                    if (value == 1)  return "跳转支付页";
                    if (value == 2)  return "跳转原生";
                    if (value == 3)  return "跳转调起支付";
                    if (value == 4)  return "身份验证";
                    if (value == 5)  return "qq群";
                    if (value == 6)  return "小说登录";
                }
            },
            {title: '支付第几个套餐', field: 'payIndexName', visible: true, align: 'center', valign: 'middle'},
            {title: '排序', field: 'sorts', visible: true, align: 'center', valign: 'middle'},
            {title: 'qq群号', field: 'qqGroupNum', visible: true, align: 'center', valign: 'middle'},
            {title: 'qq群key', field: 'qqGroupKey', visible: true, align: 'center', valign: 'middle'},
            //{title: '', field: 'createdDate', visible: true, align: 'center', valign: 'middle'},
            //{title: '', field: 'createdBy', visible: true, align: 'center', valign: 'middle'},
            //{title: '', field: 'modifiedDate', visible: true, align: 'center', valign: 'middle'},
            //{title: '', field: 'modifiedBy', visible: true, align: 'center', valign: 'middle'},
            {title: '过期开始时间', field: 'validStartDate', visible: true, align: 'center', valign: 'middle'},
            {title: '过期结束时间', field: 'validEndDate', visible: true, align: 'center', valign: 'middle'},
            {title: '包名', field: 'packageName', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    if (value == 1) {
                        return "365";
                    }else{
                        return "小妖精";
                    }
                }
            }
    ];
};

/**
 * 图片上传 
 */
TBanner.upload = function (title,url,id,w,h) {
	//alert("upload:"+title+",url="+url+",id="+id);
	url = url+"?id="+id;
	layer_show(title,url,w,h);
}
/**
 * 检查是否选中
 */
TBanner.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TBanner.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加banner
 */
TBanner.openAddTBanner = function () {
    var index = layer.open({
        type: 2,
        title: '添加banner',
        area: ['900px', '440px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tBanner/tBanner_add'
    });
    this.layerIndex = index;
};

/**
 * 修改banner详情
 */
TBanner.openTBannerDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'banner详情',
            area: ['900px', '440px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/tBanner/tBanner_update/' + TBanner.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除banner
 */
TBanner.delete = function () {
    if (this.check()) {
    	var tBannerId = this.seItem.id;
    	 layer.confirm('确认要删除吗？', {
             btn : [ '确定', '取消' ]//按钮
         }, function(index) {
        	 layer.close(index);
        	
             //此处请求后台程序，下方是成功后的前台处理……
             var ajax = new $ax(Feng.ctxPath + "/tBanner/delete", function (data) {
                 Feng.success("删除成功!");
                 TBanner.table.refresh();
             }, function (data) {
                 Feng.error("删除失败!" + data.responseJSON.message + "!");
             });
             ajax.set("tBannerId",tBannerId);
             ajax.start();
         }); 
    }
};

/**
 * 查询banner列表
 */
TBanner.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    TBanner.table.refresh({query: queryData});
};

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        TBanner.search();
    }
});

$(function () {
    var defaultColunms = TBanner.initColumn();
    var table = new BSTable(TBanner.id, "/tBanner/list", defaultColunms);
    table.setPaginationType("client");
    TBanner.table = table.init();
});
