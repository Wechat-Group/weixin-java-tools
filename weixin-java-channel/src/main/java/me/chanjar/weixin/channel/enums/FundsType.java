package me.chanjar.weixin.channel.enums;

/**
 * 资金类型
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public enum FundsType {

    /** 1 订单支付收入 */
    ORDER_PAY_INCOME(1, "订单支付收入"),
    /** 2 订单手续费 */
    ORDER_FEE(2, "订单手续费"),
    /** 3 退款 */
    REFUND(3, "退款"),
    /** 4 提现 */
    WITHDRAW(4, "提现"),
    /** 5 提现失败退票 */
    WITHDRAW_FAIL(5, "提现失败退票"),
    /** 6 导购分账 */
    GUIDE_SHARE(6, "导购分账"),
    /** 7 联盟分账 */
    LEAGUE_SHARE(7, "联盟分账"),
    /** 8 运费险分账 */
    FREIGHT_SHARE(8, "运费险分账"),
    /** 9 联盟平台抽佣 */
    LEAGUE_COMMISSION(9, "联盟平台抽佣"),
    /** 10 小店抽佣 */
    SHOP_COMMISSION(10, "小店抽佣"),
    /** 99 分账 */
    SHARE(99, "分账"),

    ;

    private final int key;
    private final String value;

    FundsType(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
