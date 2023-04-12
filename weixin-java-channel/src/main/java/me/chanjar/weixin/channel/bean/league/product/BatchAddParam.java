package me.chanjar.weixin.channel.bean.league.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 批量添加商品参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BatchAddParam implements Serializable {

  private static final long serialVersionUID = -87989229725625901L;
  /** 商品推广类别 */
  @JsonProperty("type")
  private Integer type;

  /** 商品列表 */
  @JsonProperty("list")
  private List<Product> list;

  /** 推广达人列表 */
  @JsonProperty("finder_ids")
  private List<String> finderIds;

  /** 推广开始时间戳 */
  @JsonProperty("begin_time")
  private Long beginTime;

  /** 推广结束时间戳 */
  @JsonProperty("end_time")
  private Long endTime;

  /** 是否永久推广 */
  @JsonProperty("is_forerver")
  private Boolean forever;


  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class Product implements Serializable {

    private static final long serialVersionUID = 9025105293896488093L;
    /** 商品id，不可重复数量不超过20 */
    @JsonProperty("product_id")
    private String productId;

    /** 推广佣金[0, 90]% */
    @JsonProperty("ratio")
    private Integer ratio;

  }
}
