package cn.binarywang.wx.miniapp.bean.product;

import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import lombok.Data;

/**
 * 获取订单详情 回包结构
 *
 * @author leiin
 * created on  2022/6/20 7:09 下午
 */
@Data
public class WxMinishopOrderDetailResponse extends WxMaShopBaseResponse {

  /**
   * 订单结构
   */
  private WxMinishopOrderResult order;
}
