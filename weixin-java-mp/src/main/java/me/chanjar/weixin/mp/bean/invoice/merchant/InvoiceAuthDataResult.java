package me.chanjar.weixin.mp.bean.invoice.merchant;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户开票认证信息返回结果DTO
 */
@Data
public class InvoiceAuthDataResult implements Serializable {

  /**
   * 订单授权状态，当errcode为0时会出现
   */
  private String invoiceStatus;

  /**
   * 授权时间，为十位时间戳（utc+8），当errcode为0时会出现
   */
  private Long authTime;

  /**
   * 用户授权信息
   */
  private UserAuthInfo userAuthInfo;

  @Data
  public static class UserAuthInfo implements Serializable {
    /**
     * 个人抬头
     */
    private UserField userField;

    /**
     * 单位抬头
     */
    private BizField bizField;
  }

  @Data
  public static class UserField implements Serializable {
    private String title;
    private String phone;
    private String email;
    private List<KeyValuePair> customField;
  }

  @Data
  public static class BizField implements Serializable {
    private String title;
    private String taxNo;
    private String addr;
    private String phone;
    private String bankType;
    private String bankNo;
    private List<KeyValuePair> customField;
  }

  @Data
  public static class KeyValuePair implements Serializable {
    private String key;
    private String value;
  }
}
