package me.chanjar.weixin.mp.builder.kefu;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 菜单消息builder
 * <pre>
 * 用法:
 * WxMpKefuMessage m = WxMpKefuMessage.MSGMENU().addList(lists).headContent(headContent).tailContent(tailContent).toUser(...).build();
 * </pre>
 *
 * @author billytomato
 */
public final class WxMsgMenuBuilder extends BaseBuilder<WxMsgMenuBuilder> {
  private List<WxMpKefuMessage.WxMsgMenu> list = new ArrayList<>();
  private String headContent;
  private String tailContent;


  public WxMsgMenuBuilder() {
    this.msgType = WxConsts.KefuMsgType.MSGMENU;
  }

  public WxMsgMenuBuilder addList(WxMpKefuMessage.WxMsgMenu... list) {
    Collections.addAll(this.list, list);
    return this;
  }

  public WxMsgMenuBuilder list(List<WxMpKefuMessage.WxMsgMenu> list) {
    this.list = list;
    return this;
  }

  public WxMsgMenuBuilder headContent(String headContent) {
    this.headContent = headContent;
    return this;
  }

  public WxMsgMenuBuilder tailContent(String tailContent) {
    this.tailContent = tailContent;
    return this;
  }

  @Override
  public WxMpKefuMessage build() {
    WxMpKefuMessage m = super.build();
    m.setHeadContent(this.headContent);
    m.setTailContent(this.tailContent);
    m.setList(this.list);
    return m;
  }
}
