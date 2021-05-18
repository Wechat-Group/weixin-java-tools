package com.binarywang.spring.starter.wxjava.open.config.storage;

import com.binarywang.spring.starter.wxjava.open.properties.WxOpenProperties;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;

/**
 * @author yl
 */
public abstract class AbstractWxOpenConfigStorageConfiguration {

  protected WxOpenInMemoryConfigStorage config(WxOpenInMemoryConfigStorage config, WxOpenProperties properties) {
    WxOpenProperties.ConfigStorage configStorageProperties = properties.getConfigStorage();
    config.setWxOpenInfo(properties.getAppId(), properties.getSecret(), properties.getToken(), properties.getAesKey());
    config.setHttpProxyHost(configStorageProperties.getHttpProxyHost());
    config.setHttpProxyUsername(configStorageProperties.getHttpProxyUsername());
    config.setHttpProxyPassword(configStorageProperties.getHttpProxyPassword());
    if (configStorageProperties.getHttpProxyPort() != null) {
      config.setHttpProxyPort(configStorageProperties.getHttpProxyPort());
    }
    int maxRetryTimes = configStorageProperties.getMaxRetryTimes();
    if (configStorageProperties.getMaxRetryTimes() < 0) {
      maxRetryTimes = 0;
    }
    int retrySleepMillis = configStorageProperties.getRetrySleepMillis();
    if (retrySleepMillis < 0) {
      retrySleepMillis = 1000;
    }
    config.setRetrySleepMillis(retrySleepMillis);
    config.setMaxRetryTimes(maxRetryTimes);
    return config;
  }
}
