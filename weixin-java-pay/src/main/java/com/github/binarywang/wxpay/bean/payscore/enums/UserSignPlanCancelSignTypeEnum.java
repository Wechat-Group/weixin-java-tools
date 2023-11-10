package com.github.binarywang.wxpay.bean.payscore.enums;

/**
 * @author UltramanNoa
 * @className UserSignPlanCancelSignTypeEnum
 * @description 签约计划取消类型
 * @createTime 2023/11/3 17:15
 **/
public enum UserSignPlanCancelSignTypeEnum {
  /**
   * 用户已签约协议未取消
   */
  NOT_CANCEL,

  /**
   * 用户取消已签约的协议
   */
  USER,

  /**
   * 商户取消已签约的协议
   */
  MERCHANT,

  /**
   * 用户解除服务授权时取消服务下的已签约协议
   */
  REVOKE_SERVICE,
  ;
}
