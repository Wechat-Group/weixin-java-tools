package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.Map;

/**
 * 用户信息相关操作接口.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMaUserService {
  String SET_USER_STORAGE = "https://api.weixin.qq.com/wxa/set_user_storage?appid=%s&signature=%s&openid=%s&sig_method=%s";

  /**
   * 获取登录后的session信息.
   *
   * @param jsCode 登录时获取的 code
   * @return .
   * @throws WxErrorException .
   */
  WxMaJscode2SessionResult getSessionInfo(String jsCode) throws WxErrorException;

  /**
   * 解密用户敏感数据.
   *
   * @param sessionKey    会话密钥
   * @param encryptedData 消息密文
   * @param ivStr         加密算法的初始向量
   */
  WxMaUserInfo getUserInfo(String sessionKey, String encryptedData, String ivStr);

  /**
   * 上报用户数据后台接口.
   * <p>小游戏可以通过本接口上报key-value数据到用户的CloudStorage。</p>
   * 文档参考https://developers.weixin.qq.com/minigame/dev/document/open-api/data/setUserStorage.html
   *
   * @param kvMap      要上报的数据
   * @param sessionKey 通过wx.login 获得的登录态
   * @param openid     .
   * @throws WxErrorException .
   */
  void setUserStorage(Map<String, String> kvMap, String sessionKey, String openid) throws WxErrorException;

  /**
   * 解密用户手机号信息.
   *
   * @param sessionKey    会话密钥
   * @param encryptedData 消息密文
   * @param ivStr         加密算法的初始向量
   * @return .
   */
  WxMaPhoneNumberInfo getPhoneNoInfo(String sessionKey, String encryptedData, String ivStr);

  /**
   * 验证用户信息完整性.
   *
   * @param sessionKey 会话密钥
   * @param rawData    微信用户基本信息
   * @param signature  数据签名
   * @return .
   */
  boolean checkUserInfo(String sessionKey, String rawData, String signature);
}
