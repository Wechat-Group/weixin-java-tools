package cn.binarywang.wx.miniapp.builder;

import cn.binarywang.wx.miniapp.bean.WxMaKefuMessage;

import static cn.binarywang.wx.miniapp.constant.WxMaConstants.KefuMsgType;

/**
 * 小程序卡片消息builder
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class MaPageMessageBuilder extends BaseBuilder<MaPageMessageBuilder> {
  private String title;
  private String pagePath;
  private String thumbMediaId;

  public MaPageMessageBuilder() {
    this.msgType = KefuMsgType.MA_PAGE;
  }

  public MaPageMessageBuilder title(String title) {
    this.title = title;
    return this;
  }

  public MaPageMessageBuilder pagePath(String pagePath) {
    this.pagePath = pagePath;
    return this;
  }

  public MaPageMessageBuilder thumbMediaId(String thumbMediaId) {
    this.thumbMediaId = thumbMediaId;
    return this;
  }

  @Override
  public WxMaKefuMessage build() {
    WxMaKefuMessage m = super.build();
    m.setMaPage(WxMaKefuMessage.KfMaPage.builder()
      .title(this.title)
      .pagePath(this.pagePath)
      .thumbMediaId(this.thumbMediaId)
      .build()
    );
    return m;
  }
}
