package me.chanjar.weixin.mp.bean.marketing;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Data
public class WxMpAdLeadFilter implements Serializable {
  private static final long serialVersionUID = -1469998986497327439L;
  private String field;
  private String operator;
  private List<String> values;

  public JsonObject toJsonObject() {
    JsonObject json = new JsonObject();
    json.addProperty("field", field);
    json.addProperty("operator", operator);
    if (values != null) {
      JsonArray vs = new JsonArray();
      for (String value : values) {
        vs.add(value);
      }
    }
    return json;
  }
}
