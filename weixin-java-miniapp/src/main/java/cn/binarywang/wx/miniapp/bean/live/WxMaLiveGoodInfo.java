package cn.binarywang.wx.miniapp.bean.live;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 直播商品信息
 */
@Data
public class WxMaLiveGoodInfo implements Serializable {
  private static final long serialVersionUID = 5769245932149287574L;
  private Integer goodsId;
  private String coverImgUrl;
  private String url;
  private Integer priceType;
  private BigDecimal price;
  private BigDecimal price2;
  private String name;
  /**
   * 1, 2：表示是为api添加商品，否则是在MP添加商品
   */
  private String thirdPartyTag;
}
