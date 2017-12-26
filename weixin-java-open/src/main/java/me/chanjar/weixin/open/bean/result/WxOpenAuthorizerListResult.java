/**
 *
 */
package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 已授权的帐号基本信息
 *
 * @author lfj
 */
@Data
public class WxOpenAuthorizerListResult implements Serializable {

  private static final long serialVersionUID = 1233754950604053597L;

  @SerializedName("total_count")
  private Integer totalCount;
  private List<Authorizer> list;

  @Data
  public class Authorizer implements Serializable {

    private static final long serialVersionUID = 988556960280878035L;

    @SerializedName("authorizer_appid")
    private String authorizerAppid;
    @SerializedName("refresh_token")
    private String refreshToken;
    @SerializedName("auth_time")
    private String authTime;

    @Override
    public String toString() {
      return WxMpGsonBuilder.INSTANCE.create().toJson(this);
    }

  }

  public static WxOpenAuthorizerListResult fromJson(String json) {
    return WxOpenGsonBuilder.create().fromJson(json, WxOpenAuthorizerListResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }

}
