/**
 * 待处理申诉管理初始化
 */
var MemberComplaint = {
    id: "MemberComplaintDoneTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MemberComplaint.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            // {title: '游戏编号', field: 'gameNum', visible: true, align: 'center', valign: 'middle'},
            // {title: '用户id', field: 'memberId', visible: true, align: 'center', valign: 'middle'},
            {title: '渠道', field: 'complaintChannel', visible: true, align: 'center', valign: 'middle'},
            {title: '用户编号', field: 'memberNum', visible: true, align: 'center', valign: 'middle'},
            {title: '用户名', field: 'memberName', visible: true, align: 'center', valign: 'middle'},
            {title: '用户手机', field: 'memberPhone', visible: true, align: 'center', valign: 'middle'},
            // {title: '娃娃机id', field: 'dollId', visible: true, align: 'center', valign: 'middle'},
            {title: '娃娃机图', field: 'dollImg', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    return '<img  src="'+value+'" width="80" class="img-rounded" />';
                }
            },
            {title: '娃娃机名', field: 'dollName', visible: true, align: 'center', valign: 'middle'},
            {title: '消耗金币', field: 'dollCions', visible: true, align: 'center', valign: 'middle'},
            // {title: '抓取记录id', field: 'memberCatchId', visible: true, align: 'center', valign: 'middle'},
            {title: '抓取时间', field: 'memberCatchDate', visible: true, align: 'center', valign: 'middle'},
            {title: '抓取状态', field: 'memberCatchResult', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    if(value == '抓取成功'){
                        return '<span class="label label-danger radius">'+value+'</span>';
                    }else{
                        return '<span class="label label-success radius">'+value+'</span>';
                    }
                }
            },
            {title: '视频', field: 'videoURL', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    return '<a href="javascript:;" onclick="MemberComplaint.video(\'视频\',\'/memberComplaint\/video\',\''+value+'\',\'300\',\'450\')">播放</a>';
                }
            },
            {title: '申诉原因', field: 'complaintReason', visible: true, align: 'center', valign: 'middle'},
            {title: '审核状态', field: 'checkState', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    if(value == -1) return '驳回';
                    if(value == 1) return '返币，钻';
                    if(value == 2) return '补娃娃';
                }
            },
            {title: '审核理由', field: 'checkReason', visible: true, align: 'center', valign: 'middle'},
            {title: '申诉日期', field: 'creatDate', visible: true, align: 'center', valign: 'middle'},
            // {title: '创建人', field: 'creatBy', visible: true, align: 'center', valign: 'middle'},
            {title: '处理时间', field: 'modifyDate', visible: true, align: 'center', valign: 'middle'},
            // {title: '修改人', field: 'modifyBy', visible: true, align: 'center', valign: 'middle'},
            {title: '操作人', field: 'person', visible: true, align: 'center', valign: 'middle'},
            {title: 'vip等级', field: 'vipGroup', visible: true, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
MemberComplaint.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MemberComplaint.seItem = selected[0];
        return true;
    }
};

/**
 * 播放视频
 */
MemberComplaint.video = function (title,url,videoUrl,w,h) {

    url = Feng.ctxPath + url+"?id="+videoUrl;
    layer_show(title,url,800,400);

}


/**
 * 查询已处理申诉列表
 */
MemberComplaint.search = function () {
    var queryData = {};
    queryData['memberID'] = $("#memberID").val();
    queryData['channelNum'] = $("#channelNum").val();
    queryData['catchStates'] = $("#catchStates").val();
    queryData['checkState'] = $("#checkState").val();
    MemberComplaint.table.refresh({query: queryData});
};

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        MemberComplaint.search();
    }
});


$(function () {
    var defaultColunms = MemberComplaint.initColumn();
    var table = new BSTable(MemberComplaint.id, "/memberComplaint/doneList", defaultColunms);
    table.setPaginationType("server");
    var queryData = {};
    queryData['catchStates'] = '未成功';
    table.setQueryParams(queryData);
    MemberComplaint.table = table.init();
});
