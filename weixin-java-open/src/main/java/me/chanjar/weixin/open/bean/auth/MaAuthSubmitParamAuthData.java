package me.chanjar.weixin.open.bean.auth;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * 小程序认证 提交操作 参数 数据
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * created on 2024/01/11
 */
@Data
@NoArgsConstructor
public class MaAuthSubmitParamAuthData {

  /**
   * 1企业 12个体户 15个人 参考:https://developers.weixin.qq.com/doc/oplatform/openApi/OpenApiDoc/miniprogram-management/basic-info-management/getAccountBasicInfo.html#realname-status-%E5%AE%9E%E5%90%8D%E8%AE%A4%E8%AF%81%E7%8A%B6%E6%80%81%E6%9E%9A%E4%B8%BE%E5%80%BC
   */
  @NotNull
  @SerializedName("customer_type")
  private String customerType;

  /**
   * 联系人信息
   */
  @NotNull
  @SerializedName("contact_info")
  private MaAuthSubmitParamContactInfo contactInfo;

  /**
   * 发票信息，如果是服务商代缴模式，不需要改参数
   */
  @Nullable
  @SerializedName("invoice_info")
  private MaAuthSubmitParamInvoiceInfo invoiceInfo;

  /**
   * 非个人类型必填。主体资质材料 media_id 支持jpg,jpeg .bmp.gif .png格式，仅支持一张图片
   */
  @Nullable
  private String qualification;

  /**
   * 主体资质其他证明材料 media_id 支持jpg,jpeg .bmp.gif .png格式，最多上传10张图片
   */
  @Nullable
  @SerializedName("qualification_other")
  private List<String> qualificationOther;

  /**
   * 小程序账号名称
   */
  @NotNull
  @SerializedName("account_name")
  private String accountName;

  /**
   * 小程序账号名称命名类型 1：基于自选词汇命名 2：基于商标命名
   */
  @NotNull
  @SerializedName("account_name_type")
  private Integer accountNameType;

  /**
   * 名称命中关键词-补充材料 media_id 支持jpg,jpeg .bmp.gif .png格式，支持上传多张图片
   */
  @Nullable
  @SerializedName("account_supplemental")
  private List<String> accountSupplemental;

  /**
   * 支付方式 1：消耗服务商预购包 2：小程序开发者自行支付
   */
  @NotNull
  @SerializedName("pay_type")
  private String payType;

  /**
   * 认证类型为个人类型时可以选择要认证的身份，从/wxa/sec/authidentitytree 里获取，填叶节点的name
   */
  @Nullable
  @SerializedName("auth_identification")
  private String authIdentification;

  /**
   * 填了auth_identification则必填。身份证明材料 media_id （1）基于不同认证身份上传不同的材料；（2）认证类型=1时选填，支持上传10张图片（3）支持jpg,jpeg .bmp.gif .png格式
   */
  @Nullable
  @SerializedName("auth_ident_material")
  private String authIdentMaterial;

  /**
   * 第三方联系电话
   */
  @NotNull
  @SerializedName("third_party_phone")
  private String thirdPartyPhone;

  /**
   * 选择服务商代缴模式时必填。服务市场appid
   */
  @Nullable
  @SerializedName("service_appid")
  private String serviceAppid;
}
