package me.chanjar.weixin.channel.bean.lead.component.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

import java.util.List;

/**
 * 获取账号收集的留资数据详情
 * @author imyzt
 * @date 2024/01/27
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GetFinderLiveLeadsDataResponse extends WxChannelBaseResponse {

  /**
   * 留资统计信息列表
   */
  @JsonProperty("item")
  private List<LeadCountItem> items;

  /**
   * 留资统计信息
   */
  @Data
  @NoArgsConstructor
  public static class LeadCountItem {

    /**
     * 组件类型
     * 0	表单
     * 1	企微名片
     * 2	企微客服
     */
    @JsonProperty("component_type")
    private int componentType;

    /**
     * 流量来源
     * 0	自然流量
     * 1	广告流量
     */
    @JsonProperty("traffic_type")
    private int trafficType;

    /**
     * 留资条数
     */
    @JsonProperty("leads_count")
    private int leadsCount;

  }
}
