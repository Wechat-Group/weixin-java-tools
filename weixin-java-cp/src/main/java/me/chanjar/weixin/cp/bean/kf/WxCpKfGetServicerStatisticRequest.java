package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取「客户数据统计」接待人员明细数据
 *
 * @author MsThink  created on  2023/5/13
 */
@NoArgsConstructor
@Data
public class WxCpKfGetServicerStatisticRequest {
  /**
   * 客服帐号ID
   */
  @SerializedName("open_kfid")
  private String openKfId;

  /**
   * 接待人员的userid。第三方应用为密文userid，即open_userid
   */
  @SerializedName("servicer_userid")
  private String servicerUserid;

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
