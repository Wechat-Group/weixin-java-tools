package me.chanjar.weixin.channel.bean.lead.component.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取账号收集的留资数据详情
 * @author imyzt
 * @date 2024/01/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetFinderLiveLeadsDataRequest {

  /**
   * 开始时间
   */
  @JsonProperty("start_time")
  private Long startTime;

  /**
   * 结束时间
   */
  @JsonProperty("end_time")
  private Long endTime;

  /**
   * 来源类型
   * source_type	来源类型 0	直播
   */
  @JsonProperty("source_type")
  private Long sourceType;

}
