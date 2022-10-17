package cn.binarywang.wx.miniapp.bean.shop.response;

import cn.binarywang.wx.miniapp.bean.shop.WxMaShopCouponInfo;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

/**
 * @author leiin
 * created on  2022/7/1 3:34 下午
 */
@Data
public class WxMaShopCouponListResponse extends WxMaShopBaseResponse {
  @SerializedName("total_num")
  private Long totalNum;
  @SerializedName("result_list")
  private List<ResponseCouponResult> resultList;

  @Data
  public static class ResponseCouponResult {
    private WxMaShopCouponInfo coupon;
  }
}
