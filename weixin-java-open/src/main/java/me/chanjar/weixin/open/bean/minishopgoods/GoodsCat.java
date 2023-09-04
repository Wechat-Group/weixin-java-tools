package me.chanjar.weixin.open.bean.minishopgoods;

import com.google.gson.JsonObject;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GoodsCat {
  /**
   * 类目id
   */
  private Integer catId;
  /**
   * 类目父id
   */
  private Integer fCatId;
  /**
   * 类目名称
   */
  private String name;

  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("cat_id", catId);
    jsonObject.addProperty("f_cat_id", fCatId);
    jsonObject.addProperty("name", name);
    return jsonObject;
  }

}
