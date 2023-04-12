package me.chanjar.weixin.channel.bean.league.window;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 授权信息响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AuthInfoResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 8336998502585278489L;
  /** 授权链接信息 */
  @JsonProperty("auth_info")
  private AuthInfo authInfo;

  /** 视频号openfinderid */
  @JsonProperty("openfinderid")
  private String openfinderid;
}
