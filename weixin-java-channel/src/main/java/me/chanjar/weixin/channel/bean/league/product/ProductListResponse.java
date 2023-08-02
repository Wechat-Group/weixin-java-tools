package me.chanjar.weixin.channel.bean.league.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 商品更新响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -6192518391380515045L;
  /** 商品列表 */
  @JsonProperty("items")
  private List<Item> items;

  /** 本次翻页的上下文，用于顺序翻页请求 */
  @JsonProperty("last_buffer")
  private String lastBuffer;

  /** 商品总数 */
  @JsonProperty("total_num")
  private Integer totalNum;

  /** 是否还有剩余商品 */
  @JsonProperty("has_more")
  private Boolean hasMore;

  @Data
  @NoArgsConstructor
  public static class Item implements Serializable {

    private static final long serialVersionUID = 5094378518992196239L;
    /** 商品id */
    @JsonProperty("product_id")
    private String productId;

    /** 特殊推广商品计划id */
    @JsonProperty("info_id")
    private String infoId;

  }
}
