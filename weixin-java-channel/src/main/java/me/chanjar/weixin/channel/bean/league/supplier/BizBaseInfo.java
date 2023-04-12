package me.chanjar.weixin.channel.bean.league.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 小店基础信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class BizBaseInfo implements Serializable {

  private static final long serialVersionUID = 3713638025924977002L;
  /** 小店appid */
  @JsonProperty("appid")
  private String appid;

  /** 小店头像 */
  @JsonProperty("headimg_url")
  private String headimgUrl;

  /** 小店昵称 */
  @JsonProperty("nickname")
  private String nickname;
}
