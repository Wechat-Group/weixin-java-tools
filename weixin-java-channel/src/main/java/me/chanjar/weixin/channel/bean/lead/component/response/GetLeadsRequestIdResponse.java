package me.chanjar.weixin.channel.bean.lead.component.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

import java.util.List;

/**
 * 获取留资request_id列表详情
 * @author imyzt
 * @date 2024/01/27
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GetLeadsRequestIdResponse extends WxChannelBaseResponse {

  /**
   * 某一场直播对应的留资信息请求id
   */
  @JsonProperty("item")
  private List<LiveLeadItem> items;

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
   * 直播对应的留资信息
   */
  @Data
  @NoArgsConstructor
  public static class LiveLeadItem {

    /**
     * 某一场直播对应的留资信息请求id
     */
    @JsonProperty("request_id")
    private String requestId;

    /**
     * 直播开始时间
     */
    @JsonProperty("live_start_time")
    private Long liveStartTime;

    /**
     * 直播描述
     */
    @JsonProperty("live_description")
    private String liveDescription;

  }
}
