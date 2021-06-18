package me.chanjar.weixin.qidian.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 企点接口地址域名部分的自定义设置信息.
 *
 * @author alegria
 * @date 2020-12-24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxQidianHostConfig {
  public static final String API_DEFAULT_HOST_URL = "https://api.weixin.qq.com";
  public static final String OPEN_DEFAULT_HOST_URL = "https://open.weixin.qq.com";
  public static final String QIDIAN_DEFAULT_HOST_URL = "https://api.qidian.qq.com";

  /**
   * 对应于：https://api.weixin.qq.com
   */
  private String apiHost;

  /**
   * 对应于：https://open.weixin.qq.com
   */
  private String openHost;
  /**
   * 对应于：https://api.qidian.qq.com
   */
  private String qidianHost;

  public static String buildUrl(WxQidianHostConfig hostConfig, String prefix, String path) {
    if (hostConfig == null) {
      return prefix + path;
    }

    if (hostConfig.getApiHost() != null && prefix.equals(API_DEFAULT_HOST_URL)) {
      return hostConfig.getApiHost() + path;
    }

    if (hostConfig.getQidianHost() != null && prefix.equals(QIDIAN_DEFAULT_HOST_URL)) {
      return hostConfig.getQidianHost() + path;
    }

    if (hostConfig.getOpenHost() != null && prefix.equals(OPEN_DEFAULT_HOST_URL)) {
      return hostConfig.getOpenHost() + path;
    }

    return prefix + path;
  }
}
