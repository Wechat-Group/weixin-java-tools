package cn.binarywang.wx.miniapp.bean.product;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

@Data
public class WxMinishopGoodsSkuAttr implements Serializable {
  private static final long serialVersionUID = -7274443170526394680L;

  @SerializedName("attr_key")
  private String attrKey;

  @SerializedName("attr_value")
  private String attrValue;

  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("attr_key", attrKey);
    jsonObject.addProperty("attr_value", attrValue);

    return jsonObject;
  }
}
