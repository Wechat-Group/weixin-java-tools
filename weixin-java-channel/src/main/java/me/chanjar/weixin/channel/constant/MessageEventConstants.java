package me.chanjar.weixin.channel.constant;

/**
 * 消息回调
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface MessageEventConstants {
    /** 品牌资质事件回调 */
    String BRAND = "channels_ec_brand";
    /** 商品审核结果 */
    String PRODUCT_SPU_AUDIT = "product_spu_audit";
    /** 商品上下架 */
    String PRODUCT_SPU_STATUS_UPDATE = "product_spu_listing";
    /** 商品更新 */
    String PRODUCT_SPU_UPDATE = "product_spu_update";
    /** 类目审核结果 */
    String PRODUCT_CATEGORY_AUDIT = "product_category_audit";
    /** 订单下单 */
    String ORDER_NEW = "channels_ec_order_new";
    /** 订单取消 */
    String ORDER_CANCEL = "channels_ec_order_cancel";
    /** 订单支付成功 */
    String ORDER_PAY = "channels_ec_order_pay";
    /** 订单发货 */
    String ORDER_DELIVER = "channels_ec_order_deliver";
    /** 订单确认收货 */
    String ORDER_CONFIRM = "channels_ec_order_confirm";
    /** 订单结算成功 */
    String ORDER_SETTLE = "channels_ec_order_settle";
    /** 订单其他信息更新 */
    String ORDER_EXT_INFO_UPDATE = "channels_ec_order_ext_info_update";
    /** 订单状态更新 */
    String ORDER_STATUS_UPDATE = "product_order_status_update";
    /** 售后单更新通知 */
    String AFTER_SALE_UPDATE = "channels_ec_aftersale_update";
    /** 纠纷更新通知 */
    String COMPLAINT_NOTIFY = "channels_ec_complaint_update";
    // 优惠券相关
    /** 优惠券领取通知 */
    String RECEIVE_COUPON = "channels_ec_coupon_receive";
    /** 创建优惠券通知 */
    String CREATE_COUPON = "channels_ec_coupon_create";
    /** 优惠券删除通知 */
    String DELETE_COUPON = "channels_ec_coupon_delete";
    /** 优惠券过期通知 */
    String EXPIRE_COUPON = "channels_ec_coupon_expire";
    /** 更新优惠券信息通知 */
    String UPDATE_COUPON_INFO = "channels_ec_coupon_info_change";
    /** 优惠券作废通知 */
    String INVALID_COUPON = "channels_ec_coupon_invalid";
    /** 用户优惠券过期通知 */
    String USER_COUPON_EXPIRE = "channels_ec_user_coupon_expire";
    /** 优惠券返还通知 */
    String USER_COUPON_UNUSE = "channels_ec_user_coupon_unuse";
    /** 优惠券核销通知 */
    String USER_COUPON_USE = "channels_ec_user_coupon_use";
    // 资金相关
    /** 结算账户变更回调 */
    String ACCOUNT_NOTIFY = "channels_ec_acct_notify";
    /** 提现回调 */
    String WITHDRAW_NOTIFY = "channels_ec_withdraw_notify";
    /** 提现二维码回调 */
    String QRCODE_STATUS = "qrcode_status";
    // 团长
    String SUPPLIER_ITEM_UPDATE = "head_supplier_item_update";
    // 其他
    /** 进入会话事件 */
    String USER_ENTER_TEMP_SESSION = "user_enter_tempsession";

    // 会员相关
    /** 用户加入会员 */
    String USER_VIP_JOIN = "channels_ec_vip_join";
    /** 用户注销会员 */
    String USER_VIP_CLOSE = "channels_ec_vip_close";
    /** 用户等级更新 */
    String USER_VIP_GRADE_INFO_UPDATE = "channels_ec_vip_grade_info_update";
    /** 用户积分更新 */
    String USER_VIP_SCORE_UPDATE = "channels_ec_vip_score_update";
    /** 用户积分兑换 */
    String USER_VIP_SCORE_EXCHANGE = "channels_ec_vip_score_exchange";
}
