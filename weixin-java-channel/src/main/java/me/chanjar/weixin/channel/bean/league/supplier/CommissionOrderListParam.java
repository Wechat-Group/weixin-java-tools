package me.chanjar.weixin.channel.bean.league.supplier;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.TimeRange;

/**
 * 佣金单列表请求参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommissionOrderListParam implements Serializable {

  private static final long serialVersionUID = 2805783646567362357L;
  /** 佣金单所属小店appid */
  @JsonProperty("appid")
  private String appid;

  /** 视频号finder_id */
  @JsonProperty("finder_id")
  private String finderId;

  /** 视频号openfinderid */
  @JsonProperty("openfinderid")
  private String openfinderid;

  /** 佣金单创建时间范围 */
  @JsonProperty("create_time_range")
  private TimeRange createTimeRange;

  /** 佣金单更新时间范围 */
  @JsonProperty("update_time_range")
  private TimeRange updateTimeRange;

  /** 订单ID，填此参数后其他过滤参数无效 */
  @JsonProperty("order_id")
  private String orderId;

  /** 单页佣金单数（不超过30） */
  @JsonProperty("page_size")
  private Integer pageSize;

  /** 由上次请求返回，顺序翻页时需要传入, 会从上次返回的结果往后翻一页 */
  @JsonProperty("next_key")
  private String nextKey;

}
