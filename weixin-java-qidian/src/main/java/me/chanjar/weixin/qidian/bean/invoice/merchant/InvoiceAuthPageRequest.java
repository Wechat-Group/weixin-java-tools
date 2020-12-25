package me.chanjar.weixin.qidian.bean.invoice.merchant;

import lombok.Data;

import java.io.Serializable;

/**
 * 获取授权地址的输入参数
 */
@Data
public class InvoiceAuthPageRequest implements Serializable {

  /**
   * 开票平台在微信的标识号，商户需要找开票平台提供
   */
  private String sPappid;

  /**
   * 订单id，在商户内单笔开票请求的唯一识别号
   */
  private String orderId;

  /**
   * 订单金额，以分为单位
   */
  private Long money;

  /**
   * 开票来源
   */
  private String source;

  /**
   * 授权成功后跳转页面。本字段只有在source为H5的时候需要填写，引导用户在微信中进行下一步流程。app开票因为从外部app拉起微信授权页，授权完成后自动回到原来的app，故无需填写。
   */
  private String redirectUrl;

  /**
   * 授权类型，0：开票授权，1：填写字段开票授权，2：领票授权
   */
  private Integer type;

  /**
   * 时间戳单位s
   */
  private Long timestamp;

  /**
   * 内部填充(请务设置)
   */
  private String ticket;
}
