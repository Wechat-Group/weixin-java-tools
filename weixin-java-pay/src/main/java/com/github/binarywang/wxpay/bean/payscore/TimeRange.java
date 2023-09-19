package com.github.binarywang.wxpay.bean.payscore;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 服务时间范围.
 *
 * @author doger.wang
 * created on  2020-05-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeRange implements Serializable {
  private static final long serialVersionUID = 8169562173656314930L;
  /**
   * start_time : 20091225091010
   * end_time : 20091225121010
   */
  @SerializedName("start_time")
  private String startTime;
  @SerializedName("end_time")
  private String endTime;

  /**
   * 服务开始时间备注
   */
  @SerializedName("start_time_remark")
  private String startTimeRemark;

  /**
   * 服务结束时间备注
   */
  @SerializedName("end_time_remark")
  private String endTimeRemark;
}
