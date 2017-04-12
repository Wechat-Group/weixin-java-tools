package me.chanjar.weixin.mp.api;


/**
 * 基于缓存等其它介质的，生产环境中的实现
 * Package me.chanjar.weixin.mp.api
 * Project weixin-java-parent
 * Company  PACTERA
 * Author bin.wang
 * Created on 2017/3/8 18:00
 * version 1.0.0
 */
public abstract class WxMpInOtherConfigStorage extends WxMpInMemoryConfigStorage {

  @Override
  public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
    this.updateAccessToken(accessToken, expiresInSeconds, this);
    super.updateAccessToken(accessToken, expiresInSeconds);
  }

  @Override
  public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
    this.updateJsapiTicket(jsapiTicket, expiresInSeconds, this);
    super.updateJsapiTicket(jsapiTicket, expiresInSeconds);
  }

  @Override
  public synchronized void updateCardApiTicket(String cardApiTicket, int expiresInSeconds) {
    this.updateCardApiTicket(cardApiTicket, expiresInSeconds, this);
    super.updateCardApiTicket(cardApiTicket, expiresInSeconds);
  }

  abstract void updateAccessToken(String accessToken, int expiresInSeconds, WxMpInOtherConfigStorage storage);

  abstract void updateJsapiTicket(String jsapiTicket, int expiresInSeconds, WxMpInOtherConfigStorage storage);

  abstract void updateCardApiTicket(String cardApiTicket, int expiresInSeconds, WxMpInOtherConfigStorage storage);
}
