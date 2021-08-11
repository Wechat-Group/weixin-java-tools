package me.chanjar.weixin.open.bean.minishop;

import lombok.Data;
import me.chanjar.weixin.common.error.WxError;

import java.io.Serializable;

@Data
public class MinishopAuditStatus implements Serializable {
  private static final long serialVersionUID = 106594659951047198L;

  private WxError wxError;

  /**
   * 注册状态 0:成功 1:已发送协议还未签约 2: 未发送协议或协议已过期，需发送协议，当register_status为0时以下字段有意义
   */
  private Integer registerStatus;

  /**
   * 商家信息状态, 具体含义查看状态枚举值
   */
  private Integer merchantInfoStatus;

  /**
   * 账户验证状态, 具体含义查看状态枚举值
   */
  private Integer acctVerifyStatus;


  /**
   * 基础信息状态, 具体含义查看状态枚举值
   */
  private Integer basicInfoStatus;

  /**
   * 支付签约状态, 具体含义查看状态枚举值
   */
  private Integer paySignStatus;

  /**
   * 基础信息驳回原因
   */
  private String auditRejectReason;

  /**
   * 法人验证链接
   */
  private String legalValidationUrl;

  /**
   * 参数名
   */
  private String payAuditDetailParamName;

  /**
   * 支付资质驳回原因
   */
  private String payAuditDetailRejectReason;

  /**
   * 注册的appid
   */
  private String registeredAppId;
}
