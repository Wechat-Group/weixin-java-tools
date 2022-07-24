package me.chanjar.weixin.cp.util.crypto;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-06-11
 */
public class WxCpCryptUtilTest {
  @Test
  public void test() {
    String encodingAesKey = "jWmYm7qr5nMoAUwZRjGtBxmz3KA1tkAj3ykkR6q2B2C";
    final byte[] commonsCodec = Base64.decodeBase64(encodingAesKey + "=");
    final byte[] guava = java.util.Base64.getDecoder().decode(StringUtils.remove(encodingAesKey, " "));
    final byte[] guava1 = java.util.Base64.getDecoder().decode(StringUtils.remove(encodingAesKey + "=", " "));
    assertEquals(commonsCodec, guava);
    assertEquals(guava1, guava);
  }
}
