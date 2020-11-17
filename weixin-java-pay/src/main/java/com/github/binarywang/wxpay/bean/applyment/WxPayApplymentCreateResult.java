package com.github.binarywang.wxpay.bean.applyment;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 特约商户进件 提交申请结果响应
 *
 * @author zhouyongshen
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxPayApplymentCreateResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 微信支付申请单号
   */
  @SerializedName("applyment_id")
  private String applymentId;
}
