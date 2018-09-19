package me.chanjar.weixin.cp.bean.messagebuilder;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.bean.WxCpMessage;

/**
 * <pre>
 * 文本卡片消息Builder
 * 用法: WxCustomMessage m = WxCustomMessage.TEXTCARD().title(...)....toUser(...).build();
 * Created by Binary Wang on 2017-7-2.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class TextCardBuilder extends BaseBuilder<TextCardBuilder> {
  private String title;
  private String description;
  private String url;
  private String btnTxt;

  public TextCardBuilder() {
    this.msgType = WxConsts.KefuMsgType.TEXTCARD;
  }

  public TextCardBuilder title(String title) {
    this.title = title;
    return this;
  }

  public TextCardBuilder description(String description) {
    this.description = description;
    return this;
  }

  public TextCardBuilder url(String url) {
    this.url = url;
    return this;
  }

  public TextCardBuilder btnTxt(String btnTxt) {
    this.btnTxt = btnTxt;
    return this;
  }

  @Override
  public WxCpMessage build() {
    WxCpMessage m = super.build();
    m.setTitle(this.title);
    m.setDescription(this.description);
    m.setUrl(this.url);
    m.setBtnTxt(this.btnTxt);
    return m;
  }
}
