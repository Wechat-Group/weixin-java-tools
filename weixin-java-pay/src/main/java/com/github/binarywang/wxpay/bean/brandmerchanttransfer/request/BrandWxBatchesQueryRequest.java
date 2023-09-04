package com.github.binarywang.wxpay.bean.brandmerchanttransfer.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 品牌红包微信支付批次单号查询批次单API参数
 *
 * @author moran
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BrandWxBatchesQueryRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：微信支付品牌红包批次单号
   * 变量名：batch_no
   * 是否必填：是
   * 类型：string[32, 64]
   * 描述：
   *  path微信支付品牌红包批次单号，微信商家品牌红包系统返回的唯一标识
   * 示例值：1030000071100999991182020050700019480001
   * </pre>
   */
  @SerializedName("batch_no")
  private String batchNo;

  /**
   * <pre>
   * 字段名：是否需要查询品牌红包明细单
   * 变量名：need_query_detail
   * 是否必填：是
   * 类型：boolean
   * 描述：
   *  query枚举值：
   *  true：是；
   *  false：否，默认否。
   * 商户可选择是否查询指定状态的品牌红包明细单，当品牌红包批次单状态为“FINISHED”（已完成）时，才会返回满足条件的品牌红包明细单
   * 示例值：true
   * </pre>
   */
  @SerializedName("need_query_detail")
  private Boolean needQueryDetail;

  /**
   * <pre>
   * 字段名：品牌红包明细单状态
   * 变量名：detail_status
   * 是否必填：否
   * 类型：string[1,32]
   * 描述：
   *  query查询指定状态的品牌红包明细单信息
   * DETAIL_VIEW_ALL - 全部，需要同时查询发送成功和发送失败的品牌红包明细单
   * DETAIL_VIEW_SUCCESS - 发送成功，只查询发送成功的品牌红包明细单
   * DETAIL_VIEW_FAIL - 发送失败，只查询发送失败的品牌红包明细单
   * 示例值：DETAIL_VIEW_FAIL
   * </pre>
   */
  @SerializedName("detail_state")
  private String detailState;
}
