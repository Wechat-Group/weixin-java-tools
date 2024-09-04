package com.binarywang.solon.wxjava.open.config.storage;

import com.binarywang.solon.wxjava.open.properties.WxOpenProperties;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;

/**
 * @author yl
 */
public abstract class AbstractWxOpenConfigStorageConfiguration {

  protected WxOpenInMemoryConfigStorage config(WxOpenInMemoryConfigStorage config, WxOpenProperties properties) {
    WxOpenProperties.ConfigStorage storage = properties.getConfigStorage();
    config.setWxOpenInfo(properties.getAppId(), properties.getSecret(), properties.getToken(), properties.getAesKey());
    config.setHttpProxyHost(storage.getHttpProxyHost());
    config.setHttpProxyUsername(storage.getHttpProxyUsername());
    config.setHttpProxyPassword(storage.getHttpProxyPassword());
    Integer httpProxyPort = storage.getHttpProxyPort();
    if (httpProxyPort != null) {
      config.setHttpProxyPort(httpProxyPort);
    }
    int maxRetryTimes = storage.getMaxRetryTimes();
    if (maxRetryTimes < 0) {
      maxRetryTimes = 0;
    }
    int retrySleepMillis = storage.getRetrySleepMillis();
    if (retrySleepMillis < 0) {
      retrySleepMillis = 1000;
    }
    config.setRetrySleepMillis(retrySleepMillis);
    config.setMaxRetryTimes(maxRetryTimes);
    return config;
  }
}
