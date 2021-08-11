package me.chanjar.weixin.open.bean.minishopGoods;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class DescInfo {
  /**
   * 商品详情，图文(目前只支持图片)  true
   */
  private List<String>  imgs;

  public JsonObject toJsonObject() {
    Gson gson = new Gson();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("imgs", gson.toJson(imgs));
    return jsonObject;
  }

}
