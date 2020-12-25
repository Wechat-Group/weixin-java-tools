package me.chanjar.weixin.qidian.bean.marketing;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WxMpUserAction implements Serializable {
  private static final long serialVersionUID = 7042393762652152209L;

  private Long userActionSetId;
  private String url;
  private Integer actionTime;
  private String actionType;
  private String leadsType;
  private String clickId;
  private Integer actionParam;

  private JsonObject toJsonObject() {
    JsonObject json = new JsonObject();
    json.addProperty("user_action_set_id", this.userActionSetId);
    json.addProperty("url", this.url);
    json.addProperty("action_time", this.actionTime);
    json.addProperty("action_type", this.actionType);

    if (this.clickId != null) {
      JsonObject traceJson = new JsonObject();
      traceJson.addProperty("click_id", this.clickId);
      json.add("trace", traceJson);
    }

    if (this.actionParam != null) {
      JsonObject actionParamJson = new JsonObject();
      actionParamJson.addProperty("value", actionParam);
      actionParamJson.addProperty("leads_type", leadsType);
      json.add("action_param", actionParamJson);
    }

    return json;
  }

  /**
   * list对象转换为json字符串
   *
   * @param actions .
   * @return .
   */
  public static String listToJson(List<WxMpUserAction> actions) {
    JsonArray array = new JsonArray();
    for (WxMpUserAction action : actions) {
      array.add(action.toJsonObject());
    }

    JsonObject result = new JsonObject();
    result.add("actions", array);
    return result.toString();
  }
}
