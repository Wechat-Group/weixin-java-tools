package com.github.binarywang.wxpay.bean.applyment;

import com.github.binarywang.wxpay.bean.applyment.enums.AccountTypeEnum;
import com.github.binarywang.wxpay.bean.applyment.enums.SettlementVerifyStateEnum;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SettlementModifyStateQueryResult implements Serializable {

  private static final long serialVersionUID = 464614116838248296L;
  /**
   * 开户名称
   */
  @SerializedName("account_name")
  private String accountName;

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
  @SerializedName("account_number")
  private String accountNumber;

  /**
   * 审核状态
   * @see SettlementVerifyStateEnum
   */
  @SerializedName("verify_result")
  private SettlementVerifyStateEnum verifyResult;

  /**
   * 审核驳回原因
   */
  @SerializedName("verify_fail_reason")
  private String verifyFailReason;

  /**
   * 审核结果更新时间
   */
  @SerializedName("verify_finish_time")
  private String verifyFinishTime;


}
