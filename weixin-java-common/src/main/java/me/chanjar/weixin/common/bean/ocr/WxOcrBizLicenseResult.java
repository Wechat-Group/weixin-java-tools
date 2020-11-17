package me.chanjar.weixin.common.bean.ocr;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * @author Theo Nie
 */
@Data
public class WxOcrBizLicenseResult implements Serializable {
  private static final long serialVersionUID = -5007671093920178291L;

  /**
   * 注册号
   */
  @SerializedName("reg_num")
  private String regNum;
  /**
   * 编号
   */
  @SerializedName("serial")
  private String serial;
  /**
   * 法定代表人姓名
   */
  @SerializedName("legal_representative")
  private String legalRepresentative;
  /**
   * 企业名称
   */
  @SerializedName("enterprise_name")
  private String enterpriseName;
  /**
   * 组成形式
   */
  @SerializedName("type_of_organization")
  private String typeOfOrganization;
  /**
   * 经营场所/企业住所
   */
  @SerializedName("address")
  private String address;
  /**
   * 公司类型
   */
  @SerializedName("type_of_enterprise")
  private String typeOfEnterprise;
  /**
   * 经营范围
   */
  @SerializedName("business_scope")
  private String businessScope;
  /**
   * 注册资本
   */
  @SerializedName("registered_capital")
  private String registeredCapital;
  /**
   * 实收资本
   */
  @SerializedName("paid_in_capital")
  private String paidInCapital;
  /**
   * 营业期限
   */
  @SerializedName("valid_period")
  private String validPeriod;
  /**
   * 注册日期/成立日期
   */
  @SerializedName("registered_date")
  private String registeredDate;
  /**
   * 营业执照位置
   */
  @SerializedName("cert_position")
  private CertPosition certPosition;
  /**
   * 图片大小
   */
  @SerializedName("img_size")
  private WxOcrImgSize imgSize;

  public static WxOcrBizLicenseResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxOcrBizLicenseResult.class);
  }

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }

  @Data
  public static class CertPosition implements Serializable {
    private static final long serialVersionUID = 290286813344131863L;

    @SerializedName("pos")
    private WxOcrPos pos;

    @Override
    public String toString() {
      return WxGsonBuilder.create().toJson(this);
    }
  }
}
