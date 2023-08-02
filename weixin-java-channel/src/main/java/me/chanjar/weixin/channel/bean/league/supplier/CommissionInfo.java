package me.chanjar.weixin.channel.bean.league.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 跟佣信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class CommissionInfo implements Serializable {

  private static final long serialVersionUID = 3027131215096984236L;
  /** 商品带货状态 1已上架推广 2已下架推广 5已清退 */
  @JsonProperty("status")
  private Integer status;

  /** 服务费率[0, 1000000] */
  @JsonProperty("service_ratio")
  private Integer serviceRatio;

  /** 佣金费率[0, 1000000] */
  @JsonProperty("ratio")
  private Integer ratio;

  /** unix时间戳，合作开始时间 */
  @JsonProperty("start_time")
  private Long startTime;

  /** unix时间戳，合作结束时间 */
  @JsonProperty("end_time")
  private Long endTime;

  /** 带货链接 */
  @JsonProperty("link")
  private String link;
}
