package me.chanjar.weixin.common.bean;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-06-06
 */
public class WxNetCheckResultTest {

  @Test
  public void testFromJson() {
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
