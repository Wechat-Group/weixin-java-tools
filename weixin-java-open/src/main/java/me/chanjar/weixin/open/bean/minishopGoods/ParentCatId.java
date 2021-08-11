package me.chanjar.weixin.open.bean.minishopGoods;

import com.google.gson.JsonObject;
import lombok.Data;

@Data
public class ParentCatId {
  private Integer fCatId;

  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("f_cat_id", fCatId);
    return jsonObject;
  }
}
