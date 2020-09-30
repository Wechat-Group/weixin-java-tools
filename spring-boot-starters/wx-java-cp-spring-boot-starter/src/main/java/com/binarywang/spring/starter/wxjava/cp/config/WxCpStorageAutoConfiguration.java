package com.binarywang.spring.starter.wxjava.cp.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.binarywang.spring.starter.wxjava.cp.enums.StorageType;
import com.binarywang.spring.starter.wxjava.cp.properties.RedisProperties;
import com.binarywang.spring.starter.wxjava.cp.properties.WxCpProperties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.redis.RedisTemplateWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import me.chanjar.weixin.cp.config.impl.WxCpRedissonConfigImpl;

/**
 * 企业微信存储策略自动配置.
 *
 * @author xdtand
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class WxCpStorageAutoConfiguration {

	private final ApplicationContext applicationContext;

	private final WxCpProperties wxCpProperties;

	@Bean
	@ConditionalOnMissingBean(WxCpConfigStorage.class)
	public WxCpConfigStorage WxCpConfigStorage() {
		StorageType type = wxCpProperties.getConfigStorage().getType();
		WxCpConfigStorage config;
		if (type == StorageType.Jedis) {
			config = wxCpInJedisConfigStorage();
		} else if (type == StorageType.RedisTemplate) {
			config = wxCpInRedisTemplateConfigStorage();
		} else {
			config = wxCpInMemoryConfigStorage();
		}
		// wx host config
		if (StringUtils.isNotEmpty(wxCpProperties.getHost())) {
			log.info("==>rewrite host config：{}", wxCpProperties.getHost());
			config.setBaseApiUrl(wxCpProperties.getHost());
		}
		return config;
	}

	private WxCpConfigStorage wxCpInMemoryConfigStorage() {
		WxCpDefaultConfigImpl config = new WxCpDefaultConfigImpl();
		setWxCpInfo(config);
		return config;
	}

	private WxCpConfigStorage wxCpInJedisConfigStorage() {
		RedisProperties config = wxCpProperties.getConfigStorage().getRedis();
		RedissonClient redisson = getRedission(config);
		WxCpRedissonConfigImpl wxCpRedisConfig = new WxCpRedissonConfigImpl(redisson,
				wxCpProperties.getConfigStorage().getKeyPrefix());
		setWxCpInfo(wxCpRedisConfig);
		return wxCpRedisConfig;
	}
	
  private RedissonClient getRedission(RedisProperties config) {
    Config rconfig = new Config();

    if (StringUtils.isEmpty(config.getClusterAddress())) {
      rconfig.useClusterServers().addNodeAddress(config.getClusterAddress().split(","));
    } else if (StringUtils.isEmpty(config.getSentinelAddress())) {
      rconfig.useSentinelServers().addSentinelAddress(config.getSentinelAddress().split(","));
    } else if (StringUtils.isEmpty(config.getMasterAddress())) {
      rconfig.useMasterSlaveServers().setMasterAddress(config.getMasterAddress())
          .addSlaveAddress(config.getSlaveAddress().split(","));
    } else {
      rconfig.useSingleServer().setAddress(config.getHost() + ":" + config.getPort());
    }
    return Redisson.create(rconfig);
  }

	private WxCpConfigStorage wxCpInRedisTemplateConfigStorage() {
		StringRedisTemplate redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
		WxRedisOps redisOps = new RedisTemplateWxRedisOps(redisTemplate);
		WxCpRedissonConfigImpl wxCpRedisConfig = new WxCpRedissonConfigImpl(redisOps,
				wxCpProperties.getConfigStorage().getKeyPrefix());
		setWxCpInfo(wxCpRedisConfig);
		return wxCpRedisConfig;
	}

	private void setWxCpInfo(WxCpDefaultConfigImpl config) {
		WxCpProperties properties = wxCpProperties;
		WxCpProperties.ConfigStorage configStorageProperties = properties.getConfigStorage();
		config.setCorpId(properties.getCorpId());
		config.setCorpSecret(properties.getCorpSecret());
		config.setToken(properties.getToken());
		config.setAesKey(properties.getAesKey());

		config.setHttpProxyHost(configStorageProperties.getHttpProxyHost());
		config.setHttpProxyUsername(configStorageProperties.getHttpProxyUsername());
		config.setHttpProxyPassword(configStorageProperties.getHttpProxyPassword());
		if (configStorageProperties.getHttpProxyPort() != null) {
			config.setHttpProxyPort(configStorageProperties.getHttpProxyPort());
		}
	}

}
