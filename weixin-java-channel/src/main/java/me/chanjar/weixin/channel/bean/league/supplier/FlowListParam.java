package me.chanjar.weixin.channel.bean.league.supplier;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 团长流水列表请求参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlowListParam implements Serializable {

  private static final long serialVersionUID = 3128695806885851134L;
  /** 页码，从1开始 */
  @JsonProperty("page")
  private Integer page;

  /** 页数，不填默认为10 */
  @JsonProperty("page_size")
  private Integer pageSize;

  /** 资金类型, 见 {@link me.chanjar.weixin.channel.enums.FundsType} */
  @JsonProperty("funds_type")
  private Integer fundsType;

  /** 流水产生的开始时间，uinx时间戳 */
  @JsonProperty("start_time")
  private Long startTime;

  /** 流水产生的结束时间，unix时间戳 */
  @JsonProperty("end_time")
  private Long endTime;

  /** 分页参数，翻页时写入上一页返回的next_key(page为上一页加一，并且page_size与上一页相同的时候才生效)，page * page_size >= 5000时必填 */
  @JsonProperty("next_key")
  private String nextKey;
}
