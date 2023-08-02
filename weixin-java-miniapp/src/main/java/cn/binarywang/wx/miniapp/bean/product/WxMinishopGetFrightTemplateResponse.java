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
public class WxMinishopGetFrightTemplateResponse extends WxMaShopBaseResponse {
  @SerializedName("template_list")
  private List<MinishopFeightTemplateItem> templateList;

  @Data
  public static class MinishopFeightTemplateItem {
    @SerializedName("template_id")
    private Long templateId;
    private String name;
    @SerializedName("valuation_type")
    private Integer valuationType;

  }
}
