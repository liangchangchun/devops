package com.zww.test.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "activity_sku")
public class ActivitySku {
	
	@Id
	@Column(name = "id")
    private Long id;
	@Column(name = "activity_info_id")
    private Long activityInfoId;
    @Column(name = "activity_rule_id")
    private Long activityRuleId;
    @Column(name = "sku_id")
    private Long skuId;
    @Column(name = "sku_code")
    private String skuCode;
    @Column(name = "spu_code")
    private String spuCode;
    @Column(name = "sku_name")
    private String skuName;
    @Column(name = "specs_values")
    private String specsValues;
    @Column(name = "main_img")
    private String mainImg;
    @Column(name = "one_category_name")
    private String oneCategoryName;
    @Column(name = "two_category_name")
    private String twoCategoryName;
    @Column(name = "three_category_name")
    private String threeCategoryName;
    @Column(name = "sku_type")
    private Integer skuType;
    @Column(name = "supplier_id")
    private Long supplierId;
    @Column(name = "supplier_name")
    private String supplierName;
    @Column(name = "inventory_num")
    private Integer inventoryNum;
    @Column(name = "activity_num")
    private Integer activityNum;
    @Column(name = "activity_us_num")
    private Integer activityUsNum;
    @Column(name = "activity_price")
    private BigDecimal activityPrice;
    @Column(name = "sale_price")
    private BigDecimal salePrice;
    @Column(name = "gift_num")
    private Integer giftNum;
    @Column(name = "cost_price")
    private BigDecimal costPrice;
    @Column(name = "input_tax")
    private BigDecimal inputTax;
    @Column(name = "output_tax")
    private BigDecimal outputTax;
    @Column(name = "operating_costs")
    private BigDecimal operatingCosts;
    @Column(name = "red_package")
    private BigDecimal redPackage;
    @Column(name = "net_profit_rate")
    private BigDecimal netProfitRate;
    @Column(name = "tariff_formula")
    private Long tariffFormula;
    @Column(name = "max_num")
    private Integer maxNum;
    @Column(name = "send_oil_point")
    private Integer sendOilPoint;
    @Column(name = "pool_code")
    private String poolCode;
    @Column(name = "pool_roportion")
    private BigDecimal poolRoportion;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "modify_time")
    private Date modifyTime;
    @Column(name = "creator")
    private Long creator;
    @Column(name = "modifier")
    private Long modifier;
    @Column(name = "deleted")
    private Integer deleted;
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 计算可用库存
     * @return
     */
    public Integer getAvailableActivityStock() {
        if (activityNum==null){
            activityNum=0;
        }
        if (activityUsNum==null){
            activityUsNum=0;
        }
        return activityNum-activityUsNum;
    }


}