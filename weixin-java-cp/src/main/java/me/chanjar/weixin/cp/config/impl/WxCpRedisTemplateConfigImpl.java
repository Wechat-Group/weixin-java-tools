package me.chanjar.weixin.cp.config.impl;

import lombok.NonNull;
import me.chanjar.weixin.common.redis.RedisTemplateWxRedisOps;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 基于 RedisTemplate 的实现
 *
 * @author yl
 * created on  2023/04/23
 */
public class WxCpRedisTemplateConfigImpl extends AbstractWxCpInRedisConfigImpl {
  private static final long serialVersionUID = -1660004125413310620L;

  public WxCpRedisTemplateConfigImpl(@NonNull StringRedisTemplate stringRedisTemplate) {
    this(stringRedisTemplate, null);
  }

  public WxCpRedisTemplateConfigImpl(@NonNull StringRedisTemplate stringRedisTemplate, String keyPrefix) {
    super(new RedisTemplateWxRedisOps(stringRedisTemplate), keyPrefix);
  }
}
