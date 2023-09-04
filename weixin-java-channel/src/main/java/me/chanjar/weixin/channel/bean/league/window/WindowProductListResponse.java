package me.chanjar.weixin.channel.bean.league.window;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 商品列表响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WindowProductListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -1160519267422259848L;
  /** 商品概要列表 */
  @JsonProperty("list")
  private List<ItemKey> list;

  /** 下一页的位置 */
  @JsonProperty("next_offset")
  private Integer nextOffset;

  /** 后面是否还有商品 */
  @JsonProperty("have_more")
  private Boolean haveMore;

  /** 商品总数 */
  @JsonProperty("total_num")
  private Integer totalNum;

  /** 商品概要列表 */
  @Data
  @NoArgsConstructor
  public static class ItemKey implements Serializable {

    /** 团长appid */
    @JsonProperty("appid")
    private String appid;

    /** 团长商品ID */
    @JsonProperty("product_id")
    private String productId;

    /** 团长ID */
    @JsonProperty("head_supplier_id")
    private String headSupplierId;
  }

}
