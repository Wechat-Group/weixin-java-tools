package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * The type Wx cp tp user detail.
 *
 * @author huangxiaoming
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpTpUserDetail extends WxCpBaseResp {
  private static final long serialVersionUID = -5028321625140879571L;
  /**
   * 用户所属企业的corpid
   */
  @SerializedName("corpid")
  private String corpId;

  /**
   * 成员UserID
   */
  @SerializedName("userid")
  private String userId;

  /**
   * 成员姓名
   */
  @SerializedName("name")
  private String name;

  /**
   * 性别。0表示未定义，1表示男性，2表示女性
   */
  @SerializedName("gender")
  private String gender;

  /**
   * 头像url。仅在用户同意snsapi_privateinfo授权时返回
   */
  @SerializedName("avatar")
  private String avatar;

  /**
   * 员工个人二维码（扫描可添加为外部联系人），仅在用户同意snsapi_privateinfo授权时返回
   */
  @SerializedName("qr_code")
  private String qrCode;

  /**
   * 手机，仅在用户同意snsapi_privateinfo授权时返回，第三方应用不可获取
   */
  @SerializedName("mobile")
  private String mobile;

  /**
   * 邮箱，仅在用户同意snsapi_privateinfo授权时返回，第三方应用不可获取
   */
  @SerializedName("email")
  private String email;

  /**
   * 企业邮箱，仅在用户同意snsapi_privateinfo授权时返回，第三方应用不可获取
   */
  @SerializedName("biz_mail")
  private String bizMail;

  /**
   * 仅在用户同意snsapi_privateinfo授权时返回，第三方应用不可获取
   */
  @SerializedName("address")
  private String address;

  /**
   * From json wx cp tp user detail.
   *
   * @param json the json
   * @return the wx cp tp user detail
   */
  public static WxCpTpUserDetail fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpUserDetail.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
