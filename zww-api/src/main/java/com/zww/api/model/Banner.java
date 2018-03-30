package com.zww.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String description;//描述

    private String imageUrl;//图片url

    private String hyperlink;//跳转链接

    private Boolean activeFlg;//是否有效

    private String type;//banner类型

    private Integer sorts;//排序

    private Date createdDate;//创建时间

    private Integer createdBy;//创建人

    private Date modifiedDate;//修改时间

    private Integer modifiedBy;//修改人

    private Date validStartDate;//过期开始时间

    private Date validEndDate;//过期结束时间

    private Integer linkType;//超链接类型（0 跳h5 , 1 跳支付页，2 跳原生页 3 跳具体支付）

    private Integer payIndex;//支付第几个套餐（默认 1）

    private Integer qqGroupNum;//qq群号

    private String qqGroupKey;//qq群key

    private int packageName;//包名

}
