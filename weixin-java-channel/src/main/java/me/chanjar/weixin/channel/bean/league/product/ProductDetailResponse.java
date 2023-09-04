package me.chanjar.weixin.channel.bean.league.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 商品详情响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class ProductDetailResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 5306524707144232861L;
  /** 推广商品信息 */
  @JsonProperty("item")
  private Item item;


  @Data
  @NoArgsConstructor
  public static class Item implements Serializable {

    private static final long serialVersionUID = 9112142704638318861L;
    /** 商品id */
    @JsonProperty("product_id")
    private String productId;

    /** 商品推广类别 1普通推广商品 2定向推广商品 3专属推广商品 */
    @JsonProperty("type")
    private Integer type;

    /** 商品推广状态 1已上架推广 2已下架推广 4已删除 5未达到准入标准 10待生效 */
    @JsonProperty("status")
    private Integer status;

    /** 推广佣金[0, 90]% */
    @JsonProperty("ratio")
    private Integer ratio;

    /** 特殊推广信息 */
    @JsonProperty("exclusive_info")
    private ExclusiveInfo exclusiveInfo;

    /** 扩展信息 */
    @JsonProperty("ext_info")
    private ExtInfo extInfo;
  }

  @Data
  @NoArgsConstructor
  public static class ExclusiveInfo implements Serializable {

    private static final long serialVersionUID = 6583124869090013797L;
    /** 特殊推广商品计划id */
    @JsonProperty("info_id")
    private String infoId;

    /** 推广开始时间戳 */
    @JsonProperty("begin_time")
    private Long beginTime;

    /** 推广结束时间戳 */
    @JsonProperty("end_time")
    private Long endTime;

    /** 是否永久推广 */
    @JsonProperty("is_forerver")
    private Boolean forever;

    /** 推广达人视频号列表 */
    @JsonProperty("finder_ids")
    private List<String> finderIds;

  }

  @Data
  @NoArgsConstructor
  public static class ExtInfo implements Serializable {

    /** 是否类目禁售 */
    @JsonProperty("is_sale_forbidden")
    private Boolean saleForbidden;

    /** 是否被官方封禁 */
    @JsonProperty("is_banned")
    private Boolean banned;

  }

}
