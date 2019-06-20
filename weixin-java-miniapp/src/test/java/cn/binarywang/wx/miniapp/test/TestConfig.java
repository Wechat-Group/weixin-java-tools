package cn.binarywang.wx.miniapp.test;

import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.InputStream;
import java.util.concurrent.locks.Lock;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@XStreamAlias("xml")
public class TestConfig extends WxMaInMemoryConfig {

  private String openid;
  private String kfAccount;
  private String templateId;

  public static TestConfig fromXml(InputStream is) {
    XStream xstream = XStreamInitializer.getInstance();
    xstream.processAnnotations(TestConfig.class);
    return (TestConfig) xstream.fromXML(is);
  }

  public String getOpenid() {
    return this.openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  public String getKfAccount() {
    return this.kfAccount;
  }

  public void setKfAccount(String kfAccount) {
    this.kfAccount = kfAccount;
  }

  public String getTemplateId() {
    return this.templateId;
  }

  public void setTemplateId(String templateId) {
    this.templateId = templateId;
  }

  @Override
  public void setAccessTokenLock(Lock lock) {
    super.accessTokenLock = lock;
  }

}
