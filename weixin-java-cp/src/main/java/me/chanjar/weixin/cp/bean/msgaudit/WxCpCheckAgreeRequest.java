package me.chanjar.weixin.cp.bean.msgaudit;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 获取会话同意情况请求参数.
 *
 * @author Wang_Wong  created on  2022-01-21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpCheckAgreeRequest implements Serializable {
  private static final long serialVersionUID = -4960239393895754138L;

  @SerializedName("info")
  private List<Info> info;

  /**
   * From json wx cp check agree request.
   *
   * @param json the json
   * @return the wx cp check agree request
   */
  public static WxCpCheckAgreeRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpCheckAgreeRequest.class);
  }

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  /**
   * The type Info.
   */
  @Getter
  @Setter
  public static class Info implements Serializable {
    private static final long serialVersionUID = -4960239393895754138L;

    @SerializedName("userid")
    private String userid;

    @SerializedName("exteranalopenid")
    private String exteranalOpenId;

    /**
     * From json info.
     *
     * @param json the json
     * @return the info
     */
    public static Info fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Info.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

}
