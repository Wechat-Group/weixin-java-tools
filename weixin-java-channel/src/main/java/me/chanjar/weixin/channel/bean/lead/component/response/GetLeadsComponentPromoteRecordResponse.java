package me.chanjar.weixin.channel.bean.lead.component.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

import java.util.List;

/**
 * 获取留资组件直播推广记录信息详情
 * @author imyzt
 * @date 2024/01/27
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GetLeadsComponentPromoteRecordResponse extends WxChannelBaseResponse {

  /**
   * 留资组件直播推广记录列表
   */
  @JsonProperty("record_data")
  private List<RecordData> recordData;

  /**
   * 本次翻页的上下文，用于顺序翻页请求
   */
  @JsonProperty("last_buffer")
  private String lastBuffer;

  /**
   * 是否还有留资信息
   */
  @JsonProperty("continue_flag")
  private boolean continueFlag;

  /**
   * 留资组件直播推广记录列表
   */
  @Data
  @NoArgsConstructor
  public static class RecordData {

    @JsonProperty("anchor_nickname")
    private String anchorNickname;

    @JsonProperty("live_description")
    private String liveDescription;

    @JsonProperty("live_start_time")
    private long liveStartTime;

    @JsonProperty("live_audience_count")
    private String liveAudienceCount;

    @JsonProperty("exposure_uv")
    private String exposureUV;

    @JsonProperty("click_uv")
    private String clickUV;

    @JsonProperty("exposure_click_rate")
    private double exposureClickRate;

    @JsonProperty("leads_num")
    private String leadsNum;
  }
}
