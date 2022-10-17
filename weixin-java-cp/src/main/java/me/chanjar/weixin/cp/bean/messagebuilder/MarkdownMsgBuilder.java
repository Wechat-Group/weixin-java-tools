package me.chanjar.weixin.cp.bean.messagebuilder;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;

/**
 * <pre>
 * markdown类型的消息builder
 * Created by Binary Wang on 2019/1/20.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class MarkdownMsgBuilder extends BaseBuilder<MarkdownMsgBuilder> {
  private String content;

  /**
   * Instantiates a new Markdown msg builder.
   */
  public MarkdownMsgBuilder() {
    this.msgType = WxConsts.KefuMsgType.MARKDOWN;
  }

  /**
   * Content markdown msg builder.
   *
   * @param content the content
   * @return the markdown msg builder
   */
  public MarkdownMsgBuilder content(String content) {
    this.content = content;
    return this;
  }

  @Override
  public WxCpMessage build() {
    WxCpMessage m = super.build();
    m.setContent(this.content);
    return m;
  }
}
