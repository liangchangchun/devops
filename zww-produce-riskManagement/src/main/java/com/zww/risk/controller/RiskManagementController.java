package com.zww.risk.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zww.api.model.RiskManagement;
import com.zww.api.service.RiskManagementService;
import com.zww.api.util.StringUtils;
import com.zww.risk.dao.RiskManagementDao;

/**
 * 风控微服务
 * @author Administrator
 *
 */
@RefreshScope
@RestController
public class RiskManagementController implements  RiskManagementService{
	
	@Autowired
	RiskManagementDao riskManagementDao;

	@Override
	public Integer register(@RequestParam(value = "memberId") Integer memberId,@RequestParam(value = "imei") String imei,@RequestParam(value = "ipAdrress") String ipAdrress) {
		 try {
	            //获取用户风控信息
	            RiskManagement baseRiskManagement = riskManagementDao.selectByMemberId(memberId);
	            if (baseRiskManagement == null) {
	                //创建风控信息
	                riskManagementDao.init(memberId);
	                baseRiskManagement = riskManagementDao.selectByMemberId(memberId);
	            }
	            Set<String> imeis = new HashSet<>();
	            imeis.add(baseRiskManagement.getIMEI1());
	            imeis.add(baseRiskManagement.getIMEI2());
	            imeis.add(baseRiskManagement.getIMEI3());
	            Set<String> ips = new HashSet<>();
	            ips.add(baseRiskManagement.getIP1());
	            ips.add(baseRiskManagement.getIP2());
	            ips.add(baseRiskManagement.getIP3());
	            int result = 1;
	            RiskManagement riskManagement = new RiskManagement();
	            riskManagement.setId(baseRiskManagement.getId());
	            if (StringUtils.isNotEmpty(imei) && !imeis.contains(imei)) {
	                result++;
	                riskManagement.setIMEI1(imei);
	                String oldImei1 = baseRiskManagement.getIMEI1();
	                if (StringUtils.isNotEmpty(oldImei1)) {
	                    riskManagement.setIMEI2(oldImei1);
	                    String oldImei2 = baseRiskManagement.getIMEI2();
	                    if (StringUtils.isNotEmpty(oldImei2)) {
	                        riskManagement.setIMEI3(oldImei2);
	                    }
	                }
	            }
	            if (StringUtils.isNotEmpty(ipAdrress) && !ips.contains(ipAdrress)) {
	                result++;
	                riskManagement.setIP1(ipAdrress);
	                String oldIp1 = baseRiskManagement.getIP1();
	                if (StringUtils.isNotEmpty(oldIp1)) {
	                    riskManagement.setIP2(oldIp1);
	                    String oldIp2 = baseRiskManagement.getIP2();
	                    if (StringUtils.isNotEmpty(oldIp2)) {
	                        riskManagement.setIP3(oldIp2);
	                    }
	                }
	            }
	            //保存风控信息
	            if (result > 1) {
	                result = riskManagementDao.updateById(riskManagement);
	            }
	            return result;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return 0;
	        }
	}

	@Override
	public boolean isOneIMEI(@RequestParam(value = "memberId") Integer memberId,@RequestParam(value = "memberId2") Integer memberId2) {
		 if (memberId.equals(memberId2)) {
	            return true;
	        }
	        RiskManagement byMemberId = riskManagementDao.selectByMemberId(memberId);
	        List<String> list = new ArrayList<>();
	        if (StringUtils.isNotEmpty(byMemberId.getIMEI1()) && !"IMEI".equals(byMemberId.getIMEI1())) {
	            list.add(byMemberId.getIMEI1());
	        }
	        if (StringUtils.isNotEmpty(byMemberId.getIMEI2()) && !"IMEI".equals(byMemberId.getIMEI2())) {
	            list.add(byMemberId.getIMEI2());
	        }
	        if (StringUtils.isNotEmpty(byMemberId.getIMEI3()) && !"IMEI".equals(byMemberId.getIMEI3())) {
	            list.add(byMemberId.getIMEI3());
	        }
	        RiskManagement byMemberId2 = riskManagementDao.selectByMemberId(memberId2);
	        List<String> list2 = new ArrayList<>();
	        if (StringUtils.isNotEmpty(byMemberId2.getIMEI1()) && !"IMEI".equals(byMemberId2.getIMEI1())) {
	            list2.add(byMemberId2.getIMEI1());
	        }
	        if (StringUtils.isNotEmpty(byMemberId2.getIMEI2()) && !"IMEI".equals(byMemberId2.getIMEI2())) {
	            list2.add(byMemberId2.getIMEI2());
	        }
	        if (StringUtils.isNotEmpty(byMemberId2.getIMEI3()) && !"IMEI".equals(byMemberId2.getIMEI3())) {
	            list2.add(byMemberId2.getIMEI3());
	        }
	        if (list.size() == 0 || list2.size() == 0) {
	            return false;
	        }
	        for (String s : list2) {
	            System.out.println(s);
	            if (list.contains(s)) {
	                return true;
	            }
	        }
	        return false;
	}

	@Override
	public Integer selectIMEICount(@RequestParam(value = "imei") String imei) {
		return riskManagementDao.selectIMEICount(imei);
	}

}
