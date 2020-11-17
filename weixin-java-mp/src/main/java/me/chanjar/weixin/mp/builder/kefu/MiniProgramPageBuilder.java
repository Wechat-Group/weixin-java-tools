package me.chanjar.weixin.mp.builder.kefu;

import me.chanjar.weixin.common.api.WxConsts.KefuMsgType;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;

/**
 * 小程序卡片 builder
 * <pre>
 * 用法:
 * WxMpKefuMessage m = WxMpKefuMessage.MINIPROGRAMPAGE().title("xxxx").thumbMediaId("xxxxx").appId("xxxx").pagePath("****").toUser(...).build();
 * </pre>
 *
 * @author boris.bao
 */
public final class MiniProgramPageBuilder extends BaseBuilder<MiniProgramPageBuilder> {

  private String title;
  private String appId;
  private String pagePath;
  private String thumbMediaId;

  public MiniProgramPageBuilder() {
    this.msgType = KefuMsgType.MINIPROGRAMPAGE;
  }


  public MiniProgramPageBuilder title(String title) {
    this.title = title;
    return this;
  }

  public MiniProgramPageBuilder appId(String appId) {
    this.appId = appId;
    return this;
  }


  public MiniProgramPageBuilder pagePath(String pagePath) {
    this.pagePath = pagePath;
    return this;
  }


  public MiniProgramPageBuilder thumbMediaId(String thumbMediaId) {
    this.thumbMediaId = thumbMediaId;
    return this;
  }


  @Override
  public WxMpKefuMessage build() {
    WxMpKefuMessage m = super.build();
    m.setTitle(this.title);
    m.setMiniProgramAppId(this.appId);
    m.setMiniProgramPagePath(this.pagePath);
    m.setThumbMediaId(this.thumbMediaId);
    return m;
  }


}
