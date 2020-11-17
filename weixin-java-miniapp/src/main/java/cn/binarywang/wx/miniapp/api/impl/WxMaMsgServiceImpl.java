package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaMsgService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.*;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@AllArgsConstructor
public class WxMaMsgServiceImpl implements WxMaMsgService {
  private final WxMaService wxMaService;

  @Override
  public boolean sendKefuMsg(WxMaKefuMessage message) throws WxErrorException {
    String responseContent = this.wxMaService.post(KEFU_MESSAGE_SEND_URL, message.toJson());
    return responseContent != null;
  }

  @Override
  public void sendSubscribeMsg(WxMaSubscribeMessage subscribeMessage) throws WxErrorException {
    String responseContent = this.wxMaService.post(SUBSCRIBE_MSG_SEND_URL, subscribeMessage.toJson());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxMaConstants.ERRCODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
  }

  @Override
  public void sendUniformMsg(WxMaUniformMessage uniformMessage) throws WxErrorException {
    String responseContent = this.wxMaService.post(UNIFORM_MSG_SEND_URL, uniformMessage.toJson());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxMaConstants.ERRCODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
  }

  @Override
  public JsonObject createUpdatableMessageActivityId() throws WxErrorException {
    final String responseContent = this.wxMaService.get(ACTIVITY_ID_CREATE_URL, null);
    return GsonParser.parse(responseContent);
  }

  @Override
  public void setUpdatableMsg(WxMaUpdatableMsg msg) throws WxErrorException {
    this.wxMaService.post(UPDATABLE_MSG_SEND_URL, WxMaGsonBuilder.create().toJson(msg));
  }

}
