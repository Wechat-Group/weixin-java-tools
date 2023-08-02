package me.chanjar.weixin.channel.bean.league.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 商品更新响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductUpdateResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 2144233059960259829L;
  /** 特殊推广商品计划id */
  @JsonProperty("info_id")
  private String infoId;
}
