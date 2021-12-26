package me.chanjar.weixin.cp.bean.living;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 直播观看明细.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpWatchStat implements Serializable {
  private static final long serialVersionUID = -5028321625140879571L;

  private Integer ending;

  @SerializedName("next_key")
  private String nextKey;

  @SerializedName("stat_info")
  private StatInfo statInfo;

  @Getter
  @Setter
  public static class StatInfo implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("users")
    private List<WxCpWatchStat.User> users;

    @SerializedName("external_users")
    private List<WxCpWatchStat.ExternalUser> externalUsers;

  }

  @Getter
  @Setter
  public static class User implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    private String userid;

    @SerializedName("watch_time")
    private Long watchTime;

    @SerializedName("is_comment")
    private Integer isComment;

    @SerializedName("is_mic")
    private Integer isMic;

  }

  @Getter
  @Setter
  public static class ExternalUser implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    private String name;
    private Integer type;

    @SerializedName("external_userid")
    private String externalUserid;

    @SerializedName("watch_time")
    private Long watchTime;

    @SerializedName("is_comment")
    private Integer isComment;

    @SerializedName("is_mic")
    private Integer isMic;

  }

  public static WxCpWatchStat fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpWatchStat.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
