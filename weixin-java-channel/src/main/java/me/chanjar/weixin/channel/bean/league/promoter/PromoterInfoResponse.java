package me.chanjar.weixin.channel.bean.league.promoter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 达人信息响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PromoterInfoResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 6475158486029216487L;
  /** 达人信息 */
  @JsonProperty("promoter")
  private PromoterInfo promoter;
}
