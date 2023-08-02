package me.chanjar.weixin.channel.bean.league.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品删除请求
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDeleteParam implements Serializable {

  private static final long serialVersionUID = 9129737170370664633L;
  /** 获取商品推广类别 */
  @JsonProperty("type")
  private Integer type;

  /** 商品id type为普通推广商品时必填 */
  @JsonProperty("product_id")
  private String productId;

  /** 特殊推广商品计划id type为特殊推广商品时必填 */
  @JsonProperty("info_id")
  private String infoId;
}
