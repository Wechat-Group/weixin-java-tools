package me.chanjar.weixin.mp.bean.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * @author Mklaus
 * @date 2018-01-22 下午12:18
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class WxMpSubscribeMessage {

  /**
   * 接收者openid.
   */
  private String toUser;

  /**
   * 模板ID.
   */
  private String templateId;

  /**
   * 模板跳转链接.
   * <pre>
   * url和miniprogram都是非必填字段，若都不传则模板无跳转；若都传，会优先跳转至小程序。
   * 开发者可根据实际需要选择其中一种跳转方式即可。当用户的微信客户端版本不支持跳小程序时，将会跳转至url。
   * </pre>
   */
  private String url;

  /**
   * 跳小程序所需数据，不需跳小程序可不用传该数据.
   *
   * @see #url
   */
  private WxMpTemplateMessage.MiniProgram miniProgram;

  /**
   * 订阅场景值
   */
  private String scene;

  /**
   * 消息标题 (15字以内)
   */
  private String title;

  /**
   * 消息内容文本 （200字以内）
   */
  private String contentValue;

  /**
   * 消息内容文本颜色
   */
  private String contentColor;


  public String toJson() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }


}
