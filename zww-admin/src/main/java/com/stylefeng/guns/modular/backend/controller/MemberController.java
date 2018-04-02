package com.stylefeng.guns.modular.backend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.stylefeng.guns.common.persistence.dao.*;
import com.stylefeng.guns.common.persistence.model.*;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.base.tips.TipType;
import com.stylefeng.guns.core.util.RedisKeyGenerator;
import com.stylefeng.guns.modular.backend.service.IMemberChargeHistoryService;
import com.stylefeng.guns.modular.backend.service.ITDollOrderService;
import com.stylefeng.guns.modular.backend.service.impl.ChargeOrderServiceImpl;
import com.stylefeng.guns.modular.backend.warpper.ChargeOrderWarpper;
import com.stylefeng.guns.modular.backend.warpper.TDollOrderWarpper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.modular.backend.service.IMemberService;
import com.stylefeng.guns.modular.backend.warpper.DollCatchHistoryWarpper;
import com.stylefeng.guns.modular.backend.warpper.MemberWarpper;

import javax.annotation.Resource;

/**
 * member控制器
 *
 * @author fengshuonan
 * @Date 2018-01-02 19:57:13
 */
@Controller
@RequestMapping("/member")
public class MemberController extends BaseController {

    private String PREFIX = "/backend/member/";
    private String PREFIXS = "/backend/channel/";

    
    @Autowired
    private IMemberService memberService;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private ChargeOrderMapper chargeOrderMapper;
    
    @Autowired
    TDollCatchHistoryMapper tDollCatchHistoryMapper;

    @Autowired
    MemberChannelDeductMapper memberChannelDeductMapper;

    @Autowired
    IMemberChargeHistoryService memberChargeHistoryService;

    @Autowired
    private TDollOrderMapper tDollOrderMapper;


    /**
     * 跳转到member首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "member.html";
    }

    /**
     * 跳转到小妖精channel
     */
    @RequestMapping("/xiaoyaojingChannelList")
    public String xiaoyaojingChannelList() {
        return PREFIXS + "xiaoyaojingChannel.html";
    }

    /**
     * 跳转到channel
     */
    @RequestMapping("/channelList")
    public String channel() {
        return PREFIXS + "channel.html";
    }


    /**
     * 跳转到添加member
     */
    @RequestMapping("/member_add")
    public String memberAdd() {
        return PREFIX + "member_add.html";
    }

    /**
     * 跳转到修改member
     */
    
    @RequestMapping("/member_update/{memberId}")
    public String memberUpdate(@PathVariable Integer memberId, Model model) {
        Account accounts = new Account();
        Account account = accountMapper.selectById(memberId);
        Member member = memberService.selectById(memberId);
        if (account == null){
            accounts.setCoins(member.getCoins());
            accounts.setId(member.getId());
            accounts.setSuperTicket(0);
        }
        else {
            accounts.setCoins(account.getCoins());
            accounts.setId(account.getId());
            accounts.setSuperTicket(account.getSuperTicket());
//            if (account.getCoins() != member.getCoins()){
//                account.setCoins(member.getCoins());
//                account.setSuperTicket(0);
//            }
        }
        model.addAttribute("item",accounts);
        LogObjectHolder.me().set(accounts);
        return PREFIX + "member_edit.html";
    }


