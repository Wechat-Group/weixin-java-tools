package me.chanjar.weixin.mp.bean.template;

import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 参考 http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277&token=&lang=zh_CN 发送模板消息接口部分
 */
public class WxMpTemplateMessage implements Serializable {
  private static final long serialVersionUID = 5063374783759519418L;

  /**
   * 接收者openid
   */
  private String toUser;

  /**
   * 模板ID
   */
  private String templateId;

  /**
   * <pre>
   * 跳小程序所需数据，不需跳小程序可不用传该数据
   * url和miniprogram都是非必填字段，若都不传则模板无跳转；若都传，会优先跳转至小程序。
   * 开发者可根据实际需要选择其中一种跳转方式即可。当用户的微信客户端版本不支持跳小程序时，将会跳转至url。
   * </pre>
   */
  private String url;
  /**
   * 模板跳转链接
   *
   * @see #url
   */
  private MiniProgram miniProgram;

  /**
   * 模板数据
   */
  private List<WxMpTemplateData> data = new ArrayList<>();

  public WxMpTemplateMessage() {
  }

  public static WxMpTemplateMessageBuilder builder() {
    return new WxMpTemplateMessageBuilder();
  }

  public String getToUser() {
    return this.toUser;
  }

  public void setToUser(String toUser) {
    this.toUser = toUser;
  }

  public String getTemplateId() {
    return this.templateId;
  }

  public void setTemplateId(String templateId) {
    this.templateId = templateId;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public List<WxMpTemplateData> getData() {
    return this.data;
  }

  public void setData(List<WxMpTemplateData> data) {
    this.data = data;
  }

  public void addWxMpTemplateData(WxMpTemplateData datum) {
    this.data.add(datum);
  }

  public MiniProgram getMiniProgram() {
    return this.miniProgram;
  }

  public void setMiniProgram(MiniProgram miniProgram) {
    this.miniProgram = miniProgram;
  }

  public String toJson() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }

  public static class MiniProgram {
    private String appid;
    private String pagePath;

    public MiniProgram() {
    }

    public MiniProgram(String appid, String pagePath) {
      this.appid = appid;
      this.pagePath = pagePath;
    }

    public String getAppid() {
      return this.appid;
    }

    public void setAppid(String appid) {
      this.appid = appid;
    }

    public String getPagePath() {
      return this.pagePath;
    }

    public void setPagePath(String pagePath) {
      this.pagePath = pagePath;
    }
  }

  public static class WxMpTemplateMessageBuilder {
    private String toUser;
    private String templateId;
    private String url;
    private List<WxMpTemplateData> data = new ArrayList<>();
    private MiniProgram miniProgram;

    public WxMpTemplateMessageBuilder toUser(String toUser) {
      this.toUser = toUser;
      return this;
    }

    public WxMpTemplateMessageBuilder templateId(String templateId) {
      this.templateId = templateId;
      return this;
    }

    public WxMpTemplateMessageBuilder url(String url) {
      this.url = url;
      return this;
    }

    public WxMpTemplateMessageBuilder data(List<WxMpTemplateData> data) {
      this.data = data;
      return this;
    }

    public WxMpTemplateMessageBuilder from(WxMpTemplateMessage origin) {
      this.toUser(origin.toUser);
      this.templateId(origin.templateId);
      this.url(origin.url);
      this.data(origin.data);
      return this;
    }

    public WxMpTemplateMessageBuilder miniProgram(MiniProgram miniProgram) {
      this.miniProgram = miniProgram;
      return this;
    }

    public WxMpTemplateMessage build() {
      WxMpTemplateMessage m = new WxMpTemplateMessage();
      m.toUser = this.toUser;
      m.templateId = this.templateId;
      m.url = this.url;
      m.data = this.data;
      m.miniProgram = this.miniProgram;
      return m;
    }
  }

}
