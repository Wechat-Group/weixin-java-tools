package me.chanjar.weixin.channel.bean.league.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 团长商品列表响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CoopProductListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -5440144076389135839L;
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

    private static final long serialVersionUID = -7136011408769169462L;
    /** 商品id */
    @JsonProperty("product_id")
    private String productId;

    /** 所属小店appid */
    @JsonProperty("appid")
    private String appid;

  }
}
