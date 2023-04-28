package me.chanjar.weixin.open.bean.minishopgoods;

import com.google.gson.JsonObject;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ExpressInfo {
  /**
   * 运费模板ID（先通过获取运费模板接口delivery/get_freight_template拿到）
   */
  private Integer templateId;


  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("template_id", templateId);
    return jsonObject;
  }


}
