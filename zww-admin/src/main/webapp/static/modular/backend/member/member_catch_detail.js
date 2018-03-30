/**
 * 初始化catch detail详情对话框
 */
var MemberCatchDetail = {
	id: "catchDetailTable",	//表格id
	seItem: null,		//选中的条目
	table: null,
	layerIndex: -1
};

/**
 * 初始化表格的列
 */
MemberCatchDetail.initColumn = function () {
    return [
    		{field: 'selectItem', radio: true, visible: false},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            // {title: '用户id', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '机器id', field: 'dollId', visible: true, align: 'center', valign: 'middle'},
            {title: '抓取时间', field: 'catchDate', visible: true, align: 'center', valign: 'middle'},
            {title: '抓取状态', field: 'catchStatus', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    if(value == '抓取成功'){
                        return '<span class="label label-danger radius">'+value+'</span>';
                    }else{
                        return '<span class="label label-success radius">'+value+'</span>';
                    }
                }
            },
            {title: '视频地址', field: 'videoUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '游戏编号', field: 'gameNum', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 查询列表
 */
MemberCatchDetail.search = function () {
    var queryData = {};
    queryData['userId'] = $("#userId").val();
    queryData['beginDate'] = $("#beginDate").val();
    queryData['endtime'] = $("#endtime").val();
    queryData['catchStatus'] = $("#catchStatus").val();
    MemberCatchDetail.catchNum();
    MemberCatchDetail.catchSuccessNum();
    MemberCatchDetail.table.refresh({query: queryData});
};

//jquery实现回车键触发事件
$(document).keyup(function(event){
    if(event.keyCode ==13){
        MemberCatchDetail.search();
    }
});

/**
 * 抓取次数
 */
MemberCatchDetail.catchNum = function(){
    var ajax = new $ax(Feng.ctxPath + "/member/catchNum", function (data) {
        $("#catchNum").val(data);
    }, function (data) {
        Feng.error("失败!" + data.responseJSON.message + "!");
    });
    ajax.set("memberId",$("#memberId").val());
    ajax.set("beginDate",$("#beginDate").val());
    ajax.set("endtime",$("#endtime").val());
    ajax.start();
};

/**
 * 抓取成功次数
 */
MemberCatchDetail.catchSuccessNum = function(){
    var ajax = new $ax(Feng.ctxPath + "/member/catchSuccessNum", function (data) {
        $("#catchSuccessNum").val(data);
    }, function (data) {
        Feng.error("失败!" + data.responseJSON.message + "!");
    });
    ajax.set("memberId",$("#memberId").val());
    ajax.set("beginDate",$("#beginDate").val());
    ajax.set("endtime",$("#endtime").val());
    ajax.start();
};


/**
 * 加载抓取记录
 */
$(function () {
    MemberCatchDetail.catchNum();
    MemberCatchDetail.catchSuccessNum();
    var defaultColunms = MemberCatchDetail.initColumn();
    var table = new BSTable(MemberCatchDetail.id, "/member/catchDetail/list", defaultColunms);
    table.setPaginationType("server");
    var queryData = {};
    queryData['userId'] = $("#userId").val();
    table.setQueryParams(queryData);
    MemberCatchDetail.table = table.init();
    
});