package me.chanjar.weixin.open.bean.minishop.goods;

import com.google.gson.JsonObject;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class WxMinishopGoodsSkuAttr implements Serializable {
  private String attrKey;

  private String attrValue;


  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("attr_key", attrKey);
    jsonObject.addProperty("attr_value", attrValue);

    return jsonObject;
  }
}
