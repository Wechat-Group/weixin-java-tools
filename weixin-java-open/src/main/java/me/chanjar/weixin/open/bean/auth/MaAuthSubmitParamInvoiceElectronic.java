package me.chanjar.weixin.open.bean.auth;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;

/**
 * 发票 - 电子发票
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * created on 2024/01/11
 */
@Data
@NoArgsConstructor
public class MaAuthSubmitParamInvoiceElectronic {

  /**
   * 纳税识别号（15位、17、18或20位）
   */
  @NotNull
  private String id;

  /**
   * 发票备注（选填）
   */
  @Nullable
  private String desc;
}
