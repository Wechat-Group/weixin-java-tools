package cn.binarywang.wx.miniapp.bean.product;

import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author leiin
 * created on  2022/7/8 3:39 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMinishopGetCategoryResponse extends WxMaShopBaseResponse {
  @SerializedName("cat_list")
  private List<MinishopCatItem> catList;

  @Data
  public static class MinishopCatItem {

    @SerializedName("cat_id")
    private Integer catId;

    @SerializedName("f_cat_id")
    private Integer fCatId;

    private String name;
  }
}
