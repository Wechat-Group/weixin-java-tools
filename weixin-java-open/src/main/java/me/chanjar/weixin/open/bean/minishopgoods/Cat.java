package me.chanjar.weixin.open.bean.minishopgoods;

import com.google.gson.JsonObject;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Cat {
  /**
   * 类目ID，如果brand_id=2100000000，需要先通过获取类目接口category/get拿到可用的cat_id；
   * 如果brand_id!=2100000000，则这里的cat_id需要与获取品牌接口brand/get中的1,2,3级类目一一对应
   */
  private Integer catId;
  /**
   * 	类目层级
   */
  private Integer level;

  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("cat_id", catId);
    jsonObject.addProperty("level", level);
    return jsonObject;
  }


}
