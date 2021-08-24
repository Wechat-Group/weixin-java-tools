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
public class WxMaShopAuditBrandRequest implements Serializable {
  private static final long serialVersionUID = -969331692973992066L;

  /**
   * audit_req : {"license":["https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg"],"brand_info":{"brand_audit_type":1,"trademark_type":"29","brand_management_type":2,"commodity_origin_type":2,"brand_wording":"346225226351203275","sale_authorization":["https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg"],"trademark_registration_certificate":["https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg"],"trademark_change_certificate":["https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg"],"trademark_registrant":"https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg","trademark_registrant_nu":"1249305","trademark_authorization_period":"2020-03-25 12:05:25","trademark_registration_application":["https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg"],"trademark_applicant":"张三","trademark_application_time":"2020-03-25 12:05:25","imported_goods_form":["https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg"]}}
   */

  @SerializedName("audit_req")
  private AuditReqBean auditReq;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class AuditReqBean implements Serializable {
    /**
     * license : ["https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg"]
     * brand_info : {"brand_audit_type":1,"trademark_type":"29","brand_management_type":2,"commodity_origin_type":2,"brand_wording":"346225226351203275","sale_authorization":["https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg"],"trademark_registration_certificate":["https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg"],"trademark_change_certificate":["https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg"],"trademark_registrant":"https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg","trademark_registrant_nu":"1249305","trademark_authorization_period":"2020-03-25 12:05:25","trademark_registration_application":["https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg"],"trademark_applicant":"张三","trademark_application_time":"2020-03-25 12:05:25","imported_goods_form":["https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg"]}
     */

    @SerializedName("brand_info")
    private BrandInfoBean brandInfo;
    @SerializedName("license")
    private List<String> license;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BrandInfoBean implements Serializable {
      /**
       * brand_audit_type : 1
       * trademark_type : 29
       * brand_management_type : 2
       * commodity_origin_type : 2
       * brand_wording : 346225226351203275
       * sale_authorization : ["https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg"]
       * trademark_registration_certificate : ["https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg"]
       * trademark_change_certificate : ["https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg"]
       * trademark_registrant : https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg
       * trademark_registrant_nu : 1249305
       * trademark_authorization_period : 2020-03-25 12:05:25
       * trademark_registration_application : ["https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg"]
       * trademark_applicant : 张三
       * trademark_application_time : 2020-03-25 12:05:25
       * imported_goods_form : ["https://img.zhls.qq.com/3/609b98f7e0ff43d59ce6d9cca636c3e0.jpg"]
       */

      @SerializedName("brand_audit_type")
      private Integer brandAuditType;
      @SerializedName("trademark_type")
      private String trademarkType;
      @SerializedName("brand_management_type")
      private Integer brandManagementType;
      @SerializedName("commodity_origin_type")
      private Integer commodityOriginType;
      @SerializedName("brand_wording")
      private String brandWording;
      @SerializedName("trademark_registrant")
      private String trademarkRegistrant;
      @SerializedName("trademark_registrant_nu")
      private String trademarkRegistrantNu;
      @SerializedName("trademark_authorization_period")
      private String trademarkAuthorizationPeriod;
      @SerializedName("trademark_applicant")
      private String trademarkApplicant;
      @SerializedName("trademark_application_time")
      private String trademarkApplicationTime;
      @SerializedName("sale_authorization")
      private List<String> saleAuthorization;
      @SerializedName("trademark_registration_certificate")
      private List<String> trademarkRegistrationCertificate;
      @SerializedName("trademark_change_certificate")
      private List<String> trademarkChangeCertificate;
      @SerializedName("trademark_registration_application")
      private List<String> trademarkRegistrationApplication;
      @SerializedName("imported_goods_form")
      private List<String> importedGoodsForm;
    }
  }
}

