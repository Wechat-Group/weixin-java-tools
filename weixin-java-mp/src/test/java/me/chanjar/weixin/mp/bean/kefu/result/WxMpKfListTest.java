package me.chanjar.weixin.mp.bean.kefu.result;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.testng.*;
import org.testng.annotations.*;

@Test
public class WxMpKfListTest {

  public void testFromJson() {
    String json = " {\r\n" +
      "    \"kf_list\" : [\r\n" +
      "       {\r\n" +
      "          \"kf_account\" : \"test1@test\",\r\n" +
      "          \"kf_headimgurl\" : \"http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw/0\",\r\n" +
      "          \"kf_id\" : \"1001\",\r\n" +
      "          \"kf_nick\" : \"ntest1\"\r\n" +
      "       },\r\n" +
      "       {\r\n" +
      "          \"kf_account\" : \"test2@test\",\r\n" +
      "          \"kf_headimgurl\" : \"http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw/0\",\r\n" +
      "          \"kf_id\" : \"1002\",\r\n" +
      "          \"kf_nick\" : \"ntest2\"\r\n" +
      "       },\r\n" +
      "       {\r\n" +
      "          \"kf_account\" : \"test3@test\",\r\n" +
      "          \"kf_headimgurl\" : \"http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw/0\",\r\n" +
      "          \"kf_id\" : \"1003\",\r\n" +
      "          \"kf_nick\" : \"ntest3\"\r\n" +
      "       }\r\n" +
      "    ]\r\n" +
      " }";

    WxMpKfList wxMpKfList = WxMpKfList.fromJson(json);
    Assert.assertNotNull(wxMpKfList);
    System.err.println(ToStringBuilder.reflectionToString(wxMpKfList));
  }

}
