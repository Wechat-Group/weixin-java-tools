package me.chanjar.weixin.cp.api;

import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.oa.selfagent.WxCpOpenApprovalData;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.demo.WxCpDemoInMemoryConfigStorage;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;
import org.testng.annotations.Test;

import java.io.InputStream;

/**
 * 企业微信自建应用接口测试类.
 * https://developer.work.weixin.qq.com/document/path/90269
 *
 * @author <a href="https://gitee.com/Wang_Wong/">Wang_Wong</a>
 * @date 2022-04-06
 */
@Slf4j
public class WxCpOaAgentTest {

  // extends WxCpBaseResp
  private static WxCpConfigStorage wxCpConfigStorage;
  private static WxCpService cpService;

  @Test
  public void test() throws WxErrorException {

    InputStream inputStream = ClassLoader.getSystemResourceAsStream("test-config.xml");
    WxCpDemoInMemoryConfigStorage config = WxCpDemoInMemoryConfigStorage.fromXml(inputStream);

    wxCpConfigStorage = config;
    cpService = new WxCpServiceImpl();
    cpService.setWxCpConfigStorage(config);

    /**
     * Test
     */
    String test = "{\"errcode\":0,\"errmsg\":\"ok\",\"data\":{\"ThirdNo\":\"thirdNoxxx\",\"OpenTemplateId\":\"1234567111\",\"OpenSpName\":\"付款\",\"OpenSpstatus\":1,\"ApplyTime\":1527837645,\"ApplyUsername\":\"jackiejjwu\",\"ApplyUserParty\":\"产品部\",\"ApplyUserImage\":\"http://www.qq.com/xxx.png\",\"ApplyUserId\":\"WuJunJie\",\"ApprovalNodes\":{\"ApprovalNode\":[{\"NodeStatus\":1,\"NodeAttr\":1,\"NodeType\":1,\"Items\":{\"Item\":[{\"ItemName\":\"chauvetxiao\",\"ItemParty\":\"产品部\",\"ItemImage\":\"http://www.qq.com/xxx.png\",\"ItemUserId\":\"XiaoWen\",\"ItemStatus\":1,\"ItemSpeech\":\"\",\"ItemOpTime\":0}]}}]},\"NotifyNodes\":{\"NotifyNode\":[{\"ItemName\":\"jinhuiguo\",\"ItemParty\":\"行政部\",\"ItemImage\":\"http://www.qq.com/xxx.png\",\"ItemUserId\":\"GuoJinHui\"}]},\"ApproverStep\":0}}";

    final WxCpOpenApprovalData data = WxCpGsonBuilder.create()
      .fromJson(GsonParser.parse(test).get("data"),
        new TypeToken<WxCpOpenApprovalData>() {
        }.getType()
      );

    log.info(data.toJson());


    WxCpOpenApprovalData openApprovalData = cpService.getOaAgentService().getOpenApprovalData("943225459735269376");
    log.info(openApprovalData.toJson());

  }

}
