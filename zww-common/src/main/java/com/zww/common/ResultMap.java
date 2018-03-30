package com.zww.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by SUN on 2017/12/29.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultMap implements Serializable {

    private static final long serialVersionUID = 1L;

    //默认成功
    private boolean success = Enviroment.RETURN_SUCCESS;
    private String statusCode = Enviroment.RETURN_SUCCESS_CODE;
    //反馈信息
    private String message;
    //token
    private String token;
    //数据
    private Object resultData;


    /**
     * 成功操作
     *
     * @param message 成功信息
     */
    public ResultMap(String message) {
        this.message = message;
    }

    /**
     * 成功操作
     * 带数据
     *
     * @param resultData 参数信息
     */
    public ResultMap(String message, Object resultData) {
        this.message = message;
        this.resultData = resultData;
    }

    /**
     * 失败异常操作
     *
     * @param statusCode 状态码
     * @param message    异常信息
     */
    public ResultMap(String statusCode, String message) {
        this.success = Enviroment.RETURN_FAILE;
        this.statusCode = statusCode;
        this.message = message;
    }

    /**
     * 成功返回String操作
     *
     * @param statusCode 状态码
     * @param message    异常信息
     */
    public ResultMap(boolean success,String message, String resultData) {
        this.success = success;
        this.message = message;
        this.resultData = resultData;
    }


    public void setMessage(String message) {
        this.message = message;
    }


}
