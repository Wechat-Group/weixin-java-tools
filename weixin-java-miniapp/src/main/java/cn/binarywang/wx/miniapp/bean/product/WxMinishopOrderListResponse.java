package cn.binarywang.wx.miniapp.bean.product;

import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

/**
 * @author leiin
 * created on  2022/6/20 7:09 下午
 */
@Data
public class WxMinishopOrderListResponse extends WxMaShopBaseResponse {
  private List<WxMinishopOrderResult> orders;
  @SerializedName("total_num")
  private Long totalNum;
}
