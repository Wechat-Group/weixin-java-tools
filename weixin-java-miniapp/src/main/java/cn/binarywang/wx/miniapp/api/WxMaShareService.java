package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.WxMaShareInfo;

/**
 * 分享信息相关操作接口.
 *
 * @author zhfish
 */
public interface WxMaShareService {

  /**
   * 解密分享敏感数据.
   *
   * @param sessionKey    会话密钥
   * @param encryptedData 消息密文
   * @param ivStr         加密算法的初始向量
   */
  WxMaShareInfo getShareInfo(String sessionKey, String encryptedData, String ivStr);

}
