package com.stylefeng.guns.common.constant.state;
/**
 * 上下架枚举
 * @author Administrator
 *
 */
public enum UpperAndLowerShelvesType {
	UPPERS(1,"上架"),
	LOWERS(0,"下架");

	Integer val;
    String message;

    UpperAndLowerShelvesType(Integer val, String message) {
        this.val = val;
        this.message = message;
    }

	public void setVal(Integer val) {
		this.val = val;
	}

	public Integer getVal() {
		return val;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
	  public static String valueStrOf(Integer value) {
	        if (value == null) {
	            return UPPERS.getMessage();
	        } else {
	            for (UpperAndLowerShelvesType bizType : UpperAndLowerShelvesType.values()) {
	                if (bizType.getVal()==value) {
	                    return bizType.getMessage();
	                }
	            }
	            return UPPERS.getMessage();
	        }
	    }
	  
}
