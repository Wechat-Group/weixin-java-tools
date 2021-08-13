package me.chanjar.weixin.mp.demo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import me.chanjar.weixin.common.util.xml.XmlUtil;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;

import java.io.InputStream;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Daniel Qian
 */
@XStreamAlias("xml")
class WxMpDemoInMemoryConfigStorage extends WxMpDefaultConfigImpl {
  private static final long serialVersionUID = -3706236839197109704L;

  public static WxMpDemoInMemoryConfigStorage fromXml(InputStream is) {
    WxMpDemoInMemoryConfigStorage wxMpDemoInMemoryConfigStorage = XmlUtil.toObject(is, WxMpDemoInMemoryConfigStorage.class);
    wxMpDemoInMemoryConfigStorage.accessTokenLock = new ReentrantLock();
    wxMpDemoInMemoryConfigStorage.cardApiTicketLock = new ReentrantLock();
    wxMpDemoInMemoryConfigStorage.jsapiTicketLock = new ReentrantLock();
    return wxMpDemoInMemoryConfigStorage;
  }

}
