package me.chanjar.weixin.channel.bean.lead.component.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

import java.util.List;

/**
 * 留资组件Id列表详情
 * @author imyzt
 * @date 2024/01/27
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GetLeadsComponentIdResponse extends WxChannelBaseResponse {

  /**
   * 留资组件信息
   */
  @JsonProperty("item")
  private List<LeadComponentItem> item;

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
   * 留资组件信息
   */
  @Data
  @NoArgsConstructor
  public static class LeadComponentItem {

    /**
     * 留资组件id
     */
    @JsonProperty("leads_component_id")
    private String leadsComponentId;

    /**
     * 留资组件标题
     */
    @JsonProperty("leads_description")
    private String leadsDescription;

    /**
     * 留资组件状态码
     */
    @JsonProperty("status")
    private int status;
  }
}
