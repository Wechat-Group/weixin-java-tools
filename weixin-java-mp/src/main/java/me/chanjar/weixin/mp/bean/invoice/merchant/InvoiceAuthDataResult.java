package me.chanjar.weixin.mp.bean.invoice.merchant;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户开票认证信息返回结果DTO
 *
 * @author Mario Luo
 */
@Data
public class InvoiceAuthDataResult implements Serializable {
  private static final long serialVersionUID = 7199243456761896912L;

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
    private static final long serialVersionUID = 3132380567762544927L;

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
    private static final long serialVersionUID = 2114368427010646381L;

    private String title;
    private String phone;
    private String email;
    private List<KeyValuePair> customField;
  }

  @Data
  public static class BizField implements Serializable {
    private static final long serialVersionUID = 1799355181972008881L;

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
    private static final long serialVersionUID = -1068075389526145791L;

    private String key;
    private String value;
  }
}
