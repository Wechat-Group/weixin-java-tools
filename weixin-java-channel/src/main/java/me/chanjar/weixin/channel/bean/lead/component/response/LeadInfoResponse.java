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

    @JsonProperty("title")
    private String title;

    @JsonProperty("value")
    private String value;
  }
}
