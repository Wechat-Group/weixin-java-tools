package com.binarywang.spring.demo.wxjava.cp.web;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.common.util.crypto.WxCryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author caiqy
 */
@Slf4j
@RestController
@RequestMapping("/wx/cp/tp/contact_address/{suiteId}")
public class ContactAddressController {


  //---------------------------------------------------------------------
  // 业务设置url
  //---------------------------------------------------------------------
  @GetMapping(value = "/", produces = "text/plain;charset=utf-8")
  public String get(@PathVariable(name = "suiteId") String suiteId,
                    @RequestParam(name = "msg_signature", required = false) String signature,
                    @RequestParam(name = "timestamp", required = false) String timestamp,
                    @RequestParam(name = "nonce", required = false) String nonce,
                    @RequestParam(name = "echostr", required = false) String echostr) {
    if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
      throw new IllegalArgumentException("请求参数非法，请核实!");
    }

    boolean res = SHA1.gen("zCTbQvwvtc2tea3h7St8Gw", timestamp, nonce, echostr)
      .equals(signature);
    if (res) {
      WxCryptUtil wxCryptUtil = new WxCryptUtil("zCTbQvwvtc2tea3h7St8Gw", "PqIZM5RRiph2HipSXLJiv4aC6kwlE7BEj70tkL6Ubut", "wwa1b76c4cd67ceb6a");
      return wxCryptUtil.decrypt(echostr);
    }

    return "fail";
  }

  @PostMapping(value = "/", produces = "application/xml; charset=UTF-8")
  public String post(@PathVariable(name = "suiteId") String suiteId,
                     @RequestBody String requestBody,
                     @RequestParam("msg_signature") String signature,
                     @RequestParam("timestamp") String timestamp,
                     @RequestParam("nonce") String nonce) {
    return "success";
  }

}
