package com.stylefeng.guns.common.constant.state;
/**
 * 是否完成枚举
 * @author Administrator
 *
 */
public enum FinishType {
	FINISH(1,"已完成"),
	NO_FINISH(0,"未完成");

	Integer val;
    String message;

    FinishType(Integer val, String message) {
        this.val = val;
        this.message = message;
    }

	public Integer getVal() {
		return val;
	}

	public void setVal(Integer val) {
		this.val = val;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
	  public static String valueStrOf(Integer value) {
	        if (value == null) {
	            return NO_FINISH.getMessage();
	        } else {
	            for (FinishType bizType : FinishType.values()) {
	                if (bizType.getVal()==value) {
	                    return bizType.getMessage();
	                }
	            }
	            return NO_FINISH.getMessage();
	        }
	    }
	  
}
