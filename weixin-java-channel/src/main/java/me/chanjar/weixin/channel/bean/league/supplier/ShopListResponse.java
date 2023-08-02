package me.chanjar.weixin.channel.bean.league.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 合作小店列表响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 1736467471867767456L;
  /** 小店详情 */
  @JsonProperty("shop_list")
  private List<ShopDetail> shopList;

  /** 本次翻页的上下文，用于顺序翻页请求 */
  @JsonProperty("next_key")
  private String nextKey;

  /** 是否还有剩余小店 */
  @JsonProperty("has_more")
  private Boolean hasMore;

  /** 小店详情 */
  @Data
  @NoArgsConstructor
  public static class ShopDetail implements Serializable {

    private static final long serialVersionUID = 8421286426372052694L;
    /** 小店基础信息 */
    @JsonProperty("base_info")
    private BizBaseInfo baseInfo;

    /** 小店状态 */
    @JsonProperty("status")
    private Integer status;
  }


}
