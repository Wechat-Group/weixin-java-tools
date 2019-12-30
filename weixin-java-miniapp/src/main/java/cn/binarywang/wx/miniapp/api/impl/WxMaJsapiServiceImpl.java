package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaJsapiService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.RandomUtils;
import me.chanjar.weixin.common.util.crypto.SHA1;

import java.util.concurrent.locks.Lock;

/**
 * <pre>
 *  Created by BinaryWang on 2018/8/5.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@AllArgsConstructor
public class WxMaJsapiServiceImpl implements WxMaJsapiService {
  private static final JsonParser JSON_PARSER = new JsonParser();

  private WxMaService wxMaService;

  @Override
  public String getCardApiTicket() throws WxErrorException {
    return getCardApiTicket(false);
  }

  @Override
  public String getCardApiTicket(boolean forceRefresh) throws WxErrorException {
    Lock lock = this.wxMaService.getWxMaConfig().getCardApiTicketLock();
    try {
      lock.lock();
      if (forceRefresh) {
        this.wxMaService.getWxMaConfig().expireCardApiTicket();
      }

      if (this.wxMaService.getWxMaConfig().isCardApiTicketExpired()) {
        String responseContent = this.wxMaService.get(GET_JSAPI_TICKET_URL + "?type=wx_card", null);
        JsonElement tmpJsonElement = JSON_PARSER.parse(responseContent);
        JsonObject tmpJsonObject = tmpJsonElement.getAsJsonObject();
        String jsapiTicket = tmpJsonObject.get("ticket").getAsString();
        int expiresInSeconds = tmpJsonObject.get("expires_in").getAsInt();
        this.wxMaService.getWxMaConfig().updateCardApiTicket(jsapiTicket, expiresInSeconds);
      }
    } finally {
      lock.unlock();
    }
    return this.wxMaService.getWxMaConfig().getJsapiTicket();
  }

  @Override
  public String getJsapiTicket() throws WxErrorException {
    return getJsapiTicket(false);
  }

  @Override
  public String getJsapiTicket(boolean forceRefresh) throws WxErrorException {
    Lock lock = this.wxMaService.getWxMaConfig().getJsapiTicketLock();
    try {
      lock.lock();
      if (forceRefresh) {
        this.wxMaService.getWxMaConfig().expireJsapiTicket();
      }

      if (this.wxMaService.getWxMaConfig().isJsapiTicketExpired()) {
        String responseContent = this.wxMaService.get(GET_JSAPI_TICKET_URL + "?type=jsapi", null);
        JsonElement tmpJsonElement = JSON_PARSER.parse(responseContent);
        JsonObject tmpJsonObject = tmpJsonElement.getAsJsonObject();
        String jsapiTicket = tmpJsonObject.get("ticket").getAsString();
        int expiresInSeconds = tmpJsonObject.get("expires_in").getAsInt();
        this.wxMaService.getWxMaConfig().updateJsapiTicket(jsapiTicket, expiresInSeconds);
      }
    } finally {
      lock.unlock();
    }
    return this.wxMaService.getWxMaConfig().getJsapiTicket();
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
      .appId(this.wxMaService.getWxMaConfig().getAppid())
      .timestamp(timestamp)
      .nonceStr(randomStr)
      .url(url)
      .signature(signature)
      .build();
  }
}
