package me.chanjar.weixin.open.bean.auth;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;

/**
 * 发票 - 增值税专票
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * created on 2024/01/11
 */
@Data
@NoArgsConstructor
public class MaAuthSubmitParamInvoiceVat {


  /**
   * 企业电话
   */
  @NotNull
  @SerializedName("enterprise_phone")
  private String enterprisePhone;

  /**
   * 纳税识别号（15位、17、18或20位）
   */
  @NotNull
  private String id;

  /**
   * 企业注册地址
   */
  @NotNull
  @SerializedName("enterprise_address")
  private String enterpriseAddress;

  /**
   * 企业开户银行
   */
  @NotNull
  @SerializedName("bank_name")
  private String bankName;

  /**
   * 企业银行账号
   */
  @NotNull
  @SerializedName("bank_account")
  private String bankAccount;

  /**
   * 发票邮寄地址邮编
   */
  @NotNull
  @SerializedName("mailing_address")
  private String mailingAddress;

  /**
   * 街道地址
   */
  @NotNull
  private String address;

  /**
   * 联系人
   */
  @NotNull
  private String name;

  /**
   * 联系电话
   */
  @NotNull
  private String phone;

  /**
   * 省份
   */
  @NotNull
  private String province;

  /**
   * 城市
   */
  @NotNull
  private String city;

  /**
   * 县区
   */
  @NotNull
  private String district;

  /**
   * 发票备注（选填）
   */
  @Nullable
  private String desc;
}
