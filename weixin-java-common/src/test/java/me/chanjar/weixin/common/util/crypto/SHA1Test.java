package me.chanjar.weixin.common.util.crypto;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * <pre>
 *  Created by BinaryWang on 2017/6/10.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class SHA1Test {
  @Test
  public void testGen() throws Exception {
    final String result = SHA1.gen("123", "345");
    assertNotNull(result);
    assertEquals(result,"9f537aeb751ec72605f57f94a2f6dc3e3958e1dd");
  }

  @Test(expectedExceptions = {IllegalArgumentException.class})
  public void testGen_illegalArguments() {
    final String result = SHA1.gen(null, "", "345");
    assertNotNull(result);
  }

  @Test
  public void testGenWithAmple() throws Exception {
    final String result = SHA1.genWithAmple("123", "345");
    assertNotNull(result);
    assertEquals(result,"20b896ccbd5a72dde5dbe0878ff985e4069771c6");
  }

}
