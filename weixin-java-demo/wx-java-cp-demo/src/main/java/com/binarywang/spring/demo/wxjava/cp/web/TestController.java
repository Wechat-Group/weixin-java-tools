package com.binarywang.spring.demo.wxjava.cp.web;

import com.binaywang.spring.starter.wxjava.cp.config.WxCpTpServiceContainer;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import me.chanjar.weixin.cp.bean.message.builder.WxCpTextMessage;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caiqy
 */
@RestController
@RequestMapping("/test")
public class TestController {

  private final WxCpTpServiceContainer wxCpTpServiceContainer;

  public TestController(WxCpTpServiceContainer wxCpTpServiceContainer) {
    this.wxCpTpServiceContainer = wxCpTpServiceContainer;
  }

  @GetMapping("/1")
  public String get() {
    WxCpTpService wxCpTpService = wxCpTpServiceContainer.getTpService("ww530a1c681ffac763");
    String corpId = "wwa1b76c4cd67ceb6a";
    String userId = "CaiQiYuan";
    WxCpMessage wxCpMessage = WxCpTextMessage.builderToParty("1")
      .content("hello world")
      .build();
    wxCpTpService.getWxCpTpMessageService().send(corpId, wxCpMessage);
//    wxCpTpService.getWxCpTpUserService().getUserId(corpId, userId);
    wxCpTpService.getWxCpTpExternalContactService().listExternalContacts(corpId, userId);
    return "hello";
  }
}
