package me.chanjar.weixin.channel.bean.lead.component.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 留资直播间数据详情
 * @author imyzt
 * @date 2024/01/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetFinderLiveDataListRequest {

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
   * 顺序翻页，传入上次请求返回的last_buffer, 会从上次返回的结果往后翻一页
   */
  @JsonProperty("last_buffer")
  private String lastBuffer;

}
