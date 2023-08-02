package me.chanjar.weixin.open.bean.minishop.limitdiscount;

import com.google.gson.JsonObject;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品抢购活动sku信息
 */
@Data
// 商品抢购活动sku
public class LimitDiscountSku implements Serializable {

  // 商品skuID
  private Long skuId;

  // 秒杀价格
  private BigDecimal salePrice;

  // 商品秒杀库存
  private Integer saleStock;

  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("sku_id", skuId);
    //需要将saleprice转换为以分为单位
    jsonObject.addProperty("sale_price", salePrice.multiply(new BigDecimal(100)).longValue());
    jsonObject.addProperty("sale_stock", saleStock);
    return jsonObject;
  }

}
