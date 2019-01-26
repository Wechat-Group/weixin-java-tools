package me.chanjar.weixin.cp.demo;

import java.io.InputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.ToString;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;
import me.chanjar.weixin.cp.config.WxCpInMemoryConfigStorage;

/**
 * @author Daniel Qian
 */
@XStreamAlias("xml")
@ToString
public class WxCpDemoInMemoryConfigStorage extends WxCpInMemoryConfigStorage {
  public static WxCpDemoInMemoryConfigStorage fromXml(InputStream is) {
    XStream xstream = XStreamInitializer.getInstance();
    xstream.processAnnotations(WxCpDemoInMemoryConfigStorage.class);
    return (WxCpDemoInMemoryConfigStorage) xstream.fromXML(is);
  }

}
