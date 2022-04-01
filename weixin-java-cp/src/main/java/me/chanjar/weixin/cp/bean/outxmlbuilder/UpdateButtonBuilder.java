package me.chanjar.weixin.cp.bean.outxmlbuilder;

import me.chanjar.weixin.cp.bean.message.WxCpXmlOutUpdateBtnMessage;

/**
 * 模板卡片更新点击用户的按钮点击文案消息builder
 *
 * @author nickname263
 */
public class UpdateButtonBuilder extends BaseBuilder<UpdateButtonBuilder, WxCpXmlOutUpdateBtnMessage> {


  private String replaceName;

  public UpdateButtonBuilder replaceName(String replaceName) {
    this.replaceName = replaceName;
    return this;
  }

  @Override
  public WxCpXmlOutUpdateBtnMessage build() {
    WxCpXmlOutUpdateBtnMessage m = new WxCpXmlOutUpdateBtnMessage();
    setCommon(m);
    m.setReplaceName(this.replaceName);
    return m;
  }

}
