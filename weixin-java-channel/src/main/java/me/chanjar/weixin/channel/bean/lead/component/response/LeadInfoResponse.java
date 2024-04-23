package me.chanjar.weixin.channel.bean.lead.component.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

import java.util.List;

/**
 * 留资信息详情
 * @author imyzt
 * @date 2024/01/27
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LeadInfoResponse extends WxChannelBaseResponse {

  /**
   * 用户留资信息列表
   */
  @JsonProperty("user_data")
  private List<UserData> userData;

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
   * 用户留资信息
   */
  @Data
  @NoArgsConstructor
  public static class UserData {

    /**
     * 主播昵称
     */
    @JsonProperty("anchor_nickname")
    private String anchorNickname;

    /**
     * 直播开始时间
     */
    @JsonProperty("live_start_time")
    private Long liveStartTime;

    /**
     * 	用户留资信息列表
     */
    @JsonProperty("leads_data")
    private List<LeadsData> leadsData;

    /**
     * 用户留资时间
     */
    @JsonProperty("time")
    private Long time;

  }

  @Data
  @NoArgsConstructor
  public static class LeadsData {

    /**
     * 表单名称
     */
    @JsonProperty("title")
    private String title;

    /**
     * 手机号,文本框,单选框时, 均为字符串
     * 仅当title=城市 时, 微信返回字符串数组, eg: ["北京市","北京市","东城区"]
     */
    @JsonProperty("value")
    private Object value;
  }
}
