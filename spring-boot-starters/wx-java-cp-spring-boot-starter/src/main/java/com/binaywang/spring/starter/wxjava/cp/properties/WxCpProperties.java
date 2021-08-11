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


    //---------------------------------------------------------------------
    // secret是企业应用里面用于保障数据安全的“钥匙”，每一个应用都有一个独立的访问密钥，为了保证数据的安全，secret务必不能泄漏。
    // 目前secret有：
    //
    // 自建应用secret。在管理后台->“应用与小程序”->“应用”->“自建”，点进某个应用，即可看到。
    // 基础应用secret。某些基础应用（如“审批”“打卡”应用），支持通过API进行操作。在管理后台->“应用与小程序”->“应用->”“基础”，点进某个应用，点开“API”小按钮，即可看到。
    //
    // 通讯录管理secret。在“管理工具”-“通讯录同步”里面查看（需开启“API接口同步”）；
    // 外部联系人管理secret。在“客户联系”栏，点开“API”小按钮，即可看到。
    //---------------------------------------------------------------------

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
