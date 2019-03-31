package cn.binarywang.wx.miniapp.api;

import java.util.List;

import cn.binarywang.wx.miniapp.bean.WxMaRunStepInfo;

/**
 * 微信运动相关操作接口.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMaRunService {

  /**
   * 解密分享敏感数据.
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api/open-api/werun/wx.getWeRunData.html
   *
   * @param sessionKey    会话密钥
   * @param encryptedData 消息密文
   * @param ivStr         加密算法的初始向量
   */
  List<WxMaRunStepInfo> getRunStepInfo(String sessionKey, String encryptedData, String ivStr);

}
