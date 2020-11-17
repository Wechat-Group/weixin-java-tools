package com.github.binarywang.wxpay.bean.payscore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 服务位置信息.
 *
 * @author doger.wang
 * @date 2020-05-19
 */
@Data
@NoArgsConstructor
public class Location implements Serializable {
  private static final long serialVersionUID = -4510224826631515344L;
  /**
   * start_location : 嗨客时尚主题展餐厅
   * end_location : 嗨客时尚主题展餐厅
   */
  @SerializedName("start_location")
  private String startLocation;
  @SerializedName("end_location")
  private String endLocation;
}
