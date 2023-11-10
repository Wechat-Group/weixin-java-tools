package com.github.binarywang.wxpay.bean.payscore.enums;

/**
 * @author UltramanNoa
 * @className SignPlanServiceOrderPlanDetailStateEnum
 * @description 计划明细状态
 * @createTime 2023/11/3 17:43
 **/
public enum SignPlanServiceOrderPlanDetailStateEnum {
  /**
   * 本计划详情还未使用
   */
  NOT_USED,

  /**
   * 本计划详情使用中，已有对应的服务订单
   */
  USING,

  /**
   * 本计划详情已使用，对应的服务订单已完成支付
   */
  USED,

  /**
   * 本计划详情已取消使用，对应的服务订单已取消
   */
  SIGN_PLAN_DETAIL_CANCEL,
  ;
}
