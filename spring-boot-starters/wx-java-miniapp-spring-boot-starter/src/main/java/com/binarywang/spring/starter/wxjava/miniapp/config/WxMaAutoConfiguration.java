package com.binarywang.spring.starter.wxjava.miniapp.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceHttpClientImpl;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceJoddHttpImpl;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceOkHttpImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaRedisBetterConfigImpl;
import com.binarywang.spring.starter.wxjava.miniapp.enums.HttpClientType;
import com.binarywang.spring.starter.wxjava.miniapp.properties.ConfigStorage;
import com.binarywang.spring.starter.wxjava.miniapp.properties.RedisProperties;
import com.binarywang.spring.starter.wxjava.miniapp.properties.WxMaProperties;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.redis.JedisWxRedisOps;
import me.chanjar.weixin.common.redis.RedisTemplateWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 自动配置.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-08-10
 */
@AllArgsConstructor
@Configuration
@ConditionalOnClass(WxMaService.class)
@EnableConfigurationProperties(WxMaProperties.class)
@ConditionalOnProperty(prefix = "wx.miniapp", value = "enabled", matchIfMissing = true)
public class WxMaAutoConfiguration {

  private final WxMaProperties wxMaProperties;
  private final ApplicationContext applicationContext;

  /**
   * 小程序service.
   *
   * @return 小程序service
   */
  @Bean
  @ConditionalOnMissingBean(WxMaService.class)
  public WxMaService service(WxMaConfig wxMaConfig) {
    HttpClientType httpClientType = wxMaProperties.getConfigStorage().getHttpClientType();
    WxMaService wxMaService;
    if (httpClientType == HttpClientType.OkHttp) {
      wxMaService = new WxMaServiceOkHttpImpl();
    } else if (httpClientType == HttpClientType.JoddHttp) {
      wxMaService = new WxMaServiceJoddHttpImpl();
    } else if (httpClientType == HttpClientType.HttpClient) {
      wxMaService = new WxMaServiceHttpClientImpl();
    } else {
      wxMaService = new WxMaServiceImpl();
    }
    wxMaService.setWxMaConfig(wxMaConfig);
    return wxMaService;
  }

  @Bean
  @ConditionalOnMissingBean(WxMaConfig.class)
  public WxMaConfig wxMaConfig() {
    WxMaDefaultConfigImpl config;
    switch (wxMaProperties.getConfigStorage().getType()) {
      case Jedis:
        config = wxMaJedisConfigStorage();
        break;
      case RedisTemplate:
        config = wxMaRedisTemplateConfigStorage();
        break;
      default:
        config = wxMaDefaultConfigStorage();
        break;
    }

    config.setAppid(StringUtils.trimToNull(this.wxMaProperties.getAppid()));
    config.setSecret(StringUtils.trimToNull(this.wxMaProperties.getSecret()));
    config.setToken(StringUtils.trimToNull(this.wxMaProperties.getToken()));
    config.setAesKey(StringUtils.trimToNull(this.wxMaProperties.getAesKey()));
    config.setMsgDataFormat(StringUtils.trimToNull(this.wxMaProperties.getMsgDataFormat()));

    ConfigStorage configStorageProperties = wxMaProperties.getConfigStorage();
    config.setHttpProxyHost(configStorageProperties.getHttpProxyHost());
    config.setHttpProxyUsername(configStorageProperties.getHttpProxyUsername());
    config.setHttpProxyPassword(configStorageProperties.getHttpProxyPassword());
    if (configStorageProperties.getHttpProxyPort() != null) {
      config.setHttpProxyPort(configStorageProperties.getHttpProxyPort());
    }
    return config;
  }

  private WxMaDefaultConfigImpl wxMaDefaultConfigStorage() {
    return new WxMaDefaultConfigImpl();
  }

  private WxMaDefaultConfigImpl wxMaJedisConfigStorage() {
    RedisProperties redisProperties = wxMaProperties.getConfigStorage().getRedis();
    JedisPool jedisPool;
    if (redisProperties != null && StringUtils.isNotEmpty(redisProperties.getHost())) {
      jedisPool = getJedisPool();
    } else {
      jedisPool = applicationContext.getBean(JedisPool.class);
    }
    WxRedisOps redisOps = new JedisWxRedisOps(jedisPool);
    return new WxMaRedisBetterConfigImpl(redisOps, wxMaProperties.getConfigStorage().getKeyPrefix());
  }

  private WxMaDefaultConfigImpl wxMaRedisTemplateConfigStorage() {
    StringRedisTemplate redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
    WxRedisOps redisOps = new RedisTemplateWxRedisOps(redisTemplate);
    return new WxMaRedisBetterConfigImpl(redisOps, wxMaProperties.getConfigStorage().getKeyPrefix());
  }

  private JedisPool getJedisPool() {
    ConfigStorage storage = wxMaProperties.getConfigStorage();
    RedisProperties redis = storage.getRedis();

    JedisPoolConfig config = new JedisPoolConfig();
    if (redis.getMaxActive() != null) {
      config.setMaxTotal(redis.getMaxActive());
    }
    if (redis.getMaxIdle() != null) {
      config.setMaxIdle(redis.getMaxIdle());
    }
    if (redis.getMaxWaitMillis() != null) {
      config.setMaxWaitMillis(redis.getMaxWaitMillis());
    }
    if (redis.getMinIdle() != null) {
      config.setMinIdle(redis.getMinIdle());
    }
    config.setTestOnBorrow(true);
    config.setTestWhileIdle(true);

    return new JedisPool(config, redis.getHost(), redis.getPort(), redis.getTimeout(), redis.getPassword(),
      redis.getDatabase());
  }
}
