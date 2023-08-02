package me.chanjar.weixin.cp.bean.messagebuilder;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;

import java.util.Map;

/**
 * <pre>
 * miniprogram_notice 类型的消息 builder
 * Created by Binary Wang on 2019/6/16.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class MiniProgramNoticeMsgBuilder extends BaseBuilder<MiniProgramNoticeMsgBuilder> {
  private String title;
  private String description;
  private String appId;
  private String page;
  private Boolean emphasisFirstItem;
  private Map<String, String> contentItems;

  /**
   * Instantiates a new Mini program notice msg builder.
   */
  public MiniProgramNoticeMsgBuilder() {
    this.msgType = WxConsts.KefuMsgType.MINIPROGRAM_NOTICE;
  }

  /**
   * App id mini program notice msg builder.
   *
   * @param appId the app id
   * @return the mini program notice msg builder
   */
  public MiniProgramNoticeMsgBuilder appId(String appId) {
    this.appId = appId;
    return this;
  }

  /**
   * Page mini program notice msg builder.
   *
   * @param page the page
   * @return the mini program notice msg builder
   */
  public MiniProgramNoticeMsgBuilder page(String page) {
    this.page = page;
    return this;
  }

  /**
   * Title mini program notice msg builder.
   *
   * @param title the title
   * @return the mini program notice msg builder
   */
  public MiniProgramNoticeMsgBuilder title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Description mini program notice msg builder.
   *
   * @param description the description
   * @return the mini program notice msg builder
   */
  public MiniProgramNoticeMsgBuilder description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Content items mini program notice msg builder.
   *
   * @param contentItems the content items
   * @return the mini program notice msg builder
   */
  public MiniProgramNoticeMsgBuilder contentItems(Map<String, String> contentItems) {
    this.contentItems = contentItems;
    return this;
  }

  /**
   * Emphasis first item mini program notice msg builder.
   *
   * @param emphasisFirstItem the emphasis first item
   * @return the mini program notice msg builder
   */
  public MiniProgramNoticeMsgBuilder emphasisFirstItem(Boolean emphasisFirstItem) {
    this.emphasisFirstItem = emphasisFirstItem;
    return this;
  }

  @Override
  public WxCpMessage build() {
    WxCpMessage m = super.build();
    m.setContentItems(this.contentItems);
    m.setAppId(this.appId);
    m.setDescription(this.description);
    m.setTitle(this.title);
    m.setEmphasisFirstItem(this.emphasisFirstItem);
    m.setPage(this.page);
    return m;
  }
}
