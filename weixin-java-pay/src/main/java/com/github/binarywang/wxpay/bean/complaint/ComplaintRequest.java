package com.github.binarywang.wxpay.bean.complaint;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 微信消费者投诉2.0
 * 查询投诉单列表请求实体
 *
 * @author <a href="https://gitee.com/jeequan/jeepay">jmdhappy</a>
 * @date 2022-3-19
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintRequest implements Serializable {

  private static final long serialVersionUID = 3244929701614280800L;

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

  /**
   * <pre>
   * 字段名：开始日期
   * 是否必填：是
   * 描述：投诉发生的开始日期，格式为yyyy-MM-DD。注意，查询日期跨度不超过30天，当前查询为实时查询
   * </pre>
   */
  @SerializedName("begin_date")
  private String beginDate;

  /**
   * <pre>
   * 字段名：结束日期
   * 是否必填：是
   * 描述：投诉发生的结束日期，格式为yyyy-MM-DD。注意，查询日期跨度不超过30天，当前查询为实时查询
   * </pre>
   */
  @SerializedName("end_date")
  private String endDate;

  /**
   * <pre>
   * 字段名：被诉商户号
   * 是否必填：否
   * 描述：投诉单对应的被诉商户号。
   * </pre>
   */
  @SerializedName("complainted_mchid")
  private String complaintedMchid;

}
