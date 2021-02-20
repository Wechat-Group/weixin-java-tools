package me.chanjar.weixin.open.bean.minishopGoods;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;

@Data
@Accessors(chain = true)
public class Sku {
  /**
   * 商家自定义商品ID   skus非空时必填
   */
  private String outProductId;
  /**
   * 商家自定义skuID   skus非空时必填
   */
  private String outSkuId;
  /**
   * sku小图   skus非空时必填
   */
  private String thumbImg;
  /**
   * 售卖价格,以分为单位   skus非空时必填
   */
  private Integer salePrice;
  /**
   * 市场价格,以分为单位   skus非空时必填
   */
  private Integer marketPrice;
  /**
   * 库存   skus非空时必填
   */
  private Integer stockNum;
  /**
   * 条形码   false
   */
  private String barcode;
  /**
   *商品编码 false
   */
  private String skuCode;
  /**
   * sku属性
   */
  private List<Attr> skuAttr;

  public JsonObject toJsonObject() {
    Gson gson = new Gson();
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("out_product_id", outProductId);
    jsonObject.addProperty("out_sku_id", outSkuId);
    jsonObject.addProperty("thumb_img", thumbImg);
    jsonObject.addProperty("sale_price", salePrice);
    jsonObject.addProperty("market_price", marketPrice);
    jsonObject.addProperty("stock_num", stockNum);
    jsonObject.addProperty("barcode", barcode);
    jsonObject.addProperty("sku_code", skuCode);
    jsonObject.addProperty("sku_attr",gson.toJson(skuAttr));

    return jsonObject;
  }


}
