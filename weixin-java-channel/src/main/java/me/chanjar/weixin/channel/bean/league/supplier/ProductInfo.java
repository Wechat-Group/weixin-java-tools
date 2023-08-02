package me.chanjar.weixin.channel.bean.league.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.league.ExpressInfo;
import me.chanjar.weixin.channel.bean.league.SimpleProductInfo;

/**
 * 商品信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductInfo extends SimpleProductInfo {

  private static final long serialVersionUID = 5352334936089828219L;
  /** 快递信息 */
  @JsonProperty("express_info")
  private ExpressInfo expressInfo;

  /** sku信息 */
  @JsonProperty("skus")
  private List<SkuInfo> skus;
}
