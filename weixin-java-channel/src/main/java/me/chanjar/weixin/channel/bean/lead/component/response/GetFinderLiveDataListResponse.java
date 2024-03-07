package me.chanjar.weixin.channel.bean.lead.component.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

import java.util.List;

/**
 * 留资直播间数据详情
 * @author imyzt
 * @date 2024/01/27
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GetFinderLiveDataListResponse extends WxChannelBaseResponse {

  /**
   * 直播统计信息列表
   */
  @JsonProperty("item")
  private List<LiveStatisticsItem> items;

  /**
   * 本次翻页的上下文，用于顺序翻页请求
   */
  @JsonProperty("last_buffer")
  private String lastBuffer;

  /**
   * 是否还有直播
   */
  @JsonProperty("continue_flag")
  private boolean continueFlag;

  /**
   * 直播统计信息
   */
  @Data
  @NoArgsConstructor
  public static class LiveStatisticsItem {
    /**
     * 直播唯一id
     */
    @JsonProperty("export_id")
    private String exportId;

    /**
     * 开播时间戳
     */
    @JsonProperty("live_start_time")
    private Long liveStartTime;

    /**
     * 直播时长
     */
    @JsonProperty("live_duration_in_seconds")
    private Long liveDurationInSeconds;

    /**
     * 观看人数
     */
    @JsonProperty("total_audience_count")
    private Long totalAudienceCount;

    /**
     * 喝彩次数
     */
    @JsonProperty("total_cheer_count")
    private Long totalCheerCount;

    /**
     * 分享次数
     */
    @JsonProperty("forward_count")
    private Long forwardCount;

    /**
     * 评论条数
     */
    @JsonProperty("total_comment_count")
    private Long totalCommentCount;

    /**
     * 人均观看时长
     */
    @JsonProperty("audiences_avg_seconds")
    private Long audiencesAvgSeconds;

    /**
     * 最高在线人数
     */
    @JsonProperty("max_online_count")
    private Long maxOnlineCount;

    /**
     * 新增粉丝
     */
    @JsonProperty("new_follow_count")
    private Long newFollowCount;

    /**
     * 公众号新增粉丝
     */
    @JsonProperty("new_follow_count_biz")
    private Long newFollowCountBiz;
  }
}
