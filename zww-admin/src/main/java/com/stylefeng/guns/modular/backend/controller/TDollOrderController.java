package com.stylefeng.guns.modular.backend.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.common.constant.factory.PageFactory;
import com.stylefeng.guns.common.persistence.dao.TDollOrderItemMapper;
import com.stylefeng.guns.common.persistence.dao.TDollOrderMapper;
import com.stylefeng.guns.common.persistence.model.TDoll;
import com.stylefeng.guns.common.persistence.model.TDollOrderItem;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.modular.backend.warpper.TDollOrderItemWarpper;
import com.stylefeng.guns.modular.backend.warpper.TDollOrderWarpper;
import com.stylefeng.guns.modular.backend.warpper.TDollWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.mutidatasource.DSEnum;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.persistence.model.TDollOrder;
import com.stylefeng.guns.modular.backend.service.ITDollOrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 待发货列表控制器
 *
 * @author fengshuonan
 * @Date 2018-01-30 17:05:25
 */
@Controller
@RequestMapping("/tDollOrder")
public class TDollOrderController extends BaseController {

    private String PREFIX = "/backend/tDollOrder/";

    @Autowired
    private ITDollOrderService tDollOrderService;

    @Autowired
    private TDollOrderItemMapper tDollOrderItemMapper;

    /**
     * 跳转到待发货列表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tDollOrder.html";
    }

    /**
     * 跳转到已发货列表首页
     */
    @RequestMapping("/outGoogs")
    public String outGoods() {
        return PREFIX + "tDollOrderOut.html";
    }


    /**
     *
     * 跳转订单信息
     */
    @RequestMapping("/goodsDetail")
    public String goodsDetail(String orderNum,Model model) {
        model.addAttribute("item",orderNum);
        return PREFIX + "goodsDetail.html";
    }



    /**
     *
     * 订单信息列表
     */
    @RequestMapping(value = "/goodsDetailLists")
    @ResponseBody
    public Object goodsDetailList(String orderNum) {

        TDollOrder tDollOrder = tDollOrderService.selectByorderNum(orderNum);
        Integer orderId = null;
        if(tDollOrder != null){
            orderId = tDollOrder.getId();
        }
        Page<TDollOrderItem> page = new PageFactory<TDollOrderItem>().defaultPage();
        List<Map<String, Object>> result = tDollOrderItemMapper.selectLists(page, orderId);
        page.setRecords((List<TDollOrderItem>)new TDollOrderItemWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 跳转到添加待发货列表
     */
    @RequestMapping("/tDollOrder_add")
    public String tDollOrderAdd() {
        return PREFIX + "tDollOrder_add.html";
    }

    /**
     * 跳转到修改待发货列表
     */
     
    @RequestMapping("/tDollOrder_update/{tDollOrderId}")
    public String tDollOrderUpdate(@PathVariable Integer tDollOrderId, Model model) {
        TDollOrder tDollOrder = tDollOrderService.selectById(tDollOrderId);
        model.addAttribute("item",tDollOrder);
        LogObjectHolder.me().set(tDollOrder);
        return PREFIX + "tDollOrder_edit.html";
    }

    /**
     * 获取待发货列表列表
     */
     
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String memberId,String phone) {
        Page<TDollOrder> page = new PageFactory<TDollOrder>().defaultPage();
        List<Map<String, Object>> result = tDollOrderService.selectTDollOrder(page,memberId,phone);
        page.setRecords((List<TDollOrder>)new TDollOrderWarpper(result).warp());
        return super.packForBT(page);
    }


    /**
     * 获取已发货列表列表
     */

    @RequestMapping(value = "/outList")
    @ResponseBody
    public Object outList(String memberId,String phone) {
        Page<TDollOrder> page = new PageFactory<TDollOrder>().defaultPage();
        List<Map<String, Object>> result = tDollOrderService.selectTDollOrderOut(page,memberId,phone);
        page.setRecords((List<TDollOrder>)new TDollOrderWarpper(result).warp());
        return super.packForBT(page);
    }


    /**
     * 新增待发货列表
     */
     
//    @RequestMapping(value = "/add")
//    @ResponseBody
//    public Object add(TDollOrder tDollOrder) {
//        tDollOrderService.insert(tDollOrder);
//        return super.SUCCESS_TIP;
//    }


    /**
     * 扣留违规娃娃
     */

    @RequestMapping(value = "/callInDoll")
    @ResponseBody
    public Object callInDoll(@RequestParam Integer tDollOrderId) {
        TDollOrder tDollOrder = new TDollOrder();
        tDollOrder.setId(tDollOrderId);
        tDollOrder.setModifiedBy(ShiroKit.getUser().getId());
        tDollOrder.setModifiedDate(new Date());
        tDollOrder.setStatus("违规收回");
        tDollOrderService.updateById(tDollOrder);
        return super.SUCCESS_TIP;
    }

    /**
     * 寄存娃娃直接兑换币
     */

    @RequestMapping(value = "/dollBackCoins")
    @ResponseBody
    public Object dollBackCoins(@RequestParam Integer tDollOrderId,@RequestParam String memberId) {
        tDollOrderService.dollBackCoins(tDollOrderId,memberId);
        return super.SUCCESS_TIP;
    }


    /**
     * 跳转到添加娃娃·详情
     */
    @RequestMapping("/toAddDoll/{memberId}")
    public String toAddDoll(@PathVariable String memberId, Model model) {
        model.addAttribute("memberId",memberId);
        return PREFIX + "tDollOrder_add_doll.html";
    }

    /**
     * 添加娃娃
     */
    @RequestMapping(value = "/backDoll")
    @ResponseBody
    public Object backDoll(TDollOrder tDollOrder) {
        tDollOrderService.backDoll(tDollOrder);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除待发货列表
     */
     
//    @RequestMapping(value = "/delete")
//    @ResponseBody
//    public Object delete(@RequestParam Integer tDollOrderId) {
//        tDollOrderService.deleteById(tDollOrderId);
//        return SUCCESS_TIP;
//    }

    /**
     * 修改待发货列表
     */
     
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TDollOrder tDollOrder) {
        tDollOrder.setDeliverDate(new Date());
        tDollOrder.setModifiedDate(new Date());
        tDollOrder.setStatus("已发货");
        tDollOrderService.updateById(tDollOrder);
        return super.SUCCESS_TIP;
    }

    /**
     * 待发货列表详情
     */
     
    @RequestMapping(value = "/detail/{tDollOrderId}")
    @ResponseBody
    public Object detail(@PathVariable("tDollOrderId") Integer tDollOrderId) {
        return tDollOrderService.selectById(tDollOrderId);
    }
}
