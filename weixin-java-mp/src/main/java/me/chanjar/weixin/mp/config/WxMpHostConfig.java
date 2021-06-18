package me.chanjar.weixin.mp.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 微信接口地址域名部分的自定义设置信息.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-06-09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMpHostConfig {
  public static final String API_DEFAULT_HOST_URL = "https://api.weixin.qq.com";
  public static final String MP_DEFAULT_HOST_URL = "https://mp.weixin.qq.com";
  public static final String OPEN_DEFAULT_HOST_URL = "https://open.weixin.qq.com";

  /**
   * 对应于：https://api.weixin.qq.com
   */
  private String apiHost;

  /**
   * 对应于：https://open.weixin.qq.com
   */
  private String openHost;

  /**
   * 对应于：https://mp.weixin.qq.com
   */
  private String mpHost;

  public static String buildUrl(WxMpHostConfig hostConfig, String prefix, String path) {
    if (hostConfig == null) {
      return prefix + path;
    }

    if (hostConfig.getApiHost() != null && prefix.equals(API_DEFAULT_HOST_URL)) {
      return hostConfig.getApiHost() + path;
    }

    if (hostConfig.getMpHost() != null && prefix.equals(MP_DEFAULT_HOST_URL)) {
      return hostConfig.getMpHost() + path;
    }

    if (hostConfig.getOpenHost() != null && prefix.equals(OPEN_DEFAULT_HOST_URL)) {
      return hostConfig.getOpenHost() + path;
    }

    return prefix + path;
  }
}
