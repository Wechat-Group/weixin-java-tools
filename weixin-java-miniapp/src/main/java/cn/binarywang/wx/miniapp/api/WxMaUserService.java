package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * 用户信息相关操作接口
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMaUserService {
  String JSCODE_TO_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session";

  /**
   * 获取登录后的session信息
   *
   * @param jsCode 登录时获取的 code
   */
  WxMaJscode2SessionResult getSessionInfo(String jsCode) throws WxErrorException;

  /**
   * 解密用户敏感数据
   *
   * @param sessionKey    会话密钥
   * @param encryptedData 消息密文
   * @param ivStr         加密算法的初始向量
   */
  WxMaUserInfo getUserInfo(String sessionKey, String encryptedData, String ivStr);

  /**
   * 验证用户信息完整性
   *
   * @param sessionKey 会话密钥
   * @param rawData    微信用户基本信息
   * @param signature  数据签名
   */
  boolean checkUserInfo(String sessionKey, String rawData, String signature);
}
