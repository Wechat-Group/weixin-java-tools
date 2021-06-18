package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaJsapiService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.RandomUtils;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.common.util.json.GsonParser;

import java.util.concurrent.locks.Lock;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Jsapi.GET_JSAPI_TICKET_URL;

/**
 * <pre>
 *  Created by BinaryWang on 2018/8/5.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RequiredArgsConstructor
public class WxMaJsapiServiceImpl implements WxMaJsapiService {
  private final WxMaService service;

  @Override
  public String getCardApiTicket() throws WxErrorException {
    return getCardApiTicket(false);
  }

  @Override
  public String getCardApiTicket(boolean forceRefresh) throws WxErrorException {
    if (forceRefresh) {
      this.service.getWxMaConfig().expireCardApiTicket();
    }

    if (this.service.getWxMaConfig().isCardApiTicketExpired()) {
      Lock lock = this.service.getWxMaConfig().getCardApiTicketLock();
      lock.lock();
      try {
        if (this.service.getWxMaConfig().isCardApiTicketExpired()) {
          String responseContent = this.service.get(GET_JSAPI_TICKET_URL + "?type=wx_card", null);
          JsonObject tmpJsonObject = GsonParser.parse(responseContent);
          String jsapiTicket = tmpJsonObject.get("ticket").getAsString();
          int expiresInSeconds = tmpJsonObject.get("expires_in").getAsInt();
          this.service.getWxMaConfig().updateCardApiTicket(jsapiTicket, expiresInSeconds);
        }
      } finally {
        lock.unlock();
      }
    }
    return this.service.getWxMaConfig().getCardApiTicket();
  }

  @Override
  public String getJsapiTicket() throws WxErrorException {
    return getJsapiTicket(false);
  }

  @Override
  public String getJsapiTicket(boolean forceRefresh) throws WxErrorException {
    Lock lock = this.service.getWxMaConfig().getJsapiTicketLock();
    lock.lock();
    try {
      if (forceRefresh) {
        this.service.getWxMaConfig().expireJsapiTicket();
      }

      if (this.service.getWxMaConfig().isJsapiTicketExpired()) {
        String responseContent = this.service.get(GET_JSAPI_TICKET_URL + "?type=jsapi", null);
        JsonObject tmpJsonObject = GsonParser.parse(responseContent);
        String jsapiTicket = tmpJsonObject.get("ticket").getAsString();
        int expiresInSeconds = tmpJsonObject.get("expires_in").getAsInt();
        this.service.getWxMaConfig().updateJsapiTicket(jsapiTicket, expiresInSeconds);
      }
    } finally {
      lock.unlock();
    }
    return this.service.getWxMaConfig().getJsapiTicket();
  }

  @Override
  public WxJsapiSignature createJsapiSignature(String url) throws WxErrorException {
    long timestamp = System.currentTimeMillis() / 1000;
    String randomStr = RandomUtils.getRandomStr();
    String jsapiTicket = getJsapiTicket(false);
    String signature = SHA1.genWithAmple("jsapi_ticket=" + jsapiTicket,
      "noncestr=" + randomStr, "timestamp=" + timestamp, "url=" + url);
    return WxJsapiSignature
      .builder()
      .appId(this.service.getWxMaConfig().getAppid())
      .timestamp(timestamp)
      .nonceStr(randomStr)
      .url(url)
      .signature(signature)
      .build();
  }
}
