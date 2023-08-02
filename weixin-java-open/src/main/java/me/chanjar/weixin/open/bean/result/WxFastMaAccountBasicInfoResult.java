package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 快速创建的小程序的账号基本信息.
 *
 * @author Hipple
 * @since 2019/1/23 14:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxFastMaAccountBasicInfoResult extends WxOpenResult {
  private static final long serialVersionUID = -8713680081353954208L;

  /**
   * 小程序ID
   */
  @SerializedName("appid")
  private String appId;

  /**
   * 帐号类型（1：订阅号，2：服务号，3：小程序）
   */
  @SerializedName("account_type")
  private Integer accountType;

  /**
   * 主体类型（1：企业）
   */
  @SerializedName("principal_type")
  private Integer principalType;

  /**
   * 主体名称
   */
  @SerializedName("principal_name")
  private String principalName;

  /**
   * 实名验证状态（1：实名验证成功，2：实名验证中，3：实名验证失败）调用接口1.1创建帐号时，realname_status会初始化为2对于注册方式为微信认证的帐号，资质认证成功时，realname_status会更新为1 注意！！！当realname_status不为1时，帐号只允许调用本文档内的以下API：（即无权限调用其他API） 微信认证相关接口（参考2.x） 帐号设置相关接口（参考3.x）
   */
  @SerializedName("realname_status")
  private Integer realnameStatus;

  /**
   * 小程序名称
   */
  @SerializedName("nickname")
  private String nickname;

  /**
   * 微信认证信息
   */
  @SerializedName("wx_verify_info")
  private WxVerifyInfo wxVerifyInfo;
  /**
   * 功能介绍信息
   */
  @SerializedName("signature_info")
  private SignatureInfo signatureInfo;
  /**
   * 头像信息
   */
  @SerializedName("head_image_info")
  private HeadImageInfo headImageInfo;

  /**
   * 名称信息
   */
  @SerializedName("nickname_info")
  private NicknameInfo nicknameInfo;

  /**
   * 注册国家
   * <a href="https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/Mini_Program_Basic_Info/Mini_Program_Information_Settings.html#%E6%B3%A8%E5%86%8C%E5%9B%BD%E5%AE%B6">参考链接</a>
   */
  @SerializedName("registered_country")
  private Integer registeredCountry;

  /**
   * 主体标识，非个人主体时返回的是企业或者政府或其他组织的代号
   */
  @SerializedName("credential")
  private String credential;

  /**
   * 认证类型；如果未完成微信认证则返回0；不同枚举值对应的类型说明看下方
   * 状态值	说明
   * 1	企业
   * 2	企业媒体
   * 3	政府
   * 4	非盈利组织
   * 5	民营非企业
   * 6	盈利组织
   * 8	社会团体
   * 9	事业媒体
   * 11	事业单位
   * 12	个体工商户
   * 14	海外企业
   */
  @SerializedName("customer_type")
  private Integer customerType;

  @Data
  public static class NicknameInfo {
    /**
     * 小程序名称
     */
    @SerializedName("nickname")
    private String nickname;
    /**
     * 小程序名称已使用修改次数（本年）
     */
    @SerializedName("modify_used_count")
    private Integer modifyUsedCount;
    /**
     * 小程序名称修改次数总额度（本年）
     */
    @SerializedName("modify_quota")
    private Integer modifyQuota;
  }


  @Data
  public static class WxVerifyInfo {
    /**
     * 是否资质认证（true：是，false：否）若是，拥有微信认证相关的权限
     */
    @SerializedName("qualification_verify")
    private Boolean qualificationVerify;
    /**
     * 是否名称认证（true：是，false：否）对于公众号（订阅号、服务号），是名称认证，微信客户端才会有微信认证打勾的标识。
     */
    @SerializedName("naming_verify")
    private Boolean namingVerify;
    /**
     * 是否需要年审（true：是，false：否）（qualification_verify = true时才有该字段）
     */
    @SerializedName("annual_review")
    private Boolean annualReview;

    /**
     * 年审开始时间，时间戳（qualification_verify = true时才有该字段）
     */
    @SerializedName("annual_review_begin_time")
    private String annualReviewBeginTime;

    /**
     * 年审截止时间，时间戳（qualification_verify = true时才有该字段）
     */
    @SerializedName("annual_review_end_time")
    private String annualReviewEndTime;
  }


  @Data
  public static class SignatureInfo {
    /**
     * 功能介绍
     */
    @SerializedName("signature")
    private String signature;
    /**
     * 头像已使用修改次数（本月）
     */
    @SerializedName("modify_used_count")
    private Integer modifyUsedCount;
    /**
     * 头像修改次数总额度（本月）
     */
    @SerializedName("modify_quota")
    private Integer modifyQuota;
  }


  @Data
  public static class HeadImageInfo {
    /**
     * 头像url
     */
    @SerializedName("head_image_url")
    private String headImageUrl;
    /**
     * 头像已使用修改次数（本月）
     */
    @SerializedName("modify_used_count")
    private Integer modifyUsedCount;

    /**
     * 头像修改次数总额度（本月）
     */
    @SerializedName("modify_quota")
    private Integer modifyQuota;
  }
}
