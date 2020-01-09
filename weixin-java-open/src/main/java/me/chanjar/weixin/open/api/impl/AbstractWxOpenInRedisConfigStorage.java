package me.chanjar.weixin.open.api.impl;


import org.apache.commons.lang3.StringUtils;

/**
 * @author yangyidian
 * @date 2020/01/09
 **/
public abstract class AbstractWxOpenInRedisConfigStorage extends WxOpenInMemoryConfigStorage {
  protected final static String COMPONENT_VERIFY_TICKET_KEY = "wechat_component_verify_ticket:";
  protected final static String COMPONENT_ACCESS_TOKEN_KEY = "wechat_component_access_token:";

  protected final static String AUTHORIZER_REFRESH_TOKEN_KEY = "wechat_authorizer_refresh_token:";
  protected final static String AUTHORIZER_ACCESS_TOKEN_KEY = "wechat_authorizer_access_token:";
  protected final static String JSAPI_TICKET_KEY = "wechat_jsapi_ticket:";
  protected final static String CARD_API_TICKET_KEY = "wechat_card_api_ticket:";

  /**
   * redis 存储的 key 的前缀，可为空
   */
  protected String keyPrefix;
  protected String componentVerifyTicketKey;
  protected String componentAccessTokenKey;
  protected String authorizerRefreshTokenKey;
  protected String authorizerAccessTokenKey;
  protected String jsapiTicketKey;
  protected String cardApiTicket;

  @Override
  public void setComponentAppId(String componentAppId) {
    super.setComponentAppId(componentAppId);
    String prefix = StringUtils.isBlank(keyPrefix) ? "" :
      (StringUtils.endsWith(keyPrefix, ":") ? keyPrefix : (keyPrefix + ":"));
    componentVerifyTicketKey = prefix + COMPONENT_VERIFY_TICKET_KEY.concat(componentAppId);
    componentAccessTokenKey = prefix + COMPONENT_ACCESS_TOKEN_KEY.concat(componentAppId);
    authorizerRefreshTokenKey = prefix + AUTHORIZER_REFRESH_TOKEN_KEY.concat(componentAppId);
    authorizerAccessTokenKey = prefix + AUTHORIZER_ACCESS_TOKEN_KEY.concat(componentAppId);
    this.jsapiTicketKey = JSAPI_TICKET_KEY.concat(componentAppId);
    this.cardApiTicket = CARD_API_TICKET_KEY.concat(componentAppId);
  }

  protected String getKey(String prefix, String appId) {
    return prefix.endsWith(":") ? prefix.concat(appId) : prefix.concat(":").concat(appId);
  }

}
