package cn.binarywang.wx.miniapp.bean.live;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 直播商品信息
 *
 * @author unkown
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
  /**
   * <a href="https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/industry/liveplayer/pendant.html">see here</a>
   */
  private List<String> goodsKey;


  /**
   * 当商品为第三方小程序的商品则填写为对应第三方小程序的appid，自身小程序商品则为''
   */
  private String thirdPartyAppid;
}
