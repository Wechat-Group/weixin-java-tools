package com.binaywang.spring.starter.wxjava.cp.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author caiqy
 */
@Data
@ConfigurationProperties(prefix = "wx.cp.tp")
public class WxCpTpProperties implements Serializable {

  private static final long serialVersionUID = 3217520552931601386L;

  /**
   * redis key 前缀
   */
  private String redisKeyPrefix = "wx:cp:tp";

  /**
   * 每个服务商同时也是一个企业微信的企业，都有唯一的corpid。获取此信息可在服务商管理后台“应用开发”－“通用开发参数”可查看
   */
  private String corpId;

  /**
   * 作为服务商身份的调用凭证，应妥善保管好该密钥，务必不能泄漏。
   */
  private String providerSecret;

  /**
   * 服务商的事件token
   */
  private String token;

  /**
   * 服务商的事件encoding aes key
   */
  private String aesKey;

  /**
   * 应用配置
   */
  private List<Suite> suites;

  @Data
  public static class Suite {
    /**
     * suiteid为应用的唯一身份标识
     */
    private String suiteId;

    /**
     * suite_secret为对应的调用身份密钥
     */
    private String suiteSecret;

    /**
     * Token用于计算签名
     */
    private String token;

    /**
     * EncodingAESKey用于消息内容加密，由英文或数字组成且长度为43位的自定义字符串
     */
    private String aesKey;
  }
}
