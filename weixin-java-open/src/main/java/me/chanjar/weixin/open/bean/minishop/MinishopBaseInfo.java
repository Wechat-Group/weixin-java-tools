package me.chanjar.weixin.open.bean.minishop;


import lombok.Data;

import java.io.Serializable;

/**
 * @author luowentao
 * @since 2021-01-27
 */
@Data
public class MinishopBaseInfo implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 微信小商店ID（自定）
   */
  private Long miniShopId;

  /**
   * 小程序ID
   */
  private String appId;

  /**
   * 1）小程序名称可以由中文、数字、英文、空格及部分特殊符号(“空格”、“-”、“+”、“&”、“.”)组成。长度在4-30个字符之间，一个中文字等于2个字符。
   * 2）公众号、小程序在微信公众平台上的名称是唯一的，且属于同一主体下，可以重名。
   * 3）不得与不同主体的公众号名称重名。
   */
  private String nickName;

  /**
   * 1）小程序简称可以从小程序名称中按顺序截取字符创建。长度在4-10个字符之间，一个中文字等于2个字符。
   * 2）小程序简称在微信公众平台是不唯一的，可以重名。但对于仿冒、侵权等恶意情况，平台仍会做出相关处罚。开发者也可通过侵权投诉维护自己的正当权益。
   * 3）小程序简称设置后，将在客户端任务栏向用户展示。开发者可以凭借此功能，更好地实现产品品牌价值和展示。目前暂不支持名称的其他功能。
   */
  private String abbr;

  /**
   * 介绍。请确认介绍内容不含国家相关法律法规禁止内容,介绍字数为4-120个字符，一个中文占2个字符。一个月内可申请5次修改。请提供可支持命名的材料
   */
  private String introduction;

  /**
   * 补充材料，传media id数组，当返回210047时必填
   */
  private String namingOtherStuff;

  /**
   * 邮箱
   */
  private String mail;

  /**
   * 退货地址
   */
  private Integer returnAddressId;

  /**
   * 公司地址
   */
  private Integer companyAddressId;

}
