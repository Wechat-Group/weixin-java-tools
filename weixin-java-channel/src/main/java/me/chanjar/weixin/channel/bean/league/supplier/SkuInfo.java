package me.chanjar.weixin.channel.bean.league.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.AttrInfo;


/**
 * SkuInfo
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class SkuInfo implements Serializable {

  private static final long serialVersionUID = 197261426211990640L;
  /** skuID */
  @JsonProperty("sku_id")
  private String skuId;

  /** sku小图。如果添加时没录入，回包可能不包含该字段 */
  @JsonProperty("thumb_img")
  private String thumbImg;

  /** 售卖价格，以分为单位 */
  @JsonProperty("sale_price")
  private Integer salePrice;

  /** sku库存 */
  @JsonProperty("stock_num")
  private Integer stockNum;

  /** sku属性 */
  @JsonProperty("sku_attrs")
  private List<AttrInfo> skuAttrs;
}
