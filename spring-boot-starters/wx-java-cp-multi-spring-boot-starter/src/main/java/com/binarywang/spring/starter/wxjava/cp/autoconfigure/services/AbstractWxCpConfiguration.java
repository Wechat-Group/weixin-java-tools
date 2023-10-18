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

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
      throw new RuntimeException("企业微信配置为null");
    }
    /**
     * 校验同一个企业下，agentId 是否唯一，避免使用 redis 缓存 token、ticket 时错乱。
     *
     * 查看 {@link me.chanjar.weixin.cp.config.impl.AbstractWxCpInRedisConfigImpl#setAgentId(Integer)}
     */
    Collection<CorpProperties> corpList = corps.values();
    if (corpList.size() > 1) {
      // 先按 corpId 分组统计
      Map<String, List<CorpProperties>> corpsMap = corpList.stream()
        .collect(Collectors.groupingBy(CorpProperties::getCorpId));
      Set<Map.Entry<String, List<CorpProperties>>> entries = corpsMap.entrySet();
      for (Map.Entry<String, List<CorpProperties>> entry : entries) {
        String corpId = entry.getKey();
        // 校验每个企业下，agentId 是否唯一
        boolean multi = entry.getValue().stream()
          // 通讯录没有 agentId，如果不判断是否为空，这里会报 NPE 异常
          .collect(Collectors.groupingBy(c -> c.getAgentId() == null ? 0 : c.getAgentId(), Collectors.counting()))
          .entrySet().stream().anyMatch(e -> e.getValue() > 1);
        if (multi) {
          throw new RuntimeException("请确保企业微信配置唯一性[" + corpId + "]");
        }
      }
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
