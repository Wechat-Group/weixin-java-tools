package me.chanjar.weixin.open.bean.minishop.goods;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class WxMinishopSku implements Serializable {
  private Long productId;

  private String outProductId;

  private String outSkuId;

  private String thumbImg;

  private Integer salePrice;

  private Integer marketPrice;

  private Integer stockNum;

  private String skuCode;

  private String barCode;

  private List<WxMinishopGoodsSkuAttr> skuAttrs;

  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("product_id", productId);
    jsonObject.addProperty("out_product_id", outProductId);
    jsonObject.addProperty("out_sku_id", outSkuId);
    jsonObject.addProperty("thumb_img", thumbImg);
    jsonObject.addProperty("sale_price", salePrice);
    jsonObject.addProperty("market_price", marketPrice);
    jsonObject.addProperty("stock_num", stockNum);
    jsonObject.addProperty("sku_code", skuCode);
    jsonObject.addProperty("barcode", barCode);
    JsonArray jsonArray = new JsonArray();
    for (WxMinishopGoodsSkuAttr attr : skuAttrs) {
      jsonArray.add(attr.toJsonObject());
    }
    jsonObject.add("sku_attrs", jsonArray);
    return jsonObject;
  }
}
