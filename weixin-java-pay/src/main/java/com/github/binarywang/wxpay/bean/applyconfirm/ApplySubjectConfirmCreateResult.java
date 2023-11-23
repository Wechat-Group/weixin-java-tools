package com.github.binarywang.wxpay.bean.applyconfirm;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 间连商户开户意愿确认 提交申请结果响应
 *
 * @author <a href="https://github.com/wslongchen">Mr.Pan</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ApplySubjectConfirmCreateResult implements Serializable {

  private static final long serialVersionUID = 6171290256346697399L;
  /**
   * 微信支付申请单号
   */
  @SerializedName("applyment_id")
  private String applymentId;
}
