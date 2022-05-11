package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 *客户群「加入群聊」配置处理结果
 * @author Jc
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpGroupJoinWayResult  extends WxCpBaseResp {
  private static final long serialVersionUID = 5621905029624794129L;
  @SerializedName("config_id")
  private String configId;

  public static WxCpGroupJoinWayResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGroupJoinWayResult.class);
  }
}
