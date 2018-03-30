package com.zww.api.util;

/**
 * 用户状态类，记录用户所有的状态。
 */
public class BitStatesUtils {
    public final static Long COIN_FIRST_CHARGE = 1L << 0; //用户首充状态码
    public final static Long WEEKS_CARD = 1L << 1; //用户周卡状态码
    public final static Long MONTH_CARD = 1L << 2; //用户月卡状态码
//    public final static Long OP_REAL_AUTH = 1L << 3; // 用户完成实名认证
//    public final static Long OP_VEDIO_AUTH = 1L << 4; // 用户完成视频认证
//    public final static Long HAS_BIDREQUEST_IN_PROCESS = 1L << 5;// 用户有一个借款在申请流程当中
//    public final static Long OP_BIND_BANKINFO= 1L << 6;// 用户有一个借款在申请流程当中
//    public final static Long HAS_WITHDRAW_PROCESS= 1L << 7;// 用户有一个提现在申请流程当中

    /**
     * @param states 所有状态值
     * @param value  需要判断状态值
     * @return 是否存在
     */
    public static boolean hasState(long states, long value) {
        return (states & value) != 0;
    }

    /**
     * @param states 已有状态值
     * @param value  需要添加状态值
     * @return 新的状态值
     */
    public static long addState(long states, long value) {
        if (hasState(states, value)) {
            return states;
        }
        return (states | value);
    }

    /**
     * @param states 已有状态值
     * @param value  需要删除状态值
     * @return 新的状态值
     */
    public static long removeState(long states, long value) {
        if (!hasState(states, value)) {
            return states;
        }
        return states ^ value;
    }
}
