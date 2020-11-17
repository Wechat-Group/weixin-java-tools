package com.github.binarywang.wxpay.bean.applyment;

import com.github.binarywang.wxpay.bean.applyment.enums.AccountTypeEnum;
import com.github.binarywang.wxpay.v3.SpecEncrypt;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 修改结算账户请求对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ModifySettlementRequest implements Serializable {
  private static final long serialVersionUID = 4568552340365230872L;
  /**
   * 账户类型
   */
  @SerializedName("account_type")
  private AccountTypeEnum accountType;
  /**
   * 开户银行
   */
  @SerializedName("account_bank")
  private String accountBank;
  /**
   * 开户银行省市编码
   */
  @SerializedName("bank_address_code")
  private String bankAddressCode;
  /**
   * 开户银行全称（含支行）
   */
  @SerializedName("bank_name")
  private String bankName;
  /**
   * 开户银行联行号
   */
  @SerializedName("bank_branch_id")
  private String bankBranchId;

  /**
   * 银行账号
   */
  @SpecEncrypt
  @SerializedName("account_number")
  private String accountNumber;
}
