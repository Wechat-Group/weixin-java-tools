package me.chanjar.weixin.channel.bean.league.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 合作商品详情响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CoopProductResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -4066427847985394479L;
  /** 商品信息 */
  @JsonProperty("item")
  private Item item;


  @Data
  @NoArgsConstructor
  public static class Item implements Serializable {

    private static final long serialVersionUID = 6123572874440025928L;
    /** 所属小店appid */
    @JsonProperty("appid")
    private String appid;

    /** 商品id */
    @JsonProperty("product_id")
    private String productId;

    /** 商品信息 */
    @JsonProperty("product_info")
    private ProductInfo productInfo;

    /** 跟佣信息 */
    @JsonProperty("commission_info")
    private CommissionInfo commissionInfo;
  }
}
