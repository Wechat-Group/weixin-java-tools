package me.chanjar.weixin.mp.bean.marketing;

import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */

@Data
public class WxMpUserActionSet implements Serializable {
  private static final long serialVersionUID = 1979861770645159905L;
  protected static final JsonParser JSON_PARSER = new JsonParser();

  /**
   * user_action_set_id
   * 用户行为源名称
   */
  @SerializedName("user_action_set_id")
  private Long userActionSetId;

  /**
   * title.
   * 用户行为源描述
   */
  @SerializedName("description")
  private String description;

  /**
   * activate_status.
   * 数据接入状态， true 表示已接入， false 表示未接入
   */
  @SerializedName("activate_status")
  private Boolean activate_status;

  /**
   * created_time.
   * 创建时间
   */
  @SerializedName("created_time")
  private String createdTime;

  public static List<WxMpUserActionSet> fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(
      JSON_PARSER.parse(json).getAsJsonObject().get("data").getAsJsonObject().get("list"),
      new TypeToken<List<WxMpUserActionSet>>() {
      }.getType());
  }

}
