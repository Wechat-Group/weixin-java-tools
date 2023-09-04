package com.github.binarywang.wxpay.bean.profitsharing.request;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信V3接口-申请分账账单请求类
 *
 * @author 狂龙骄子
 * @since 4.4.0
 * @date 2022-12-09
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class ProfitSharingBillV3Request implements Serializable {
  private static final long serialVersionUID = 5200819754873844593L;

  /**
   * <pre>
   * 字段名：子商户号
   * 是否必填：否
   * 描述：不填则默认返回服务商下的所有分账账单。如需下载某个子商户下的分账账单，则填指定的子商户号。
   * </pre>
   */
  @SerializedName("sub_mchid")
  private String subMchId;

  /**
   * <pre>
   * 字段名：账单日期
   * 是否必填：是
   * 描述：格式yyyy-MM-DD，仅支持三个月内的账单下载申请。
   * </pre>
   */
  @SerializedName("bill_date")
  private String billDate;

  /**
   * <pre>
   * 字段名：压缩类型
   * 是否必填：否
   * 描述：不填则默认是数据流。枚举值：GZIP：返回格式为.gzip的压缩包账单。
   * </pre>
   */
  @SerializedName("tar_type")
  private String tarType;

}
