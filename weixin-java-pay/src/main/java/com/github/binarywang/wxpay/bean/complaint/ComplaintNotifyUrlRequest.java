package com.github.binarywang.wxpay.bean.complaint;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 微信消费者投诉2.0
 * 投诉通知请求实体
 *
 * @author <a href="https://gitee.com/jeequan/jeepay">jmdhappy</a>
 * @date 2022-3-19
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintNotifyUrlRequest implements Serializable {

  private static final long serialVersionUID = -1L;

  /**
   * <pre>
   * 字段名：通知地址
   * 是否必填：是
   * 描述：通知地址，仅支持https。
   * </pre>
   */
  @SerializedName("url")
  private String url;

}
