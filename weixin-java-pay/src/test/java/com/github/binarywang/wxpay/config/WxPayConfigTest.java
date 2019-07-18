package com.github.binarywang.wxpay.config;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * <pre>
 *  Created by BinaryWang on 2017/6/18.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxPayConfigTest {
  private WxPayConfig payConfig = new WxPayConfig();

  /**
   * Test init ssl context.
   *
   * @throws Exception the exception
   */
  @Test
  public void testInitSSLContext() throws Exception {
    payConfig.setMchId("123");
    payConfig.setKeyPath("classpath:/abc.p12");
    payConfig.initSSLContext();
  }

}
