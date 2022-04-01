package com.github.binarywang.wxpay.bean.complaint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 微信消费者投诉2.0
 * 反馈处理完成请求实体
 *
 * @author <a href="https://gitee.com/jeequan/jeepay">jmdhappy</a>
 * @date 2022-3-19
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class CompleteRequest implements Serializable {

  private static final long serialVersionUID = 3243229701614220801L;

  /**
   * <pre>
   * 字段名：投诉单号
   * 是否必填：是
   * 描述：投诉单对应的投诉单号
   * </pre>
   */
  @SerializedName("complaint_id")
  @Expose
  private String complaintId;

  /**
   * <pre>
   * 字段名：被诉商户号
   * 是否必填：是
   * 描述：投诉单对应的被诉商户号
   * </pre>
   */
  @SerializedName("complainted_mchid")
  private String complaintedMchid;

}
