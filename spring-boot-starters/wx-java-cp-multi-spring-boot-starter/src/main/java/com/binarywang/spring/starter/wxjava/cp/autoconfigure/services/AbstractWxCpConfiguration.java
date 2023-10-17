package com.binarywang.spring.starter.wxjava.cp.autoconfigure.services;

import com.binarywang.spring.starter.wxjava.cp.properties.CorpProperties;
import com.binarywang.spring.starter.wxjava.cp.properties.WxCpMultiProperties;
import com.binarywang.spring.starter.wxjava.cp.service.WxCpMultiServices;
import com.binarywang.spring.starter.wxjava.cp.service.WxCpMultiServicesImpl;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * WxCpConfigStorage 抽象配置类
 *
 * @author yl
 * created on 2023/10/16
 */
@RequiredArgsConstructor
public abstract class AbstractWxCpConfiguration {

  protected WxCpMultiServices configWxCpServices(WxCpMultiProperties wxCpMultiProperties) {
    WxCpMultiServicesImpl wxCpServices = new WxCpMultiServicesImpl();
    Map<String, CorpProperties> corps = wxCpMultiProperties.getCorps();
    if (corps == null || corps.isEmpty()) {
      throw new RuntimeException("xxxx");
    }
    Set<Map.Entry<String, CorpProperties>> entries = corps.entrySet();
    for (Map.Entry<String, CorpProperties> entry : entries) {
      String tenantId = entry.getKey();
      CorpProperties corpProperties = entry.getValue();
      WxCpDefaultConfigImpl storage = this.configWxCpDefaultConfigImpl(wxCpMultiProperties);
      this.configCorp(storage, corpProperties);
      this.configHttp(storage, wxCpMultiProperties.getConfigStorage());
      WxCpService wxCpService = this.configWxCpService(storage, wxCpMultiProperties.getConfigStorage());
      wxCpServices.addWxCpService(tenantId, wxCpService);
    }
    return wxCpServices;
  }

  /**
   * 配置 WxCpDefaultConfigImpl
   *
   * @param wxCpMultiProperties 参数
   * @return WxCpDefaultConfigImpl
   */
  protected abstract WxCpDefaultConfigImpl configWxCpDefaultConfigImpl(WxCpMultiProperties wxCpMultiProperties);

  private WxCpService configWxCpService(WxCpConfigStorage wxCpConfigStorage, WxCpMultiProperties.ConfigStorage storage) {
    WxCpService wxCpService = new WxCpServiceImpl();
    wxCpService.setWxCpConfigStorage(wxCpConfigStorage);

    int maxRetryTimes = storage.getMaxRetryTimes();
    if (maxRetryTimes < 0) {
      maxRetryTimes = 0;
    }
    int retrySleepMillis = storage.getRetrySleepMillis();
    if (retrySleepMillis < 0) {
      retrySleepMillis = 1000;
    }
    wxCpService.setRetrySleepMillis(retrySleepMillis);
    wxCpService.setMaxRetryTimes(maxRetryTimes);
    return wxCpService;
  }

  private void configCorp(WxCpDefaultConfigImpl config, CorpProperties corpProperties) {
    String corpId = corpProperties.getCorpId();
    String corpSecret = corpProperties.getCorpSecret();
    Integer agentId = corpProperties.getAgentId();
    String token = corpProperties.getToken();
    String aesKey = corpProperties.getAesKey();
    // 企业微信，私钥，会话存档路径
    String msgAuditPriKey = corpProperties.getMsgAuditPriKey();
    String msgAuditLibPath = corpProperties.getMsgAuditLibPath();

    config.setCorpId(corpId);
    config.setCorpSecret(corpSecret);
    config.setAgentId(agentId);
    if (StringUtils.isNotBlank(token)) {
      config.setToken(token);
    }
    if (StringUtils.isNotBlank(aesKey)) {
      config.setAesKey(aesKey);
    }
    if (StringUtils.isNotBlank(msgAuditPriKey)) {
      config.setMsgAuditPriKey(msgAuditPriKey);
    }
    if (StringUtils.isNotBlank(msgAuditLibPath)) {
      config.setMsgAuditLibPath(msgAuditLibPath);
    }
  }

  private void configHttp(WxCpDefaultConfigImpl config, WxCpMultiProperties.ConfigStorage storage) {
    String httpProxyHost = storage.getHttpProxyHost();
    Integer httpProxyPort = storage.getHttpProxyPort();
    String httpProxyUsername = storage.getHttpProxyUsername();
    String httpProxyPassword = storage.getHttpProxyPassword();
    if (StringUtils.isNotBlank(httpProxyHost)) {
      config.setHttpProxyHost(httpProxyHost);
      if (httpProxyPort != null) {
        config.setHttpProxyPort(httpProxyPort);
      }
      if (StringUtils.isNotBlank(httpProxyUsername)) {
        config.setHttpProxyUsername(httpProxyUsername);
      }
      if (StringUtils.isNotBlank(httpProxyPassword)) {
        config.setHttpProxyPassword(httpProxyPassword);
      }
    }
  }
}
