package cn.binarywang.wx.miniapp.bean.safety.request;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 获取用户的安全等级请求参数
 *
 * @author <a href="https://github.com/azouever">azouever</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaUserSafetyRiskRankRequest implements Serializable {
  private static final long serialVersionUID = 1052539797739665816L;


  /**
   * 小程序appid
   * 必填
   */
  private String appId;

  /**
   * 用户的openid
   * 必填
   */
  private String openid;

  /**
   * 场景值，0:注册，1:营销作弊
   * 必填
   */
  private Integer scene;

  /**
   * 用户手机号
   * 非必填
   */
  @SerializedName("mobile_no")
  private String mobileNo;

  /**
   * 用户访问源ip
   * 必填
   */
  @SerializedName("client_ip")
  private String clientIp;

  /**
   * 用户邮箱地址
   * 非必填
   */
  @SerializedName("email_address")
  private String emailAddress;

  /**
   * 额外补充信息
   * 非必填
   */
  @SerializedName("extended_info")
  private String extendedInfo;

  /**
   * false：正式调用，true：测试调用
   * 非必填
   */
  @SerializedName("is_test")
  private boolean isTest;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}
