package me.chanjar.weixin.channel.bean.league.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品详情请求
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDetailParam implements Serializable {

  private static final long serialVersionUID = 7624234965127527565L;
  /** 获取商品推广类别 */
  @JsonProperty("type")
  private Integer type;

  /** 商品id type为普通推广商品时必填 */
  @JsonProperty("product_id")
  private String productId;

  /** 特殊推广商品计划id type为特殊推广商品时必填 */
  @JsonProperty("info_id")
  private String infoId;

  /** 是否获取特殊推广商品绑定的达人列表， type为特殊推广商品时有效 */
  @JsonProperty("need_relation")
  private Boolean needRelation;

  /** 拉取达人数 need_relation为真时必填 不超过50 */
  @JsonProperty("page_size")
  private Integer pageSize;

  /** need_relation为真时有效，页面下标，下标从1开始，默认为1 */
  @JsonProperty("page_index")
  private Integer pageIndex;

  /** need_relation为真时有效，是否需要返回该计划绑定达人总数 */
  @JsonProperty("need_total_num")
  private Boolean needTotalNum;

}
