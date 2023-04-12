package me.chanjar.weixin.channel.bean.league.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 批量添加商品响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BatchAddResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 2686612709939873527L;
  /** 商品id信息 */
  @JsonProperty("result_info_list")
  private List<ResultInfo> resultInfoList;


  @Data
  @NoArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  public static class ResultInfo extends WxChannelBaseResponse {

    private static final long serialVersionUID = -534890760974302155L;
    /** 商品id */
    @JsonProperty("product_id")
    private String productId;

    /** 特殊推广商品计划id */
    @JsonProperty("info_id")
    private String infoId;

    /** 推广失败达人列表 */
    @JsonProperty("fail_finder_ids")
    private List<String> failFinderIds;
  }
}
