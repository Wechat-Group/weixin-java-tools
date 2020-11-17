package me.chanjar.weixin.cp.bean;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * .
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-08-18
 */
public class WxCpTpXmlPackageTest {

  @Test
  public void testFromXml() {
    WxCpTpXmlPackage result = WxCpTpXmlPackage.fromXml("<xml> \n" +
      "   <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
      "   <AgentID><![CDATA[toAgentID]]></AgentID>\n" +
      "   <Encrypt><![CDATA[msg_encrypt]]></Encrypt>\n" +
      "</xml>\n");
    assertThat(result).isNotNull();
    assertThat(result.getToUserName()).isEqualTo("toUser");
    assertThat(result.getAgentId()).isEqualTo("toAgentID");
    assertThat(result.getMsgEncrypt()).isEqualTo("msg_encrypt");
  }
}
