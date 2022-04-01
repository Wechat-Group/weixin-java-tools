package me.chanjar.weixin.cp.bean.msgaudit;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 获取会话同意情况返回对象.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpAgreeInfo implements Serializable {
  private static final long serialVersionUID = -5028321625140879571L;

  @SerializedName("errcode")
  private Integer errcode;

  @SerializedName("errmsg")
  private String errmsg;

  @SerializedName("agreeinfo")
  private List<AgreeInfo> agreeInfo;

  @Getter
  @Setter
  public static class AgreeInfo implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("status_change_time")
    private Long statusChangeTime;

    @SerializedName("userid")
    private String userid;

    @SerializedName("exteranalopenid")
    private String exteranalOpenId;

    @SerializedName("agree_status")
    private String agreeStatus;

    public static AgreeInfo fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, AgreeInfo.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  public static WxCpAgreeInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpAgreeInfo.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
