package me.chanjar.weixin.cp.bean.living;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 直播返回对象.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpLivingResult implements Serializable {
  private static final long serialVersionUID = -5028321625140879571L;

  @SerializedName("errcode")
  private Integer errcode;

  @SerializedName("errmsg")
  private String errmsg;

  /**
   * The type Living id result.
   */
  @Getter
  @Setter
  public static class LivingIdResult implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("next_cursor")
    private String nextCursor;

    @SerializedName("livingid_list")
    private String[] livingIdList;

    /**
     * From json living id result.
     *
     * @param json the json
     * @return the living id result
     */
    public static LivingIdResult fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, LivingIdResult.class);
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

  /**
   * From json wx cp living result.
   *
   * @param json the json
   * @return the wx cp living result
   */
  public static WxCpLivingResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpLivingResult.class);
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
