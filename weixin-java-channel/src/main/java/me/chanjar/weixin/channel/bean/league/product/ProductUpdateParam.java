package me.chanjar.weixin.channel.bean.league.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品更新请求
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductUpdateParam implements Serializable {

  private static final long serialVersionUID = -3519313269193693460L;
  /** 获取商品推广类别 */
  @JsonProperty("type")
  private Integer type;

  /** 商品id type为普通推广商品时必填 */
  @JsonProperty("product_id")
  private String productId;

  /** 特殊推广商品计划id type为特殊推广商品时必填 */
  @JsonProperty("info_id")
  private String infoId;

  /** 更新操作类别 */
  @JsonProperty("operate_type")
  private Integer operateType;

  /** 推广佣金[0, 90]% */
  @JsonProperty("ratio")
  private Integer ratio;

  /** 特殊推广信息 */
  @JsonProperty("exclusive_info")
  private ExclusiveInfo exclusiveInfo;


  /** 特殊推广信息 */
  @Data
  @NoArgsConstructor
  public static class ExclusiveInfo implements Serializable {

    private static final long serialVersionUID = -8120260214345369170L;
    /** 推广开始时间戳 */
    @JsonProperty("begin_time")
    private Long beginTime;

    /** 推广结束时间戳 */
    @JsonProperty("end_time")
    private Long endTime;

    /** 是否永久推广 */
    @JsonProperty("is_forerver")
    private Boolean forever;

    /** 新增推广达人视频号列表，不超过30个 */
    @JsonProperty("add_finder_ids")
    private List<String> addFinderIds;

    /** 删除推广达人视频号列表，不超过30个 */
    @JsonProperty("del_finder_ids")
    private List<String> delFinderIds;
  }
}
