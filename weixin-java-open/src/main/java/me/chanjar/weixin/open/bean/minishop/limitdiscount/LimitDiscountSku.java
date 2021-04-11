package me.chanjar.weixin.open.bean.minishop.limitdiscount;

import com.google.gson.JsonObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品抢购活动sku信息
 */
@Data
@ApiModel("商品抢购活动sku")
public class LimitDiscountSku implements Serializable {

  @ApiModelProperty("商品skuID")
  private Long skuId;

  @ApiModelProperty("秒杀价格")
  private BigDecimal salePrice;

  @ApiModelProperty("商品秒杀库存")
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
