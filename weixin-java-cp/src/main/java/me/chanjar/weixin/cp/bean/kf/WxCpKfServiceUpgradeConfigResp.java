package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * The type Wx cp kf service upgrade config resp.
 *
 * @author leiin  created on  2022/4/26 5:21 下午
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class WxCpKfServiceUpgradeConfigResp extends WxCpBaseResp {

  private static final long serialVersionUID = -3212550906238196617L;

  @SerializedName("member_range")
  private MemberRange memberRange;

  @SerializedName("groupchat_range")
  private GroupchatRange groupchatRange;

  /**
   * From json wx cp kf service upgrade config resp.
   *
   * @param json the json
   * @return the wx cp kf service upgrade config resp
   */
  public static WxCpKfServiceUpgradeConfigResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpKfServiceUpgradeConfigResp.class);
  }

  /**
   * The type Member range.
   */
  @Data
  @NoArgsConstructor
  public static class MemberRange {
    @SerializedName("userid_list")
    private List<String> useridList;

    @SerializedName("department_id_list")
    private List<Integer> departmentIdList;
  }

  /**
   * The type Groupchat range.
   */
  @Data
  @NoArgsConstructor
  public static class GroupchatRange {
    @SerializedName("chat_id_list")
    private List<String> chatIdList;
  }
}
