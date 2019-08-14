package me.chanjar.weixin.mp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxNetCheckResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.util.WxMpConfigStorageHolder;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * <pre>
 *  Created by BinaryWang on 2019/3/29.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class BaseWxMpServiceImplTest {
  @Inject
  private WxMpService wxService;

  @Test
  public void testSwitchover() {
    assertTrue(this.wxService.switchover("another"));
    assertThat(WxMpConfigStorageHolder.get()).isEqualTo("another");
    assertFalse(this.wxService.switchover("whatever"));
  }

  @Test
  public void testSwitchoverTo() throws WxErrorException {
    assertThat(this.wxService.switchoverTo("another").getAccessToken()).isNotEmpty();
    assertThat(WxMpConfigStorageHolder.get()).isEqualTo("another");
  }

  @Test
  public void testNetCheck() throws WxErrorException {
    WxNetCheckResult result = this.wxService.netCheck(WxConsts.NetCheckArgs.ACTIONALL, WxConsts.NetCheckArgs.OPERATORDEFAULT);
    Assert.assertNotNull(result);

  }

  @Test
  public void testNectCheckResult() {
    String json = "{\n" +
      "    \"dns\": [\n" +
      "        {\n" +
      "            \"ip\": \"111.161.64.40\", \n" +
      "            \"real_operator\": \"UNICOM\"\n" +
      "        }, \n" +
      "        {\n" +
      "            \"ip\": \"111.161.64.48\", \n" +
      "            \"real_operator\": \"UNICOM\"\n" +
      "        }\n" +
      "    ], \n" +
      "    \"ping\": [\n" +
      "        {\n" +
      "            \"ip\": \"111.161.64.40\", \n" +
      "            \"from_operator\": \"UNICOM\"," +
      "            \"package_loss\": \"0%\", \n" +
      "            \"time\": \"23.079ms\"\n" +
      "        }, \n" +
      "        {\n" +
      "            \"ip\": \"111.161.64.48\", \n" +
      "            \"from_operator\": \"UNICOM\", \n" +
      "            \"package_loss\": \"0%\", \n" +
      "            \"time\": \"21.434ms\"\n" +
      "        }\n" +
      "    ]\n" +
      "}";
    WxNetCheckResult result = WxNetCheckResult.fromJson(json);
    Assert.assertNotNull(result);
    Assert.assertNotNull(result.getDnsInfos());

    WxNetCheckResult.WxNetCheckDnsInfo dnsInfo = new WxNetCheckResult.WxNetCheckDnsInfo();
    dnsInfo.setIp("111.161.64.40");
    dnsInfo.setRealOperator("UNICOM");
    Assert.assertEquals(result.getDnsInfos().get(0), dnsInfo);

    WxNetCheckResult.WxNetCheckPingInfo pingInfo = new WxNetCheckResult.WxNetCheckPingInfo();
    pingInfo.setTime("21.434ms");
    pingInfo.setFromOperator("UNICOM");
    pingInfo.setIp("111.161.64.48");
    pingInfo.setPackageLoss("0%");
    Assert.assertEquals(result.getPingInfos().get(1), pingInfo);

  }
}
