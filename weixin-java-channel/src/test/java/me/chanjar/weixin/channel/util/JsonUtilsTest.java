package me.chanjar.weixin.channel.util;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import me.chanjar.weixin.channel.bean.base.AttrInfo;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public class JsonUtilsTest {

  @Test
  public void testEncode() {
    AttrInfo info = new AttrInfo("这是Key", "这是Value");
    String json = JsonUtils.encode(info);
    System.out.println(json);
    assertNotNull(json);
  }

  @Test
  public void testDecode() {
    String json = "{\"attr_key\": \"这是Key\",\"attr_value\": \"这是Value\"}";
    AttrInfo info = JsonUtils.decode(json, AttrInfo.class);
    assertNotNull(info);
    assertEquals(info.getKey(), "这是Key");
  }
}
