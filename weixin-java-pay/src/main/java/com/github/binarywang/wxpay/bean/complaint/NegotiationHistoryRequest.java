package com.github.binarywang.wxpay.bean.complaint;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 微信消费者投诉2.0
 * 查询投诉协商历史请求实体
 *
 * @author <a href="https://gitee.com/jeequan/jeepay">jmdhappy</a>
 * @date 2022-3-19
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class NegotiationHistoryRequest implements Serializable {

  private static final long serialVersionUID = 3244929701614280806L;

  /**
   * <pre>
   * 字段名：投诉单号
   * 是否必填：是
   * 描述：投诉单对应的投诉单号
   * </pre>
   */
  @SerializedName("complaint_id")
  private String complaintId;

  /**
   * <pre>
   * 字段名：分页大小
   * 是否必填：否
   * 描述：设置该次请求返回的最大投诉条数，范围【1,50】,商户自定义字段，不传默认为10。
   * 注：如遇到提示“当前查询结果数据量过大”，是回包触发微信支付下行数据包大小限制，请缩小入参limit并重试。
   * </pre>
   */
  @SerializedName("limit")
  private Integer limit = 10;

  /**
   * <pre>
   * 字段名：分页开始位置
   * 是否必填：否
   * 描述：该次请求的分页开始位置，从0开始计数，例如offset=10，表示从第11条记录开始返回，不传默认为0 。
   * </pre>
   */
  @SerializedName("offset")
  private Integer offset = 0;

}
