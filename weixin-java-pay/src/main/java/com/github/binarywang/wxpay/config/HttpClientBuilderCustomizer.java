package com.github.binarywang.wxpay.config;

import org.apache.http.impl.client.HttpClientBuilder;

/**
 * @author wangn <15124178@qq.com> 2023/9/8
 */
@FunctionalInterface
public interface HttpClientBuilderCustomizer {
  void customize(HttpClientBuilder var1);
}
