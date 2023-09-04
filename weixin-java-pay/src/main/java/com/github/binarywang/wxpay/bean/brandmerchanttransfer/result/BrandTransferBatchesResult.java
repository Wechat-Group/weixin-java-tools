package com.github.binarywang.wxpay.bean.brandmerchanttransfer.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 品牌红包商家转账结果
 *
 * @author moran
 **/
@Data
@NoArgsConstructor
public class BrandTransferBatchesResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：商家品牌红包批次单号
   * 变量名：out_batch_no
   * 是否必填：是
   * 类型：string[5, 32]
   * 描述：
   *  品牌商户系统内部区分品牌红包批次单下不同品牌红包明细单的唯一标识，要求此参数只能由数字、大小写字母组成
   * 示例值：plfk2020042013
   * </pre>
   */
  @SerializedName("out_batch_no")
  private String outBatchNo;

  /**
   * <pre>
   * 字段名：微信支付品牌红包批次单号
   * 变量名：batch_no
   * 是否必填：是
   * 类型：string[32, 64]
   * 描述：
   *  微信批次单号，微信商家转账系统返回的唯一标识
   * 示例值：1210000071100999991182020050700019480001
   * </pre>
   */
  @SerializedName("batch_no")
  private String batchNo;

  /**
   * <pre>
   * 字段名：品牌红包批次创建时间
   * 变量名：create_time
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  批次受理成功时返回，遵循rfc3339标准格式，
   *  格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
   *  例如：2015-05-20T13:29:35+08:00表示北京时间2015年05月20日13点29分35秒
   * 示例值：2015-05-20T13:29:35+08:00
   * </pre>
   */
  @SerializedName("create_time")
  private String createTime;
}
