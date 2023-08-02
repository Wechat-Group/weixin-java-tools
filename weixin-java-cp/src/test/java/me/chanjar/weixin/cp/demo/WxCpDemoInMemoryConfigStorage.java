package me.chanjar.weixin.cp.demo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.ToString;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;

import java.io.InputStream;

/**
 * The type Wx cp demo in memory config storage.
 *
 * @author Daniel Qian
 */
@XStreamAlias("xml")
@ToString
public class WxCpDemoInMemoryConfigStorage extends WxCpDefaultConfigImpl {
  /**
   * From xml wx cp demo in memory config storage.
   *
   * @param is the is
   * @return the wx cp demo in memory config storage
   */
  public static WxCpDemoInMemoryConfigStorage fromXml(InputStream is) {
    XStream xstream = XStreamInitializer.getInstance();
    xstream.processAnnotations(WxCpDemoInMemoryConfigStorage.class);
    return (WxCpDemoInMemoryConfigStorage) xstream.fromXML(is);
  }

}
