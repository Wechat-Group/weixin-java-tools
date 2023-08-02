package me.chanjar.weixin.channel.bean.league.window;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 授权状态响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AuthStatusResponse extends WxChannelBaseResponse {

  /** 是否授权，0: 未授权, 1: 已授权 */
  @JsonProperty("window_auth_status")
  private Integer windowAuthStatus;
}
