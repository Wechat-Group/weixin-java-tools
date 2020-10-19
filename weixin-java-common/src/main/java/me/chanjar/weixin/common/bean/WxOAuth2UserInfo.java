package me.chanjar.weixin.common.bean;


import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * oauth2用户个人信息.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-10-11
 */
@Data
public class WxOAuth2UserInfo implements Serializable {
  private static final long serialVersionUID = 3181943506448954725L;

  /**
   * openid	普通用户的标识，对当前开发者帐号唯一
   */
  private String openid;
  /**
   * nickname	普通用户昵称
   */
  private String nickname;
  /**
   * sex	普通用户性别，1为男性，2为女性
   */
  private Integer sex;
  /**
   * city	普通用户个人资料填写的城市
   */
  private String city;

  /**
   * province	普通用户个人资料填写的省份
   */
  private String province;
  /**
   * country	国家，如中国为CN
   */
  private String country;
  /**
   * headimgurl	用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），
   * 用户没有头像时该项为空
   */
  @SerializedName("headimgurl")
  private String headImgUrl;
  /**
   * unionid	用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
   */
  @SerializedName("unionid")
  private String unionId;

  /**
   * privilege	用户特权信息，json数组，如微信沃卡用户为（chinaunicom）
   */
  @SerializedName("privilege")
  private String[] privileges;

  public static WxOAuth2UserInfo fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxOAuth2UserInfo.class);
  }
}
