package me.chanjar.weixin.open.bean.minishopGoods;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class GoodsCatList {
  /**
   * 错误码信息
   */
  private Integer errcode;
  /**
   * 错误信息
   */
  private String errmsg;
  /**
   * 类目数组
   */
  private List<GoodsCat> catList;

  public JsonObject toJsonObject() {
    Gson gson = new Gson();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("errcode", errcode);
    jsonObject.addProperty("errmsg", errmsg);
    jsonObject.addProperty("cat_list", gson.toJson(catList));
    return jsonObject;
  }


}
