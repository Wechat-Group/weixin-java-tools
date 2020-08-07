package com.github.binarywang.wxpay.bean.applyment.enums;

/**
 * 返回特约商户的结算账户-汇款验证结果枚举类
 *
 * @author zhouyognshen
 */
public enum SettlementVerifyResultEnum {
    /**
     * 系统汇款验证中，商户可发起提现尝试。
     */
    VERIFYING,
    /**
     * 系统成功汇款，该账户可正常发起提现。
     */
    VERIFY_SUCCESS,
    /**
     * 系统汇款失败，该账户无法发起提现，请检查修改。
     */
    VERIFY_FAIL,
    ;

}
