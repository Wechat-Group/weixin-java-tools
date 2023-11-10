package com.github.binarywang.wxpay.bean.payscore.enums;

/**
 * @author UltramanNoa
 * @className SignPlanServiceOrderStateEnum
 * @description 签约计划服务订单状态
 * @createTime 2023/11/3 15:28
 **/
public enum SignPlanServiceOrderStateEnum {

  /**
   * 商户已创建服务订单
   */
  CREATED,

  /**
   * 服务订单进行中
   */
  DOING,

  /**
   * 服务订单完成
   */
  DONE,

  /**
   * 商户取消服务订单
   */
  REVOKED,

  /**
   * 服务订单已失效
   */
  EXPIRED,
  ;
}
