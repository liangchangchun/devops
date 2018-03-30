package com.zww.api.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by SUN on 2018/3/2.
 * 用户风控信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiskManagement implements Serializable {

	private static final long serialVersionUID = -5472217870143053582L;
	private int id;
    private Integer userId;
    private String IMEI1;
    private String IMEI2;
    private String IMEI3;
    private String IP1;
    private String IP2;
    private String IP3;

}
