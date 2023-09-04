package me.chanjar.weixin.channel.bean.league.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品列表请求
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductListParam implements Serializable {

  private static final long serialVersionUID = -1914139382459786057L;
  /** 商品推广类别 */
  @JsonProperty("type")
  private Integer type;

  /** 单页商品数（不超过100） */
  @JsonProperty("page_size")
  private Integer pageSize;

  /** 页面下标，下标从1开始，默认为1 */
  @JsonProperty("page_index")
  private Integer pageIndex;

  /** 商品id，拉取特殊推广商品时有效 */
  @JsonProperty("product_id")
  private String productId;

  /** 视频号id，拉取特殊推广商品时有效 */
  @JsonProperty("finder_id")
  private String finderId;

  /** 由上次请求返回，顺序翻页时需要传入, 会从上次返回的结果往后翻一页（填了该值后page_index不生效） */
  @JsonProperty("last_buffer")
  private String lastBuffer;

  /** 是否需要返回满足筛选条件的商品总数（填last_buffer后该值无效） */
  @JsonProperty("need_total_num")
  private Boolean needTotalNum;
}
