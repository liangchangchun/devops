package com.zww.test.util;

import java.math.BigDecimal;

public class RedpackNetUtil {

	public static void congsumeMaoNet(BigDecimal salePrice ,BigDecimal redPackage,BigDecimal costPrice,BigDecimal outputTax,BigDecimal poolRoportion,BigDecimal inputTax,BigDecimal operatingCosts,BigDecimal netProfitRate) {
			//BigDecimal salePrice = new BigDecimal("188");
			salePrice.setScale(8, BigDecimal.ROUND_HALF_UP);
	        //BigDecimal redPackage = new BigDecimal("0");
			redPackage.setScale(8, BigDecimal.ROUND_HALF_UP);
	        //BigDecimal costPrice = new BigDecimal("101.6");
	        costPrice.setScale(8, BigDecimal.ROUND_HALF_UP);
	        //BigDecimal outputTax = new BigDecimal("0.17");
	        outputTax.setScale(8, BigDecimal.ROUND_HALF_UP);
	        //BigDecimal poolRoportion = new BigDecimal("0.15");
	        poolRoportion.setScale(8, BigDecimal.ROUND_HALF_UP);
	        //BigDecimal inputTax = new BigDecimal("0.17");
	        inputTax.setScale(8, BigDecimal.ROUND_HALF_UP);
	        //BigDecimal operatingCosts = new BigDecimal("0.06");
	        operatingCosts.setScale(8, BigDecimal.ROUND_HALF_UP);
	        BigDecimal redp = getRedpackNet( salePrice , redPackage, costPrice, outputTax, poolRoportion, inputTax, operatingCosts);
	        redp.setScale(8, BigDecimal.ROUND_HALF_UP);
	       // String result = "salePrice :"+ salePrice.doubleValue() +", redPackage:"+ redPackage.doubleValue() +", costPrice:"+costPrice.doubleValue()+", outputTax:"+outputTax.doubleValue()+", poolRoportion:"+poolRoportion.doubleValue()+", inputTax:"+inputTax.doubleValue()+", operatingCosts:"+operatingCosts.doubleValue();
	        String result = "redpack 抵扣红包商品净利率 :" + redp.doubleValue();
	        BigDecimal redp2 = zbingRedpackNet(salePrice , redPackage, costPrice, outputTax, poolRoportion, inputTax, operatingCosts);
	        redp2.setScale(8, BigDecimal.ROUND_HALF_UP);
	        result += "对比结果:" + redp2.doubleValue();
	        result += "是否一致:" + redp.equals(redp2);
	        BigDecimal maoNet = getMaoNet(salePrice , redPackage, costPrice, outputTax, poolRoportion, inputTax, operatingCosts);
	        BigDecimal m1 = new BigDecimal(maoNet.doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP);
	        //maoNet.setScale(2, BigDecimal.ROUND_HALF_UP);
	        result += "------maoNet 毛利:" + m1.doubleValue();
	        BigDecimal maoNet2 = zbingMaoNet(salePrice , redPackage, costPrice, outputTax, poolRoportion, inputTax, operatingCosts);
	        //maoNet2.setScale(2, BigDecimal.ROUND_HALF_UP);
	        BigDecimal m2 = new BigDecimal(maoNet2.doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP);
	        result += "对比结果:" + m2.doubleValue();
	        result += "毛利是否一致:" + m1.equals(m2);
	        BigDecimal m3 = netProfitRate ;
	        result += "对比数据库结果:" + m3.doubleValue();
	        result += "db对比结果:" + m2.equals(m3);
	        printResult(result);
	}
	
	public static synchronized void printResult(String result) {
		System.out.println(result);
	}
	
	
	public static BigDecimal zbingMaoNet(BigDecimal salePrice ,BigDecimal redPackage1,BigDecimal costPrice,BigDecimal outputTax,BigDecimal poolRoportion,BigDecimal inputTax,BigDecimal operatingCosts) {
		//BigDecimal salePrice=activitySku.getActivityPrice();
		 // BigDecimal redPackagePrice=new BigDecimal(redPackage1.doubleValue()).divide(BigDecimal.valueOf(100),4,BigDecimal.ROUND_HALF_UP);
		 BigDecimal redPackagePrice=redPackage1.divide(BigDecimal.valueOf(100),8,BigDecimal.ROUND_HALF_UP);
		//  BigDecimal costPrice=activitySku.getCostPrice();
		//  BigDecimal outputTax =activitySku.getOutputTax();
		//  BigDecimal poolRoportion=activitySku.getPoolRoportion();
		//  BigDecimal inputTax =activitySku.getInputTax();
		//  BigDecimal operatingCosts = activitySku.getOperatingCosts();
		  //净价 =售价-红包抵扣/100
		  BigDecimal price=salePrice.subtract(redPackagePrice);
		  //商品成本=进价
		  //净价-成本
		  BigDecimal arg1 = price.subtract(costPrice);

		  //净价*销项税/(1+销项税)
		  BigDecimal arg2 = ((price.multiply(outputTax)).divide((new BigDecimal(1.0).add(outputTax)),8,BigDecimal.ROUND_HALF_UP));

		  //成本*进项税/(1+进项税)
		  BigDecimal arg3 = ((costPrice.multiply(inputTax)).divide(inputTax.add(new BigDecimal(1.0)),8,BigDecimal.ROUND_HALF_UP));

		  //净价*（运营成本占比+商品池比例）
		  BigDecimal arg4=(price.multiply(poolRoportion.add(operatingCosts))).setScale(8, BigDecimal.ROUND_HALF_UP);
		  //arg4 = arg4.setScale(8, BigDecimal.ROUND_HALF_UP);
		  BigDecimal gross1 = arg1.subtract(arg2).add(arg3).subtract(arg4);
		  BigDecimal gross =gross1.setScale(8,BigDecimal.ROUND_HALF_UP);
       // System.out.println(gross+"======"+gross.divide(salePrice.subtract(redPackage),4,BigDecimal.ROUND_HALF_UP));
        return gross;
	}
	
