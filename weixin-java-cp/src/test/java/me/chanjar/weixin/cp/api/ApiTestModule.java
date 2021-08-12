package me.chanjar.weixin.cp.api;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.xml.XmlUtil;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class ApiTestModule implements Module {
  private static final String TEST_CONFIG_XML = "test-config.xml";
  protected WxXmlCpInMemoryConfigStorage config;

  @Override
  public void configure(Binder binder) {
    try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(TEST_CONFIG_XML)) {
      if (inputStream == null) {
        throw new WxRuntimeException("测试配置文件【" + TEST_CONFIG_XML + "】未找到，请参照test-config-sample.xml文件生成");
      }

      config = XmlUtil.toObject(inputStream, WxXmlCpInMemoryConfigStorage.class);
      WxCpService wxService = new WxCpServiceImpl();
      wxService.setWxCpConfigStorage(config);

      binder.bind(WxCpService.class).toInstance(wxService);
      binder.bind(WxXmlCpInMemoryConfigStorage.class).toInstance(config);
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
  }

  @Data
  @EqualsAndHashCode(callSuper = true)
  @XStreamAlias("xml")
  public static class WxXmlCpInMemoryConfigStorage extends WxCpDefaultConfigImpl {
    private static final long serialVersionUID = -4521839921547374822L;

    protected String userId;
    protected String departmentId;
    protected String tagId;
    protected String externalUserId;
  }

}
