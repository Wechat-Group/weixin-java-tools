package cn.binarywang.wx.miniapp.bean.shop.response;

import cn.binarywang.wx.miniapp.bean.shop.WxMaShopCouponInfo;
import lombok.Data;

/**
 * @author leiin
 * created on  2022/7/1 3:34 下午
 */
@Data
public class WxMaShopCouponResponse extends WxMaShopBaseResponse {
  private ResponseCouponResult result;

  @Data
  public static class ResponseCouponResult {
    private WxMaShopCouponInfo coupon;
  }
}
