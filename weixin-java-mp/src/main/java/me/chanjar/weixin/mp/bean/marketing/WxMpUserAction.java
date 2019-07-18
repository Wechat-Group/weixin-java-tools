package me.chanjar.weixin.mp.bean.marketing;

import com.google.gson.JsonObject;
import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */

@Data
public class WxMpUserAction implements Serializable {
  private static final long serialVersionUID = 7042393762652152209L;
  private Long userActionSetId;
  private String url;
  private Boolean actionTime;
  private String actionType;
  private String clickId;
  private Integer actionParam;

  public JsonObject toJsonObject() {
    JsonObject json = new JsonObject();
    json.addProperty("user_action_set_id", this.userActionSetId);
    json.addProperty("url", this.url);
    json.addProperty("action_time", this.actionTime);
    if (this.clickId != null) {
      JsonObject traceJson = new JsonObject();
      traceJson.addProperty("click_id", this.clickId);
      json.add("trace", traceJson);
    }
    if (this.actionParam != null) {
      JsonObject actionParamJson = new JsonObject();
      actionParamJson.addProperty("value", actionParam);
      json.add("action_param", actionParamJson);
    }
    return json;
  }
}
