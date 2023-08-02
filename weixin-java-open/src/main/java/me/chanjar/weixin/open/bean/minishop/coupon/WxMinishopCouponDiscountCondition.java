package me.chanjar.weixin.open.bean.minishop.coupon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 小商店商品折扣券详细信息
 */
@Data
public class WxMinishopCouponDiscountCondition implements Serializable {
  private static final long serialVersionUID = 7020614663289497294L;

  /**
   * 商品折扣券打折金额
   */
  private Integer productCnt;

  /**
   * 商品id，商品折扣券需填写
   */
  private List<Integer> productIds;

  /**
   * 商品价格，满减券需填写
   */
  private Integer productPrice;

  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    jsonObject.addProperty("product_cnt", productCnt);
    jsonObject.add("product_ids", gson.toJsonTree(productIds));
    jsonObject.addProperty("product_price", productPrice);

    return jsonObject;
  }
}
