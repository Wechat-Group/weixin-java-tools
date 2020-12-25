package me.chanjar.weixin.qidian.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.chanjar.weixin.qidian.bean.WxQidianHostConfig;
import me.chanjar.weixin.qidian.config.WxQidianConfigStorage;

import static me.chanjar.weixin.qidian.bean.WxQidianHostConfig.*;

/**
 * <pre>
 *  公众号接口api地址
 *  Created by BinaryWang on 2019-06-03.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMpApiUrl {

  /**
   * 得到api完整地址.
   *
   * @param config 微信公众号配置
   * @return api地址
   */
  default String getUrl(WxQidianConfigStorage config) {
    WxQidianHostConfig hostConfig = null;
    if (config != null) {
      hostConfig = config.getHostConfig();
    }
    return buildUrl(hostConfig, this.getPrefix(), this.getPath());

  }

  /**
   * the path
   *
   * @return path
   */
  String getPath();

  /**
   * the prefix
   *
   * @return prefix
   */
  String getPrefix();

  @AllArgsConstructor
  @Getter
  enum OAuth2 implements WxMpApiUrl {
    /**
     * 用code换取oauth2的access token.
     */
    OAUTH2_ACCESS_TOKEN_URL(API_DEFAULT_HOST_URL,
        "/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code"),
    /**
     * 刷新oauth2的access token.
     */
    OAUTH2_REFRESH_TOKEN_URL(API_DEFAULT_HOST_URL,
        "/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s"),
    /**
     * 用oauth2获取用户信息.
     */
    OAUTH2_USERINFO_URL(API_DEFAULT_HOST_URL, "/sns/userinfo?access_token=%s&openid=%s&lang=%s"),
    /**
     * 验证oauth2的access token是否有效.
     */
    OAUTH2_VALIDATE_TOKEN_URL(API_DEFAULT_HOST_URL, "/sns/auth?access_token=%s&openid=%s"),
    /**
     * oauth2授权的url连接.
     */
    CONNECT_OAUTH2_AUTHORIZE_URL(OPEN_DEFAULT_HOST_URL,
        "/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&connect_redirect=1#wechat_redirect");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum Other implements WxMpApiUrl {
    /**
     * 获取access_token.
     */
    GET_ACCESS_TOKEN_URL(QIDIAN_DEFAULT_HOST_URL, "/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s"),
    /**
     * 获得各种类型的ticket.
     */
    GET_TICKET_URL(API_DEFAULT_HOST_URL, "/cgi-bin/ticket/getticket?type="),
    /**
     * 长链接转短链接接口.
     */
    SHORTURL_API_URL(API_DEFAULT_HOST_URL, "/cgi-bin/shorturl"),
    /**
     * 语义查询接口.
     */
    SEMANTIC_SEMPROXY_SEARCH_URL(API_DEFAULT_HOST_URL, "/semantic/semproxy/search"),
    /**
     * 获取微信服务器IP地址.
     */
    GET_CALLBACK_IP_URL(API_DEFAULT_HOST_URL, "/cgi-bin/getcallbackip"),
    /**
     * 网络检测.
     */
    NETCHECK_URL(API_DEFAULT_HOST_URL, "/cgi-bin/callback/check"),
    /**
     * 第三方使用网站应用授权登录的url.
     */
    QRCONNECT_URL(OPEN_DEFAULT_HOST_URL,
        "/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect"),
    /**
     * 获取公众号的自动回复规则.
     */
    GET_CURRENT_AUTOREPLY_INFO_URL(API_DEFAULT_HOST_URL, "/cgi-bin/get_current_autoreply_info"),
    /**
     * 公众号调用或第三方平台帮公众号调用对公众号的所有api调用（包括第三方帮其调用）次数进行清零.
     */
    CLEAR_QUOTA_URL(API_DEFAULT_HOST_URL, "/cgi-bin/clear_quota");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum Dial implements WxMpApiUrl {
    /**
     * IVR外呼.
     */
    IVR_DIAL(QIDIAN_DEFAULT_HOST_URL, "/cgi-bin/call/dial/ivrdial"),
    /**
     * 拉取IVR列表.
     */
    GET_IVR_LIST(QIDIAN_DEFAULT_HOST_URL, "/cgi-bin/call/dial/getivrlist");

    private final String prefix;
    private final String path;

  }

  @AllArgsConstructor
  @Getter
  enum CallData implements WxMpApiUrl {
    /**
     * 总机号列表拉取.
     */
    GET_SWITCH_BOARD_LIST(QIDIAN_DEFAULT_HOST_URL, "/cgi-bin/call/callData/getswitchboardlist");

    private final String prefix;
    private final String path;

  }

}
