package com.zww.test.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zww.test.Respository.ActivitySkuRepository;
import com.zww.test.entity.ActivitySku;
import com.zww.test.service.OrderNoService;
import com.zww.test.util.RedpackNetUtil;

@RestController
public class OrderNoInfoController {
	
	@Autowired
	OrderNoService orderNoService;
	@Autowired
	ActivitySkuRepository activitySkuRepository;

	  @GetMapping(value = "/getOrderNo")
	  @ResponseBody
	  public String index() {
	        return orderNoService.getOrderNoInfo();
	  }
	  
	  @GetMapping(value = "/getRedpackNet")
	  @ResponseBody
	  public String getRedpackNet() {
		 List<ActivitySku>  list = activitySkuRepository.findAll();
		 for (ActivitySku sku :list) {
			 BigDecimal salePrice = sku.getSalePrice();
			 BigDecimal redPackage = sku.getRedPackage();
			 BigDecimal costPrice = sku.getCostPrice();
			 BigDecimal outputTax = sku.getOutputTax();
			 BigDecimal poolRoportion = sku.getPoolRoportion();
			 BigDecimal inputTax = sku.getInputTax();
			 BigDecimal operatingCosts = sku.getOperatingCosts();
			 BigDecimal netProfitRate = sku.getNetProfitRate();
			 RedpackNetUtil.congsumeMaoNet(salePrice, redPackage, costPrice, outputTax, poolRoportion, inputTax, operatingCosts, netProfitRate);
		 }
	     return "计算完成";
	  }
}
