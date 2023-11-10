package com.github.binarywang.wxpay.bean.payscore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author UltramanNoa
 * @className UserSignPlanDetailMerchatNo
 * @description 签约计划对应的计划详情列表的商户侧单号信息
 * @createTime 2023/11/3 15:51
 **/
@Data
@NoArgsConstructor
public class UserSignPlanDetailMerchatNo implements Serializable {

  private static final long serialVersionUID = 2668791598158720023L;

  /**
   * 计划明细序号
   */
  @SerializedName("plan_detail_no")
  private Integer planDetailNo;

  /**
   * 商户侧计划明细使用订单号
   */
  @SerializedName("merchant_plan_detail_no")
  private String merchantPlanDetailNo;
}
