package cn.binarywang.wx.miniapp.bean.live;

import lombok.Data;

import java.io.Serializable;

/**
 * 直播间小助手用户信息
 */
@Data
public class WxMaLiveAssistantInfo implements Serializable {
  private static final long serialVersionUID = -5603581848069320808L;
  /**
   * 修改时间
   */
  private Long timestamp;
  /**
   * 头像
   **/
  private String headimg;
  /**
   * 用户微信号
   **/
  private String username;
  /**
   * 用户昵称
   **/
  private String nickname;
  /**
   * 微信号
   **/
  private String alias;
  /**
   * openid
   **/
  private String openid;

}
