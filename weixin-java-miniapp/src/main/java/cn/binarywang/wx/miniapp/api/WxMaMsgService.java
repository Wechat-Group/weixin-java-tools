package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.*;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * <pre>
 * 消息发送接口
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMaMsgService {
  String KEFU_MESSAGE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
  String TEMPLATE_MSG_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send";
  String SUBSCRIBE_MSG_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send";
  String UNIFORM_MSG_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send";
  String ACTIVITY_ID_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/activityid/create";
  String UPDATABLE_MSG_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/updatablemsg/send";

  /**
   * <pre>
   * 发送客服消息
   * 详情请见: <a href="https://developers.weixin.qq.com/miniprogram/dev/api-backend/customerServiceMessage.send.html">发送客服消息</a>
   * 接口url格式：https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param message 客服消息
   * @return .
   * @throws WxErrorException .
   */
  boolean sendKefuMsg(WxMaKefuMessage message) throws WxErrorException;

  /**
   * <pre>
   * 发送订阅消息
   * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/subscribe-message/subscribeMessage.send.html
   * </pre>
   *
   * @param subscribeMessage 订阅消息
   * @throws WxErrorException .
   */
  void sendSubscribeMsg(WxMaSubscribeMessage subscribeMessage) throws WxErrorException;

  /**
   * <pre>
   * 下发小程序和公众号统一的服务消息
   * 详情请见: <a href="https://developers.weixin.qq.com/miniprogram/dev/api/open-api/uniform-message/sendUniformMessage.html">下发小程序和公众号统一的服务消息</a>
   * 接口url格式：https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param uniformMessage 消息
   * @throws WxErrorException .
   */
  void sendUniformMsg(WxMaUniformMessage uniformMessage) throws WxErrorException;

  /**
   * <pre>
   *  创建被分享动态消息的 activity_id.
   *  动态消息: https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/share/updatable-message.html
   *
   *  文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/updatable-message/updatableMessage.createActivityId.html
   *  接口地址：GET https://api.weixin.qq.com/cgi-bin/message/wxopen/activityid/create?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @return .
   * @throws WxErrorException .
   */
  JsonObject createUpdatableMessageActivityId() throws WxErrorException;

  /**
   * <pre>
   *  修改被分享的动态消息.
   *  动态消息: https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/share/updatable-message.html
   *
   *  文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/updatable-message/updatableMessage.setUpdatableMsg.html
   *  接口地址：POST https://api.weixin.qq.com/cgi-bin/message/wxopen/activityid/create?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param msg 动态消息
   * @throws WxErrorException .
   */
  void setUpdatableMsg(WxMaUpdatableMsg msg) throws WxErrorException;
}
