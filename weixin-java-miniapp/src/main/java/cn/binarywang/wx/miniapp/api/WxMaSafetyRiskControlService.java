package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.safety.request.WxMaUserSafetyRiskRankRequest;
import cn.binarywang.wx.miniapp.bean.safety.response.WxMaUserSafetyRiskRankResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * <pre>
 * 小程序安全风控相关接口
 * </pre>
 *
 * @author <a href="https://github.com/azouever">azouever</a>
 */
public interface WxMaSafetyRiskControlService {

  /**
   * <pre>
   * 根据提交的用户信息数据获取用户的安全等级，无需用户授权
   * 文档：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/safety-control-capability/riskControl.getUserRiskRank.html
   * </pre>
   *
   * @param wxMaUserSafetyRiskRankRequest 获取用户安全等级请求
   * @throws WxErrorException 通用异常
   */
  WxMaUserSafetyRiskRankResponse getUserRiskRank(WxMaUserSafetyRiskRankRequest wxMaUserSafetyRiskRankRequest) throws WxErrorException;

}