	public static BigDecimal zbingRedpackNet(BigDecimal salePrice ,BigDecimal redPackage1,BigDecimal costPrice,BigDecimal outputTax,BigDecimal poolRoportion,BigDecimal inputTax,BigDecimal operatingCosts) {
		//BigDecimal salePrice=activitySku.getActivityPrice();
		BigDecimal redPackagePrice=redPackage1.divide(BigDecimal.valueOf(100),8,BigDecimal.ROUND_HALF_UP);
		//  BigDecimal costPrice=activitySku.getCostPrice();
		//  BigDecimal outputTax =activitySku.getOutputTax();
		//  BigDecimal poolRoportion=activitySku.getPoolRoportion();
		//  BigDecimal inputTax =activitySku.getInputTax();
		//  BigDecimal operatingCosts = activitySku.getOperatingCosts();
		  //净价 =售价-红包抵扣/100
		  BigDecimal price=salePrice.subtract(redPackagePrice);
		  //商品成本=进价
		  //净价-成本
		  BigDecimal arg1 = price.subtract(costPrice);

		  //净价*销项税/(1+销项税)
		  BigDecimal arg2 = ((price.multiply(outputTax)).divide((new BigDecimal(1.0).add(outputTax)),8,BigDecimal.ROUND_HALF_UP));

		  //成本*进项税/(1+进项税)
		  BigDecimal arg3 = ((costPrice.multiply(inputTax)).divide(inputTax.add(new BigDecimal(1.0)),8,BigDecimal.ROUND_HALF_UP));

		  //净价*（运营成本占比+商品池比例）
		  BigDecimal arg4=(price.multiply(poolRoportion.add(operatingCosts))).setScale(8, BigDecimal.ROUND_HALF_UP);
		  //arg4 = arg4.setScale(8, BigDecimal.ROUND_HALF_UP);
		  BigDecimal gross1 = arg1.subtract(arg2).add(arg3).subtract(arg4);
		  BigDecimal gross =gross1.setScale(2,BigDecimal.ROUND_HALF_UP);
        //System.out.println(gross+"======"+gross.divide(salePrice.subtract(redPackage),4,BigDecimal.ROUND_HALF_UP));
        return gross.divide(salePrice.subtract(redPackagePrice),4,BigDecimal.ROUND_HALF_UP);
	}
	
	public static BigDecimal getMaoNet(BigDecimal salePrice ,BigDecimal redPackage,BigDecimal costPrice,BigDecimal outputTax,BigDecimal poolRoportion,BigDecimal inputTax,BigDecimal operatingCosts){
		//BigDecimal redpack = new BigDecimal(0);
		BigDecimal base = BigDecimal.valueOf(100);
		BigDecimal one = new BigDecimal(1.0);
		BigDecimal a = sub(salePrice,div(redPackage,base,8));
		BigDecimal b = sub(a,costPrice);
		
		BigDecimal c = div(mul(a,outputTax),add(one,outputTax),8);
		
		BigDecimal d = div(mul(costPrice,inputTax),add(one,inputTax),8);//成本*进项税/(1+进项税)
		//净价*（运营成本占比+商品池比例）
		BigDecimal e = add(operatingCosts,poolRoportion);
		BigDecimal f = sub(c,d); //净价*销项税/(1+销项税)
		BigDecimal g = sub(b,f);
		BigDecimal h = mul(a,e);
		BigDecimal mao = sub(g,h);
		BigDecimal gross =mao.setScale(8,BigDecimal.ROUND_HALF_UP);
		return gross;
		
	}

	public static BigDecimal getRedpackNet(BigDecimal salePrice ,BigDecimal redPackage,BigDecimal costPrice,BigDecimal outputTax,BigDecimal poolRoportion,BigDecimal inputTax,BigDecimal operatingCosts) {
		BigDecimal redpack = new BigDecimal(0);
		BigDecimal base = BigDecimal.valueOf(100);
		BigDecimal one = new BigDecimal(1.0);
		BigDecimal a = sub(salePrice,div(redPackage,base,8));
		BigDecimal b = sub(a,costPrice);
		BigDecimal c = div(outputTax,add(one,outputTax),8);
		BigDecimal d = mul(costPrice,div(inputTax,add(one,inputTax),8));
		BigDecimal e = add(operatingCosts,poolRoportion);
		BigDecimal f = sub(mul(a,c),d);
		BigDecimal g = sub(b,f);
		BigDecimal h = mul(a,e);
		redpack = div(sub(g,h),a,8);
		return redpack;
	}
	
	//减
	public static BigDecimal sub(BigDecimal v1,BigDecimal v2){ 
      return v1.subtract(v2); 
	} 

  public static BigDecimal add(BigDecimal v1,BigDecimal v2){ 
	return v1.add(v2); 
 }

  public static BigDecimal mul(BigDecimal v1,BigDecimal v2){ 
	  return v1.multiply(v2); 
  } 

  public static BigDecimal div(BigDecimal v1,BigDecimal v2,int scale){
	if( scale<0 ){
		throw new IllegalArgumentException("The scale must be a positive integer or zero");
	}
	return v1.divide(v2,scale,BigDecimal.ROUND_HALF_UP);
  }
}
