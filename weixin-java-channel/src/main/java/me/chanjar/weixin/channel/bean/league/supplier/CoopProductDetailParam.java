package me.chanjar.weixin.channel.bean.league.supplier;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 合作商品详情请求
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CoopProductDetailParam implements Serializable {

  private static final long serialVersionUID = 3515221514742929207L;
  /** 商品id */
  @JsonProperty("product_id")
  private String productId;

  /** 团长商品 所属小店appid */
  @JsonProperty("appid")
  private String appId;
}
