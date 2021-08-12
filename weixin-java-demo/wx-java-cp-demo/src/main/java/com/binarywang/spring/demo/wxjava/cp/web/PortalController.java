package com.binarywang.spring.demo.wxjava.cp.web;

import com.binaywang.spring.starter.wxjava.cp.config.WxCpTpServiceContainer;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.util.xml.XmlUtil;
import me.chanjar.weixin.cp.bean.WxCpTpXmlPackage;
import me.chanjar.weixin.cp.bean.message.WxCpTpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;
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

  //---------------------------------------------------------------------
  // 业务设置url
  //---------------------------------------------------------------------
  @GetMapping(value = "/biz", produces = "text/plain;charset=utf-8")
  public String bizGet(@PathVariable(name = "suiteId") String suiteId,
                       @RequestParam(name = "msg_signature", required = false) String signature,
                       @RequestParam(name = "timestamp", required = false) String timestamp,
                       @RequestParam(name = "nonce", required = false) String nonce,
                       @RequestParam(name = "echostr", required = false) String echostr) {

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

    return "fail";
  }

  @PostMapping(value = "/biz", produces = "application/xml; charset=UTF-8")
  public String bizPost(@PathVariable(name = "suiteId") String suiteId,
                        @RequestBody String requestBody,
                        @RequestParam("msg_signature") String signature,
                        @RequestParam("timestamp") String timestamp,
                        @RequestParam("nonce") String nonce) {
    final WxCpTpService wxCpTpService = wxCpTpServiceContainer.getTpService(suiteId);
    WxCpTpXmlPackage wxCpTpXmlPackage = WxCpTpXmlPackage.fromXml(requestBody);
    if (wxCpTpService.checkSignature(signature, timestamp, nonce, wxCpTpXmlPackage.getMsgEncrypt())) {
      String xml = new WxCpTpCryptUtil(wxCpTpService.getWxCpTpConfigStorage()).decrypt(wxCpTpXmlPackage.getMsgEncrypt());
      log.info("\n 解密后xml:" + xml);
//      WxCpTpXmlMessage wxCpTpXmlMessage = XmlUtil.toObject(xml, WxCpTpXmlMessage.class);
//      WxCpXmlOutMessage wxCpXmlOutMessage = wxCpTpServiceContainer.getRouter(suiteId).route(wxCpTpXmlMessage);
//      if (wxCpXmlOutMessage != null) {
//        return wxCpTpServiceContainer.getCryptUtil(suiteId).encrypt(wxCpXmlOutMessage.toXml());
//      }
      return "success";
    } else {
      log.error("\n\nunknown callback\n\n");
      return "fail";
    }
  }

  //---------------------------------------------------------------------
  // 数据回调url
  // 订阅/取消订阅
  //---------------------------------------------------------------------
  @GetMapping(value = "/data", produces = "text/plain;charset=utf-8")
  public String dataGet(@PathVariable(name = "suiteId") String suiteId,
                        @RequestParam(name = "msg_signature", required = false) String signature,
                        @RequestParam(name = "timestamp", required = false) String timestamp,
                        @RequestParam(name = "nonce", required = false) String nonce,
                        @RequestParam(name = "echostr", required = false) String echostr) {

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

    return "fail";
  }

  @PostMapping(value = "/data", produces = "application/xml; charset=UTF-8")
  public String dataPost(@PathVariable(name = "suiteId") String suiteId,
                         @RequestBody String requestBody,
                         @RequestParam("msg_signature") String signature,
                         @RequestParam("timestamp") String timestamp,
                         @RequestParam("nonce") String nonce) {
    final WxCpTpService wxCpTpService = wxCpTpServiceContainer.getTpService(suiteId);
    WxCpTpXmlPackage wxCpTpXmlPackage = WxCpTpXmlPackage.fromXml(requestBody);
    if (wxCpTpService.checkSignature(signature, timestamp, nonce, wxCpTpXmlPackage.getMsgEncrypt())) {
      String xml = new WxCpTpCryptUtil(wxCpTpService.getWxCpTpConfigStorage()).decrypt(wxCpTpXmlPackage.getMsgEncrypt());
      log.info("\n 解密后xml:" + xml);
//      WxCpTpXmlMessage wxCpTpXmlMessage = XmlUtil.toObject(xml, WxCpTpXmlMessage.class);
//      WxCpXmlOutMessage wxCpXmlOutMessage = wxCpTpServiceContainer.getRouter(suiteId).route(wxCpTpXmlMessage);
//      if (wxCpXmlOutMessage != null) {
//        return wxCpTpServiceContainer.getCryptUtil(suiteId).encrypt(wxCpXmlOutMessage.toXml());
//      }
      return "success";
    } else {
      log.error("\n\nunknown callback\n\n");
      return "fail";
    }
  }


  //---------------------------------------------------------------------
  // 指令回调url
  // 刷新ticket
  // 取消授权/授权
  // 人员变化
  // 标签变化
  //---------------------------------------------------------------------
  @GetMapping(value = "/command", produces = "text/plain;charset=utf-8")
  public String commandGet(@PathVariable(name = "suiteId") String suiteId,
                           @RequestParam(name = "msg_signature", required = false) String signature,
                           @RequestParam(name = "timestamp", required = false) String timestamp,
                           @RequestParam(name = "nonce", required = false) String nonce,
                           @RequestParam(name = "echostr", required = false) String echostr) {

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

    return "fail";
  }

  @PostMapping(value = "/command", produces = "application/xml; charset=UTF-8")
  public String commandPost(@PathVariable(name = "suiteId") String suiteId,
                            @RequestBody String requestBody,
                            @RequestParam("msg_signature") String signature,
                            @RequestParam("timestamp") String timestamp,
                            @RequestParam("nonce") String nonce) {
    final WxCpTpService wxCpTpService = wxCpTpServiceContainer.getTpService(suiteId);
    WxCpTpXmlPackage wxCpTpXmlPackage = WxCpTpXmlPackage.fromXml(requestBody);
    if (wxCpTpService.checkSignature(signature, timestamp, nonce, wxCpTpXmlPackage.getMsgEncrypt())) {
      String xml = new WxCpTpCryptUtil(wxCpTpService.getWxCpTpConfigStorage()).decrypt(wxCpTpXmlPackage.getMsgEncrypt());
      log.info("\n 解密后xml:" + xml);
      WxCpTpXmlMessage wxCpTpXmlMessage = XmlUtil.toObject(xml, WxCpTpXmlMessage.class);
      WxCpXmlOutMessage wxCpXmlOutMessage = wxCpTpServiceContainer.getRouter(suiteId).route(wxCpTpXmlMessage);
      if (wxCpXmlOutMessage != null) {
        return wxCpTpServiceContainer.getCryptUtil(suiteId).encrypt(wxCpXmlOutMessage.toXml());
      }
      return "success";
    } else {
      log.error("\n\nunknown callback\n\n");
      return "fail";
    }
  }
}
