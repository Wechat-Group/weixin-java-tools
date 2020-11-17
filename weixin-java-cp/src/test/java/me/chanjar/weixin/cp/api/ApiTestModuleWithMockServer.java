package me.chanjar.weixin.cp.api;

import com.google.inject.Binder;

/**
 * 带mock server 的test module.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-08-30
 */
public class ApiTestModuleWithMockServer extends ApiTestModule {
  public static final int mockServerPort = 8080;

  @Override
  public void configure(Binder binder) {
    super.configure(binder);
    super.config.setBaseApiUrl("http://localhost:" + mockServerPort);
  }
}
