package com.binarywang.spring.demo.wxjava.cp.handler;

import com.binarywang.spring.starter.wxjava.base.annotation.WxHandler;
import com.binaywang.spring.starter.wxjava.cp.handler.AbstractWxCpTpMessageMatchHandler;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.bean.WxCpTpPermanentCodeInfo;
import me.chanjar.weixin.cp.bean.message.WxCpTpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;
import me.chanjar.weixin.cp.constant.WxCpTpConsts;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;

import java.util.Map;

/**
 * @author caiqy
 */
@WxHandler
public class AuthCodeHandler extends AbstractWxCpTpMessageMatchHandler {

  @Override
  public String getInfoType() {
    return WxCpTpConsts.InfoType.CREATE_AUTH;
  }

  @Override
  public String getChangeType() {
    return null;
  }

  @Override
  public String getEventType() {
    return null;
  }

  @Override
  public String getMsgType() {
    return null;
  }

  @Override
  public boolean ignoreMatch() {
    return false;
  }

  @Override
  public WxCpXmlOutMessage handle(WxCpTpXmlMessage wxMessage, Map<String, Object> context, WxCpTpService wxCpTpService, WxSessionManager sessionManager) throws WxErrorException {
    WxCpTpPermanentCodeInfo wxCpTpPermanentCodeInfo = wxCpTpService.getPermanentCodeInfo(wxMessage.getAuthCode());
    // 永久授权码,持久化,内部已经持久化了,但是真实业务中可以保存到数据库中
    return null;
  }
}
