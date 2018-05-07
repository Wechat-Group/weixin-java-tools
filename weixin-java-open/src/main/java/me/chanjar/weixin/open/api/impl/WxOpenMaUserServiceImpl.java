package me.chanjar.weixin.open.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaUserService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.binarywang.wx.miniapp.util.crypt.WxMaCryptUtils;
import com.google.common.base.Joiner;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.open.api.WxOpenComponentService;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Charming
 */
class WxOpenMaUserServiceImpl implements WxMaUserService {
  private static final String COMPONENT_JSCODE_TO_SESSION_URL = "https://api.weixin.qq.com/sns/component/jscode2session";
  private WxOpenComponentService wxOpenComponentService;
  private WxMaService wxMaService;

  public WxOpenMaUserServiceImpl(WxOpenComponentService wxOpenComponentService, WxMaService wxMaService) {
    this.wxOpenComponentService = wxOpenComponentService;
    this.wxMaService = wxMaService;
  }

  /**
   * 第三方平台开发者的服务器使用登录凭证 code 以及
   * 第三方平台的 component_access_token
   * 获取 session_key 和 openid。
   * 其中 session_key 是对用户数据进行加密签名的密钥。
   * 为了自身应用安全，session_key 不应该在网络上传输。
   * 文档：https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1492585163_FtTNA&token=&lang=zh_CN
   *
   * @param jsCode 登录时获取的 code
   * @return session_key 和 openid
   * @throws WxErrorException 发生错误时
   */
  @Override
  public WxMaJscode2SessionResult getSessionInfo(String jsCode)  throws WxErrorException {
    Map<String, String> params = new HashMap<>(5);
    params.put("appid", wxMaService.getWxMaConfig().getAppid());
    params.put("js_code", jsCode);
    params.put("grant_type", "authorization_code");
    params.put("component_appid", wxOpenComponentService.getWxOpenConfigStorage().getComponentAppId());
    params.put("component_access_token", wxOpenComponentService.getComponentAccessToken(false));

    String result = this.wxMaService.get(COMPONENT_JSCODE_TO_SESSION_URL, Joiner.on("&").withKeyValueSeparator("=").join(params));
    return WxMaJscode2SessionResult.fromJson(result);
  }

  @Override
  public WxMaUserInfo getUserInfo(String sessionKey, String encryptedData, String ivStr) {
    return WxMaUserInfo.fromJson(WxMaCryptUtils.decrypt(sessionKey, encryptedData, ivStr));
  }

  @Override
  public WxMaPhoneNumberInfo getPhoneNoInfo(String sessionKey, String encryptedData, String ivStr) {
    return WxMaPhoneNumberInfo.fromJson(WxMaCryptUtils.decrypt(sessionKey, encryptedData, ivStr));
  }

  @Override
  public boolean checkUserInfo(String sessionKey, String rawData, String signature) {
    final String generatedSignature = DigestUtils.sha1Hex(rawData + sessionKey);
    return generatedSignature.equals(signature);
  }
}
