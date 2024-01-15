package me.chanjar.weixin.open.bean.auth;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;

/**
 * 发票信息
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * created on 2024/01/11
 */
@Data
@NoArgsConstructor
public class MaAuthSubmitParamInvoiceInfo {

  /**
   * 发票类型 1: 不开发票 2: 电子发票 3: 增值税专票
   */
  @NotNull
  @SerializedName("invoice_type")
  private Integer invoiceType;

  /**
   * 发票类型=2时必填 电子发票开票信息
   */
  @Nullable
  private MaAuthSubmitParamInvoiceElectronic electronic;

  /**
   * 发票类型=3时必填 增值税专票开票信息
   */
  @Nullable
  private MaAuthSubmitParamInvoiceVat vat;

  /**
   * 发票抬头，发票类型!=1时必填 需要和认证主体名称一样
   */
  @Nullable
  @SerializedName("invoice_title")
  private String invoiceTitle;
}
