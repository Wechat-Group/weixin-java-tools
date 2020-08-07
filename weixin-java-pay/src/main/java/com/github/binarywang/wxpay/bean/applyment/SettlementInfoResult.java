package com.github.binarywang.wxpay.bean.applyment;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 查询结算账户返回对象信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SettlementInfoResult {

    /**
     * 账户类型
     */
    @SerializedName("account_type")
    private String accountType;
    /**
     * 开户银行
     */
    @SerializedName("account_bank")
    private String accountBank;
    /**
     * 开户银行全称（含支行]
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
     * 汇款验证结果
     * @see com.github.binarywang.wxpay.bean.applyment.enums.SettlementVerifyResultEnum
     */
    @SerializedName("verify_result")
    private String verifyResult;
}
