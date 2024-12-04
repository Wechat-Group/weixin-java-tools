package me.chanjar.weixin.channel.bean.lead.component.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 按直播场次获取留资信息详情
 * @author imyzt
 * @date 2024/01/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetLeadsInfoByRequestIdRequest {

  /**
   * 用于查询某个留资组件某场直播收集的留资信息
   */
  @JsonProperty("request_id")
  private String requestId;

  /**
   * 顺序翻页，传入上次请求返回的last_buffer, 会从上次返回的结果往后翻一页
   */
  @JsonProperty("last_buffer")
  private String lastBuffer;

  /**
   * 接口版本号,默认=1
   * =null和=1,微信返回的结构不一样,=1信息更全
   */
  @JsonProperty("version")
  private Integer version;

}
