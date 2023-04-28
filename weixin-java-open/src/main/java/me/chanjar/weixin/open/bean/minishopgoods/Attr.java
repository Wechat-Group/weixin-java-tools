package me.chanjar.weixin.open.bean.minishopgoods;

import com.google.gson.JsonObject;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Attr {
  /**
   * 属性键key（属性自定义用）
   */
  private String attrKey;
  /**
   * 属性值（属性自定义用）
   */
  private String attrValue;


  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("attr_key", attrKey);
    jsonObject.addProperty("attr_value", attrValue);
    return jsonObject;
  }


}
