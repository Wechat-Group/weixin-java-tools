package com.binaywang.spring.starter.wxjava.cp.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author caiqy
 */
@Data
@ConfigurationProperties(prefix = "wx.cp")
public class WxCpProperties implements Serializable {

  private static final long serialVersionUID = -6901799076021694608L;

  /**
   * 设置企业微信的corpId
   */
  private String corpId;

  private List<AppConfig> appConfigs;

  @Getter
  @Setter
  public static class AppConfig {
    /**
     * 设置企业微信应用的AgentId
     */
    private Integer agentId;

    /**
     * 设置企业微信应用的Secret
     */
    private String secret;

    /**
     * 设置企业微信应用的token
     */
    private String token;

    /**
     * 设置企业微信应用的EncodingAESKey
     */
    private String aesKey;

  }
}
