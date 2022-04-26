package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * @author leiin
 * @date 2022/4/26 5:21 下午
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

  public static WxCpKfServiceUpgradeConfigResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpKfServiceUpgradeConfigResp.class);
  }

  @Data
  @NoArgsConstructor
  public class MemberRange {
    @SerializedName("userid_list")
    private List<String> useridList;

    @SerializedName("department_id_list")
    private List<Integer> departmentIdList;
  }

  @Data
  @NoArgsConstructor
  private class GroupchatRange {
    @SerializedName("chat_id_list")
    private List<String> chatIdList;
  }
}
