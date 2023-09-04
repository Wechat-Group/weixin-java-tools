package me.chanjar.weixin.channel.bean.league.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;


/**
 * 团长订单列表响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CommissionOrderListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 1143184321517598592L;
  /** 商品id信息 */
  @JsonProperty("list")
  private List<ProductIdInfo> list;

  /** 本次翻页的上下文，用于顺序翻页请求 */
  @JsonProperty("next_key")
  private String nextKey;

  /** 是否还有剩余商品 */
  @JsonProperty("has_more")
  private Boolean hasMore;


  @Data
  @NoArgsConstructor
  public static class ProductIdInfo implements Serializable {

    private static final long serialVersionUID = -691189837681217282L;
    /** 商品id */
    @JsonProperty("order_id")
    private String orderId;

    /** skuid */
    @JsonProperty("sku_id")
    private String skuId;

  }
}
