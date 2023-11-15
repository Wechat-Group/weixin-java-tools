package com.github.binarywang.wxpay.bean.payscore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author UltramanNoa
 * @className PayScorePlanDetail
 * @description 支付分计划明细列表
 * @createTime 2023/11/3 11:22
 **/
@Data
@NoArgsConstructor
public class PayScorePlanDetailResult extends PayScorePlanDetailRequest implements Serializable {

  private static final long serialVersionUID = -2195861995542633650L;
  /**
   * 计划明细序号(返回参数)
   */
  @SerializedName("plan_detail_no")
  private Integer planDetailNo;
}
