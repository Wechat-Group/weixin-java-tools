package com.binarywang.spring.demo.wxjava.cp.web;

import com.binaywang.spring.starter.wxjava.cp.config.WxCpTpServiceContainer;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import me.chanjar.weixin.cp.util.crypto.WxCpTpCryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author caiqy
 */
@Slf4j
@RestController
@RequestMapping("/wx/cp/tp/portal/{suiteId}")
public class PortalController {

  @Autowired
  private WxCpTpServiceContainer wxCpTpServiceContainer;

  @GetMapping(value = "/authorize",produces = "text/plain;charset=utf-8")
  public String authorizeGet(@PathVariable(name = "suiteId") String suiteId,
                        @RequestParam(name = "msg_signature", required = false) String signature,
                        @RequestParam(name = "timestamp", required = false) String timestamp,
                        @RequestParam(name = "nonce", required = false) String nonce,
                        @RequestParam(name = "echostr", required = false) String echostr) {
    log.info("\n接收到来自微信服务器的认证消息：signature = [{}], timestamp = [{}], nonce = [{}], echostr = [{}]",
      signature, timestamp, nonce, echostr);

    if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
      throw new IllegalArgumentException("请求参数非法，请核实!");
    }

    final WxCpTpService wxCpTpService = wxCpTpServiceContainer.getTpService(suiteId);
    if (wxCpTpService == null) {
      throw new IllegalArgumentException(String.format("未找到对应suiteId=[%s]的配置，请核实！", suiteId));
    }

    if (wxCpTpService.checkSignature(signature, timestamp, nonce, echostr)) {
      return new WxCpTpCryptUtil(wxCpTpService.getWxCpTpConfigStorage()).decrypt(echostr);
    }

    return "非法请求";
  }

  @PostMapping(value = "/authorize",produces = "application/xml; charset=UTF-8")
  public String authorizePost(@PathVariable(name = "suiteId") String suiteId,
                     @RequestBody String requestBody,
                     @RequestParam("msg_signature") String signature,
                     @RequestParam("timestamp") String timestamp,
                     @RequestParam("nonce") String nonce) {
    log.info("\n接收微信请求：[signature=[{}], timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
      signature, timestamp, nonce, requestBody);

    final WxCpTpService wxCpTpService = wxCpTpServiceContainer.getTpService(suiteId);

    if (wxCpTpService.checkSignature(signature, timestamp, nonce, requestBody)) {
      String xml = new WxCpTpCryptUtil(wxCpTpService.getWxCpTpConfigStorage()).decrypt(requestBody);
      System.out.println(xml);
    }

    return "success";
  }
  @GetMapping(value = "/msg",produces = "text/plain;charset=utf-8")
  public String msgGet(@PathVariable(name = "suiteId") String suiteId,
                        @RequestParam(name = "msg_signature", required = false) String signature,
                        @RequestParam(name = "timestamp", required = false) String timestamp,
                        @RequestParam(name = "nonce", required = false) String nonce,
                        @RequestParam(name = "echostr", required = false) String echostr) {
    log.info("\n接收到来自微信服务器的认证消息：signature = [{}], timestamp = [{}], nonce = [{}], echostr = [{}]",
      signature, timestamp, nonce, echostr);

    if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
      throw new IllegalArgumentException("请求参数非法，请核实!");
    }

    final WxCpTpService wxCpTpService = wxCpTpServiceContainer.getTpService(suiteId);
    if (wxCpTpService == null) {
      throw new IllegalArgumentException(String.format("未找到对应suiteId=[%s]的配置，请核实！", suiteId));
    }

    if (wxCpTpService.checkSignature(signature, timestamp, nonce, echostr)) {
      return new WxCpTpCryptUtil(wxCpTpService.getWxCpTpConfigStorage()).decrypt(echostr);
    }

    return "非法请求";
  }

  @PostMapping(value = "/msg",produces = "application/xml; charset=UTF-8")
  public String msgPost(@PathVariable(name = "suiteId") String suiteId,
                     @RequestBody String requestBody,
                     @RequestParam("msg_signature") String signature,
                     @RequestParam("timestamp") String timestamp,
                     @RequestParam("nonce") String nonce) {
    log.info("\n接收微信请求：[signature=[{}], timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
      signature, timestamp, nonce, requestBody);

    final WxCpTpService wxCpTpService = wxCpTpServiceContainer.getTpService(suiteId);

    if (wxCpTpService.checkSignature(signature, timestamp, nonce, requestBody)) {
      String xml = new WxCpTpCryptUtil(wxCpTpService.getWxCpTpConfigStorage()).decrypt(requestBody);
      System.out.println(xml);
    }

    return "success";
  }


}
