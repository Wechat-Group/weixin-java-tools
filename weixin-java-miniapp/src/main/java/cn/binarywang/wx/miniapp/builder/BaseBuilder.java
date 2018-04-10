package cn.binarywang.wx.miniapp.builder;

import cn.binarywang.wx.miniapp.bean.WxMaKefuMessage;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class BaseBuilder<T> {
  protected String msgType;
  protected String toUser;

  @SuppressWarnings("unchecked")
  public T toUser(String toUser) {
    this.toUser = toUser;
    return (T) this;
  }

  /**
   * 构造器方法.
   */
  public WxMaKefuMessage build() {
    WxMaKefuMessage m = new WxMaKefuMessage();
    m.setMsgType(this.msgType);
    m.setToUser(this.toUser);
    return m;
  }
}
