package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaShopDeliveryGetCompanyListResponse extends WxMaShopBaseResponse implements Serializable {
  private static final long serialVersionUID = -1478684494303814483L;

  @SerializedName("company_list")
  private List<CompanyListBean> companyList;

  @Data
  public static class CompanyListBean implements Serializable {
    /**
     * delivery_id : SF
     * delivery_name : 顺丰速运
     */

    @SerializedName("delivery_id")
    private String deliveryId;
    @SerializedName("delivery_name")
    private String deliveryName;
  }
}
