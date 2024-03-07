package me.chanjar.weixin.channel.bean.lead.component.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取留资request_id列表详情
 * @author imyzt
 * @date 2024/01/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetLeadsRequestIdRequest {

  /**
   * 用于查询某个留资组件某段时间内收集的留资信息
   */
  @JsonProperty("leads_component_id")
  private String leadsComponentId;

  /**
   * 顺序翻页，传入上次请求返回的last_buffer, 会从上次返回的结果往后翻一页
   */
  @JsonProperty("last_buffer")
  private String lastBuffer;

}
