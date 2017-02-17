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

  private String toUser;
  private String templateId;
  private String url;
  private List<WxMpTemplateData> data = new ArrayList<>();

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

  public String toJson() {
    return WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }

  public static WxMpTemplateMessageBuilder builder() {
    return new WxMpTemplateMessageBuilder();
  }

  public static class WxMpTemplateMessageBuilder {
    private String toUser;
    private String templateId;
    private String url;
    private List<WxMpTemplateData> data = new ArrayList<>();

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

    public WxMpTemplateMessage build() {
      WxMpTemplateMessage m = new WxMpTemplateMessage();
      m.toUser = this.toUser;
      m.templateId = this.templateId;
      m.url = this.url;
      m.data = this.data;
      return m;
    }
  }

}
