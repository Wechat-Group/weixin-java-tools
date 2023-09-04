package me.chanjar.weixin.channel.bean.league.window;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.league.SimpleProductInfo;

/**
 * 商品详情响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WindowProductResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -4671578350314241014L;
  /** 商品详情 */
  @JsonProperty("product_detail")
  private ProductDetail productDetail;


  /**
   * 商品详情
   */
  @Data
  @NoArgsConstructor
  public static class ProductDetail implements Serializable {

    private static final long serialVersionUID = -6574563870972328273L;
    /** 所属小店appid */
    @JsonProperty("appid")
    private String appid;

    /** 商品id */
    @JsonProperty("product_id")
    private String productId;

    /** 商品信息 */
    @JsonProperty("product_info")
    private SimpleProductInfo productInfo;


  }
}
