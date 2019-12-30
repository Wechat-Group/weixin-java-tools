package me.chanjar.weixin.mp.bean.ocr;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * @author Theo Nie
 */
@Data
public class WxMpOcrDrivingLicenseResult implements Serializable {
  private static final long serialVersionUID = -6984670645802585738L;

  /**
   * 证号
   */
  @SerializedName("id_num")
  private String idNum;
  /**
   * 姓名
   */
  @SerializedName("name")
  private String name;
  /**
   * 性别
   */
  @SerializedName("sex")
  private String sex;
  /**
   * 国籍
   */
  @SerializedName("nationality")
  private String nationality;
  /**
   * 住址
   */
  @SerializedName("address")
  private String address;
  /**
   * 出生日期
   */
  @SerializedName("birth_date")
  private String birthDate;
  /**
   * 初次领证日期
   */
  @SerializedName("issue_date")
  private String issueDate;
  /**
   * 准驾车型
   */
  @SerializedName("car_class")
  private String carClass;
  /**
   * 有效期限起始日
   */
  @SerializedName("valid_from")
  private String validFrom;
  /**
   * 有效期限终止日
   */
  @SerializedName("valid_to")
  private String validTo;
  /**
   * 印章文字
   */
  @SerializedName("official_seal")
  private String officialSeal;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  public static WxMpOcrDrivingLicenseResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpOcrDrivingLicenseResult.class);
  }
}
