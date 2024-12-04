package com.github.binarywang.wxpay.config;

import com.github.binarywang.wxpay.exception.WxPayException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.jcajce.provider.util.KeyUtil;
import org.testng.annotations.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

/**
 * <pre>
 *  Created by BinaryWang on 2017/6/18.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxPayConfigTest {
  private final WxPayConfig payConfig = new WxPayConfig();

  @Test
  public void testInitSSLContext_classpath() throws Exception {
    payConfig.setMchId("123");
    payConfig.setKeyPath("classpath:/dlt.p12");
    payConfig.initSSLContext();
  }

  @Test
  public void testInitSSLContext_http() throws Exception {
    payConfig.setMchId("123");
    payConfig.setKeyPath("https://www.baidu.com");
    payConfig.initSSLContext();
  }

  @Test
  public void testInitSSLContext() throws Exception {
    this.testInitSSLContext_classpath();
    this.testInitSSLContext_http();
  }

  @Test
  @SuppressWarnings("ResultOfMethodCallIgnored")
  public void testHashCode() {
    payConfig.hashCode();
  }

  @Test
  public void testInitSSLContext_base64() throws Exception {
    payConfig.setMchId("123");
    payConfig.setKeyString("MIIKmgIBAzCCCmQGCS...");
    payConfig.initSSLContext();
  }


  @Test
  public void testInitApiV3HttpClient() throws Exception {
    Security.addProvider(new BouncyCastleProvider());
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA","BC");
    keyPairGenerator.initialize(2048,new SecureRandom());
    KeyPair keyPair = keyPairGenerator.genKeyPair();
    byte[] encoded = keyPair.getPrivate().getEncoded();
    // 模拟用户配置
    String privateKeyString = Base64.getEncoder().encodeToString(encoded);
    payConfig.setPrivateKeyString(privateKeyString);
    payConfig.setApiV3Key("Test");
    payConfig.initApiV3HttpClient();
  }

}
