package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.device.WxMaDeviceSubscribeMessageRequest;
import cn.binarywang.wx.miniapp.bean.device.WxMaDeviceTicketRequest;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 小程序设备订阅消息相关 API
 * 文档：
 *
 * @author <a href="https://github.com/leejuncheng">JCLee</a>
 * @since 2021-12-16 17:13:35
 */
public interface WxMaDeviceSubscribeService {

  /**
   * <pre>
   * 获取设备票据
   * 应用场景：
   * 小程序前端界面拉起设备消息授权订阅弹框界面
   * 注意：
   * 设备ticket有效时间为5分钟
   * </pre>
   * @param deviceTicketRequest
   * @return
   * @throws WxErrorException
   */
  String getSnTicket(WxMaDeviceTicketRequest deviceTicketRequest) throws WxErrorException;

  /**
   * <pre>
   * 发送设备订阅消息
   * </pre>
   *
   * @param deviceSubscribeMessageRequest 订阅消息
   * @throws WxErrorException .
   */
  void sendDeviceSubscribeMsg(WxMaDeviceSubscribeMessageRequest deviceSubscribeMessageRequest) throws WxErrorException;

}
