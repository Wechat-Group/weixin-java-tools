package us.wili.jason.weixin.open.api.test;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class TestConfigStorage {
  private String componentAppid;
  private String componentAppsecret;
  private String componentToken;
  private String componentAesKey;
  private String componentVerifyTicket;
  private String authorizerAppid;
  private String authorizerRefreshToken;
  private String templateId;
  private String shortTemplateId;
  private String openid;

  public String getComponentAppid() {
    return componentAppid;
  }

  public void setComponentAppid(String componentAppid) {
    this.componentAppid = componentAppid;
  }

  public String getComponentAppsecret() {
    return componentAppsecret;
  }

  public void setComponentAppsecret(String componentAppsecret) {
    this.componentAppsecret = componentAppsecret;
  }

  public String getComponentToken() {
    return componentToken;
  }

  public void setComponentToken(String componentToken) {
    this.componentToken = componentToken;
  }

  public String getComponentAesKey() {
    return componentAesKey;
  }

  public void setComponentAesKey(String componentAesKey) {
    this.componentAesKey = componentAesKey;
  }

  public String getAuthorizerAppid() {
    return authorizerAppid;
  }

  public void setAuthorizerAppid(String authorizerAppid) {
    this.authorizerAppid = authorizerAppid;
  }

  public String getAuthorizerRefreshToken() {
    return authorizerRefreshToken;
  }

  public void setAuthorizerRefreshToken(String authorizerRefreshToken) {
    this.authorizerRefreshToken = authorizerRefreshToken;
  }

  public String getComponentVerifyTicket() {
    return componentVerifyTicket;
  }

  public void setComponentVerifyTicket(String componentVerifyTicket) {
    this.componentVerifyTicket = componentVerifyTicket;
  }

  public String getTemplateId() {
    return templateId;
  }

  public void setTemplateId(String templateId) {
    this.templateId = templateId;
  }

  public String getShortTemplateId() {
    return shortTemplateId;
  }

  public void setShortTemplateId(String shortTemplateId) {
    this.shortTemplateId = shortTemplateId;
  }

  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }
}
