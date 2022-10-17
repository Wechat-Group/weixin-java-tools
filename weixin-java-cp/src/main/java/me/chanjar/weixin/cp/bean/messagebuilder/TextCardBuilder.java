package me.chanjar.weixin.cp.bean.messagebuilder;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;

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

  /**
   * Instantiates a new Text card builder.
   */
  public TextCardBuilder() {
    this.msgType = WxConsts.KefuMsgType.TEXTCARD;
  }

  /**
   * Title text card builder.
   *
   * @param title the title
   * @return the text card builder
   */
  public TextCardBuilder title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Description text card builder.
   *
   * @param description the description
   * @return the text card builder
   */
  public TextCardBuilder description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Url text card builder.
   *
   * @param url the url
   * @return the text card builder
   */
  public TextCardBuilder url(String url) {
    this.url = url;
    return this;
  }

  /**
   * Btn txt text card builder.
   *
   * @param btnTxt the btn txt
   * @return the text card builder
   */
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
