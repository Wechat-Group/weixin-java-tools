package me.chanjar.weixin.channel.bean.league.window;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 授权信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class AuthInfo implements Serializable {

  private static final long serialVersionUID = 6265034296219892453L;
  /** 授权链接 */
  @JsonProperty("auth_url")
  private String authUrl;

  /** 授权路径 */
  @JsonProperty("auth_wxa_path")
  private String authWxaPath;

  /** appid */
  @JsonProperty("auth_wxa_appid")
  private String authWxaAppid;

  /** 小程序name */
  @JsonProperty("auth_wxa_username")
  private String authWxaUsername;
}
