package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaMsgService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaKefuMessage;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import cn.binarywang.wx.miniapp.bean.WxMaUniformMessage;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@AllArgsConstructor
public class WxMaMsgServiceImpl implements WxMaMsgService {
  private static final JsonParser JSON_PARSER = new JsonParser();
  private WxMaService wxMaService;

  @Override
  public boolean sendKefuMsg(WxMaKefuMessage message) throws WxErrorException {
    String responseContent = this.wxMaService.post(KEFU_MESSAGE_SEND_URL, message.toJson());
    return responseContent != null;
  }

  /**
   * <pre>
   * 小程序模板消息接口将于2020年1月10日下线，开发者可使用订阅消息功能
   * </pre>
   */
  @Override
  public void sendTemplateMsg(WxMaTemplateMessage templateMessage) throws WxErrorException {
    String responseContent = this.wxMaService.post(TEMPLATE_MSG_SEND_URL, templateMessage.toJson());
    JsonObject jsonObject = JSON_PARSER.parse(responseContent).getAsJsonObject();
    if (jsonObject.get(WxMaConstants.ERRCODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
  }

  @Override
  public void sendSubscribeMsg(WxMaSubscribeMessage subscribeMessage) throws WxErrorException {
    String responseContent = this.wxMaService.post(SUBSCRIBE_MSG_SEND_URL, subscribeMessage.toJson());
    JsonObject jsonObject = JSON_PARSER.parse(responseContent).getAsJsonObject();
    if (jsonObject.get(WxMaConstants.ERRCODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
  }

  @Override
  public void sendUniformMsg(WxMaUniformMessage uniformMessage) throws WxErrorException {
    String responseContent = this.wxMaService.post(UNIFORM_MSG_SEND_URL, uniformMessage.toJson());
    JsonObject jsonObject = JSON_PARSER.parse(responseContent).getAsJsonObject();
    if (jsonObject.get(WxMaConstants.ERRCODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
  }

}
