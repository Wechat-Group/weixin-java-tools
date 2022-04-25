package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取「客户数据统计」企业汇总数据
 *
 * @author zhongjun
 * @date 2022/4/25
 **/
@NoArgsConstructor
@Data
public class WxCpKfGetCorpStatisticRequest {

  /**
   * 客服帐号ID。不传入时返回的数据为企业维度汇总的数据
   */
  @SerializedName("open_kfid")
  private String openKfId;

  /**
   * 起始日期的时间戳，填这一天的0时0分0秒（否则系统自动处理为当天的0分0秒）。取值范围：昨天至前180天
   */
  @SerializedName("start_time")
  private Long startTime;
  /**
   * 结束日期的时间戳，填这一天的0时0分0秒（否则系统自动处理为当天的0分0秒）。取值范围：昨天至前180天
   */
  @SerializedName("end_time")
  private Long endTime;

}
