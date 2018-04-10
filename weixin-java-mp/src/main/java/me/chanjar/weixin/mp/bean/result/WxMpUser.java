package me.chanjar.weixin.mp.bean.result;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 微信用户信息.
 *
 * @author chanjarster
 */
@Data
public class WxMpUser implements Serializable {
  private static final long serialVersionUID = 5788154322646488738L;

  private Boolean subscribe;
  private String openId;
  private String nickname;
  /**
   * 性别描述信息：男、女、未知等.
   */
  private String sexDesc;
  /**
   * 性别表示：1，2等数字.
   */
  private Integer sex;
  private String language;
  private String city;
  private String province;
  private String country;
  private String headImgUrl;
  private Long subscribeTime;
  /**
   * https://mp.weixin.qq.com/cgi-bin/announce?action=getannouncement&announce_id=11513156443eZYea&version=&lang=zh_CN
   * <pre>
   * 只有在将公众号绑定到微信开放平台帐号后，才会出现该字段。
   * 另外，在用户未关注公众号时，将不返回用户unionID信息。
   * 已关注的用户，开发者可使用“获取用户基本信息接口”获取unionID；
   * 未关注用户，开发者可使用“微信授权登录接口”并将scope参数设置为snsapi_userinfo，获取用户unionID
   * </pre>
   */
  private String unionId;
  private String remark;
  private Integer groupId;
  private Long[] tagIds;

  /**
   * 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）.
   */
  private String[] privileges;

  public static WxMpUser fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(json, WxMpUser.class);
  }

  public static List<WxMpUser> fromJsonList(String json) {
    Type collectionType = new TypeToken<List<WxMpUser>>() {
    }.getType();
    Gson gson = WxMpGsonBuilder.INSTANCE.create();
    JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
    return gson.fromJson(jsonObject.get("user_info_list"), collectionType);
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

}
