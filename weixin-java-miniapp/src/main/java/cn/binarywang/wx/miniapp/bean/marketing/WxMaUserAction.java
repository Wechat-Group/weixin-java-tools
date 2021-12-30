package cn.binarywang.wx.miniapp.bean.marketing;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.rmi.runtime.Log;

import java.io.Serializable;
import java.util.List;

/**
 * @Description :微信营销接口
 * @author <a href="https://github.com/184759547">184759547</a>
 * @since : 2021/12/28
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WxMaUserAction implements Serializable {
  private static final long serialVersionUID = 7482378011709983616L;
  private String url;
  private Integer actionTime;
  private String actionType;
  private String leadsType;
  private String clickId;
  private Integer actionParam;

  private JsonObject toJsonObject() {
    JsonObject json = new JsonObject();
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
      if (this.leadsType != null) {
        actionParamJson.addProperty("leads_type", leadsType);
      }
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
  public static String listToJson(List<WxMaUserAction> actions, Long userActionSetId) {
    JsonArray array = new JsonArray();
    for (WxMaUserAction action : actions) {
      array.add(action.toJsonObject());
    }

    JsonObject result = new JsonObject();
    result.addProperty("user_action_set_id", userActionSetId);
    result.add("actions", array);
    return result.toString();
  }
}
