package cn.binarywang.wx.miniapp.bean.product;

import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

/**
 * @author leiin
 * created on  2022/7/8 3:46 下午
 */
@Data
public class WxMinishopGetBrandResponse extends WxMaShopBaseResponse {
  private List<MinishopBrandItem> brands;

  @Data
  public static class MinishopBrandItem {
    @SerializedName("first_cat_id")
    private Integer firstCatId;
    @SerializedName("second_cat_id")
    private Integer secondCatId;
    @SerializedName("third_cat_id")
    private Integer thirdCatId;
    @SerializedName("brand_info")
    private MinishopBrandInfo brandInfo;
  }

  @Data
  public static class MinishopBrandInfo {
    @SerializedName("brand_id")
    private Long brandId;
    @SerializedName("brand_name")
    private String brandName;
  }
}
