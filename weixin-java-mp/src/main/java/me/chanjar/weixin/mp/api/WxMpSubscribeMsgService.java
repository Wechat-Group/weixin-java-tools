package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.subscribe.WxMpSubscribeMessage;

/**
 * <pre>
 * 一次性订阅消息接口
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1500374289_66bvB
 * </pre>
 *
 * @author Mklaus
 * @date 2018-01-22 上午11:07
 */
public interface WxMpSubscribeMsgService {

  /**
   * <pre>
   * 构造用户订阅一条模板消息授权的url连接
   * 详情请见: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1500374289_66bvB
   * </pre>
   *
   * @param redirectURI 用户授权完成后的重定向链接，无需urlencode, 方法内会进行encode
   * @param scene 重定向后会带上scene参数，开发者可以填0-10000的整形值，用来标识订阅场景值
   * @param reserved 用于保持请求和回调的状态，授权请后原样带回给第三方 (最多128字节，要求做urlencode)
   * @return url
   */
  String subscribeMsgAuthorizationUrl(String redirectURI, int scene, String reserved);

  /**
   * <pre>
   * 发送一次性订阅消息
   * 详情请见: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1500374289_66bvB
   * </pre>
   *
   * @return 消息Id
   */
  boolean sendSubscribeMessage(WxMpSubscribeMessage message) throws WxErrorException;

}
