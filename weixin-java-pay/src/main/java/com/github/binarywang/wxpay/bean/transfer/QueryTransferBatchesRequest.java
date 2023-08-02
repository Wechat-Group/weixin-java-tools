package com.github.binarywang.wxpay.bean.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 查询微信批次单号查询批次单API参数
 *
 * @author zhongjun
 * created on  2022/6/17
 **/
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class QueryTransferBatchesRequest implements Serializable {
  private static final long serialVersionUID = -2175582517588397426L;

  /**
   * 微信批次单号
   */
  private String batchId;

  /**
   * 是否查询转账明细单
   */
  private Boolean needQueryDetail;

  private Integer offset;

  private Integer limit;

  /**
   * 明细状态
   * 查询指定状态的转账明细单，当need_query_detail为true时，该字段必填
   * ALL:全部。需要同时查询转账成功和转账失败的明细单
   * SUCCESS:转账成功。只查询转账成功的明细单
   * FAIL:转账失败。只查询转账失败的明细单
   */
  private String detailStatus;

  /**
   * 商家批次单号
   */
  private String outBatchNo;
}