    /**
     * 获取member列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer id, String userId,String userName,String registerDate,String lastLoginFrom) {
    	 Page<Member> page = new PageFactory<Member>().defaultPage();
    	 List<Map<String, Object>> result = memberService.selectMember(page,id,userId,userName,registerDate,lastLoginFrom);
    	 page.setRecords((List<Member>)new MemberWarpper(result).warp());
    	 return super.packForBT(page);
    }


    /**
     * 获取channel列表
     */
    @RequestMapping(value = "/channel")
    @ResponseBody
    public Object channelListList(String channelNum,String userId,String userName,String registerDate,String endDate, String lastLoginFrom) {
        Page<Member> page = new PageFactory<Member>().defaultPage();
        List<Map<String, Object>> result = memberService.selectChannel(page,channelNum,userId,userName,registerDate, endDate, lastLoginFrom);
        page.setRecords((List<Member>)new MemberWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 获取小妖精channel列表
     */
    @RequestMapping(value = "/xiaoyaojingChannel")
    @ResponseBody
    public Object xiaoyaojingChannelList(String channelNum,String userId,String userName,String registerDate,String endDate, String lastLoginFrom) {
        Page<Member> page = new PageFactory<Member>().defaultPage();
        List<Map<String, Object>> result = memberService.xiaoyaojingSelectChannel(page,channelNum,userId,userName,registerDate, endDate, lastLoginFrom);
        page.setRecords((List<Member>)new MemberWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 渠道扣量
     * @param
     * @return
     */
    @RequestMapping(value = "/channelDeduct")
    @ResponseBody
    public Object channelDeduct(@RequestParam String memberIds) {

        String[] idsAll = null;
        Integer result = 0;
        //存放id集合
        List<String> idsString = new ArrayList<>();
        if(memberIds != null) {
            idsAll = memberIds.split(",");
        }
        Integer id = null;
        //获取扣量信息
        for (String idd:idsAll) {
            if(idd != null){
                id = Integer.parseInt(idd);
                //去除已扣量的用户
                Integer resultid = memberChannelDeductMapper.selectByUserid(id);
                if (resultid <= 0){
                    Member member = memberService.selectById(id);
                    MemberChannelDeduct memberChannelDeduct = this.channelDeduct(member);
                    result = memberChannelDeductMapper.insert(memberChannelDeduct);
                }
            }
        }
        if(result>0) {
            return SUCCESS_TIP;
        }else {
            return new ErrorTip(TipType.UPLOAD_ERROR.getCode(),TipType.UPLOAD_ERROR.getMessage());
        }
    }


    /**
     * 用户封号
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/closeMember")
    @ResponseBody
    public Object closeMember(@RequestParam Integer memberId) {
        memberService.closeMember(memberId);
        return SUCCESS_TIP;
    }

    /**
     * 解封
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/openMember")
    @ResponseBody
    public Object openMember(@RequestParam Integer memberId) {
        memberService.openMember(memberId);
        return SUCCESS_TIP;
    }

    /**
     * 新增member
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Member member) {
        memberService.insert(member);
        return super.SUCCESS_TIP;
    }
*/
    /**
     * 删除member
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer memberId) {
        memberService.deleteById(memberId);
        return SUCCESS_TIP;
    }
     */

    /**
     * 修改member
     */
    
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Account account) {
        //加币记录
        memberChargeHistoryService.insertChargeHistory(account);
        accountMapper.updateById(account);
        return super.SUCCESS_TIP;
    }

    /**
     * member详情
     */
    
    @RequestMapping(value = "/detail/{memberId}")
    @ResponseBody
    public Object detail(@PathVariable("memberId") Integer memberId) {
        return memberService.selectById(memberId);
    }



    /**
     * 跳转寄存娃娃页
     * @param memberId
     * @param model
     * @return
     */
    @RequestMapping(value = "/goodsDetail/{memberId}")
    public String goodsDetail(@PathVariable("memberId") Integer memberId,Model model) {
        Member member = memberService.selectById(memberId);
        model.addAttribute("item",member);
        return PREFIX + "member_tDollOrder.html";
    }

    /**
     * 寄存中娃娃列表
     */
    @RequestMapping(value = "/goodsDetail/list")
    @ResponseBody
    public Object goodsDetail( String memberId,String phone) {
        Page<TDollOrder> page = new PageFactory<TDollOrder>().defaultPage();
        List<Map<String, Object>> result = tDollOrderMapper.selectTDollOrderMember(page,memberId,phone);
        page.setRecords((List<TDollOrder>)new TDollOrderWarpper(result).warp());
        return super.packForBT(page);
}


    /**
     * 抓取记录详情
     */

    @RequestMapping(value = "/catchDetail/{memberId}")
    public String catchDetail(@PathVariable("memberId") Integer memberId,Model model) {
       // return chargeOrderMapper.selectById(memberId);
    	Member member = memberService.selectById(memberId);
    	Date lastLoginDate =   member.getLastLoginDate();
    	model.addAttribute("lastLoginDate",DateUtil.getTime(lastLoginDate));
        model.addAttribute("item",member);
    	 return PREFIX + "member_catch_detail.html";
    }
    
    /**
     * 获取抓取记录列表
     */
    @RequestMapping(value = "/catchDetail/list")
    @ResponseBody
    public Object catchDetailList(Integer userId,Integer dollId,String catchStatus,String beginDate,String endtime) {
    	 Page<TDollCatchHistory> page = new PageFactory<TDollCatchHistory>().defaultPage();
    	 List<Map<String, Object>> result = tDollCatchHistoryMapper.selectDollCatchHistoryList(page, userId, dollId, catchStatus, beginDate, endtime);
    	 page.setRecords((List<TDollCatchHistory>)new DollCatchHistoryWarpper(result).warp());
    	 return super.packForBT(page);
    }

    /**
     * 充值记录详情
     */

    @RequestMapping(value = "/chargeDetail/{memberId}")
    public String chargeDetail(@PathVariable("memberId") Integer memberId,Model model) {
        Member member = memberService.selectById(memberId);
        model.addAttribute("item",member);
        return PREFIX + "chargeOrder_detail.html";

    }

    /**
     * 获取充值记录列表
     */
    @RequestMapping(value = "/chargeDetail/list")
    @ResponseBody
    public Object chargeDetailList(String memberName,String memberId,Integer chargeruleid,Integer chargeState,String registeDate,String endtime){

        Integer userId = null;
        if(memberId != null && !"".equals(memberId)){
            Member member = memberService.selectIdByMemberId(memberId);
            if(member != null){
                userId = member.getId();
            }
        }
        Page<ChargeOrder> page = new PageFactory<ChargeOrder>().defaultPage();
        List<Map<String, Object>> result = chargeOrderMapper.selectListOneMember(page,memberName,userId,chargeruleid,chargeState,registeDate,endtime);
        page.setRecords((List<ChargeOrder>)new ChargeOrderWarpper(result).warp());
        return super.packForBT(page);

    }


    /**
     * 抓取总次数
     */
    @RequestMapping(value = "/catchNum")
    @ResponseBody
    public Object catchNum(String memberId,String beginDate,String endtime) {
        Integer userId = null;
        if(memberId != null && (!"".equals(memberId))){
            Member member = memberService.selectIdByMemberId(memberId);
            if(member != null){
                userId = member.getId();
            }
        }
        Integer catchNum = tDollCatchHistoryMapper.selectCatchNum(userId,beginDate,endtime);
        if(catchNum == null){
            catchNum = 0;
        }
        return catchNum;
    }

    /**
     * 抓取总次数
     */
    @RequestMapping(value = "/catchSuccessNum")
    @ResponseBody
    public Object catchSuccessNum(String memberId,String beginDate,String endtime) {
        Integer userId = null;
        if(memberId != null && (!"".equals(memberId))){
            Member member = memberService.selectIdByMemberId(memberId);
            if(member != null){
                userId = member.getId();
            }
        }
        Integer catchSuccessNum = tDollCatchHistoryMapper.selectCatchSuccessNum(userId,beginDate,endtime);
        if(catchSuccessNum == null){
            catchSuccessNum = 0;
        }
        return catchSuccessNum;
    }


    /**
     * 总金额orderManage
     */
    @RequestMapping(value = "/allMoney")
    @ResponseBody
    public Object allMoney(String memberName,String memberId,String comboNames,String chargeState,String registeDate,String endtime) {
        Integer userId = null;
        if(memberId != null && (!"".equals(memberId))){
            Member member = memberService.selectIdByMemberId(memberId);
            if(member != null){
                userId = member.getId();
            }
        }
        Double allMoney = chargeOrderMapper.selectAllMoneyOneMember(memberName,userId,comboNames,chargeState,registeDate,endtime);
        if(allMoney == null){
            allMoney = 0.0;
        }
        return allMoney;
    }



    public MemberChannelDeduct channelDeduct(Member member) {
        MemberChannelDeduct  memberChannelDeduct = new MemberChannelDeduct();
        memberChannelDeduct.setUserId(member.getId());
        memberChannelDeduct.setMemberID(member.getMemberID());
        memberChannelDeduct.setName(member.getName());
        memberChannelDeduct.setMobile(member.getMobile());
        memberChannelDeduct.setWeixinId(member.getWeixinId());
        memberChannelDeduct.setGender(member.getGender());
        memberChannelDeduct.setRegisterDate(member.getRegisterDate());
        memberChannelDeduct.setLastLoginDate(member.getLastLoginDate());
        memberChannelDeduct.setRegisterFrom(member.getRegisterFrom());
        memberChannelDeduct.setLastLoginFrom(member.getLastLoginFrom());
        memberChannelDeduct.setRegisterChannel(member.getRegisterChannel());
        memberChannelDeduct.setLoginChannel(member.getLoginChannel());
        memberChannelDeduct.setPhoneModel(member.getPhoneModel());
        memberChannelDeduct.setOnlineFlg(member.getOnlineFlg());
        return memberChannelDeduct;
    }

}
