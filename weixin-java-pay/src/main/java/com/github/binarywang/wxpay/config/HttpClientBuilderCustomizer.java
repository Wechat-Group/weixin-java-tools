package com.github.binarywang.wxpay.config;

import org.apache.http.impl.client.HttpClientBuilder;

/**
 * @author <a href="https://github.com/ifcute">dagewang</a>
 */
@FunctionalInterface
public interface HttpClientBuilderCustomizer {
  void customize(HttpClientBuilder var1);
}
