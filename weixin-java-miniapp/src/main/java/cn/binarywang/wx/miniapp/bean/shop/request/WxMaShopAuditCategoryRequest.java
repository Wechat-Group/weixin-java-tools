package cn.binarywang.wx.miniapp.bean.shop.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author liming1019
 * @date 2021/8/12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaShopAuditCategoryRequest implements Serializable {
  private static final long serialVersionUID = -6730876344556487071L;

  /**
   * audit_req : {"license":["www.xxxxx.com"],"category_info":{"level1":7419,"level2":7439,"level3":7448,"certificate":["www.xxx.com"]}}
   */

  @SerializedName("audit_req")
  private AuditReqBean auditReq;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class AuditReqBean implements Serializable {
    /**
     * license : ["www.xxxxx.com"]
     * category_info : {"level1":7419,"level2":7439,"level3":7448,"certificate":["www.xxx.com"]}
     */

    @SerializedName("category_info")
    private CategoryInfoBean categoryInfo;
    @SerializedName("license")
    private List<String> license;

    @Data
    @Builder
    public static class CategoryInfoBean implements Serializable {
      /**
       * level1 : 7419
       * level2 : 7439
       * level3 : 7448
       * certificate : ["www.xxx.com"]
       */

      @SerializedName("level1")
      private Integer level1;
      @SerializedName("level2")
      private Integer level2;
      @SerializedName("level3")
      private Integer level3;
      @SerializedName("certificate")
      private List<String> certificate;
    }
  }
}
