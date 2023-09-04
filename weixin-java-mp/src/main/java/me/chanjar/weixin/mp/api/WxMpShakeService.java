package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.WxMpShakeInfoResult;
import me.chanjar.weixin.mp.bean.WxMpShakeQuery;
import me.chanjar.weixin.mp.bean.shake.*;

/**
 * 摇一摇周边的相关接口.
 *
 * @author rememberber
 */
public interface WxMpShakeService {
  /**
   * <pre>
   * 获取设备及用户信息<br/>
   * 获取设备信息，包括UUID、major、minor，以及距离、openID等信息。
   * 详情请见: https://mp.weixin.qq.com/wiki?action=doc&id=mp1443447963
   * http请求方式: POST（请使用https协议）
   * 接口地址：https://api.weixin.qq.com/shakearound/user/getshakeinfo?access_token=ACCESS_TOKE
   * </pre>
   *
   * @param wxMpShakeQuery 查询参数
   * @return the shake info
   * @throws WxErrorException the wx error exception
   */
  WxMpShakeInfoResult getShakeInfo(WxMpShakeQuery wxMpShakeQuery) throws WxErrorException;

  /**
   * <pre>
   * 页面管理<br/>
   * 详情请见: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1459246752
   * </pre>
   *
   * @param shakeAroundPageAddQuery the shake around page add query
   * @return . wx mp shake around page add result
   * @throws WxErrorException the wx error exception
   */
  WxMpShakeAroundPageAddResult pageAdd(WxMpShakeAroundPageAddQuery shakeAroundPageAddQuery) throws WxErrorException;

  /**
   * <pre>
   * 配置设备与页面的关联关系<br/>
   * 详情请见: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1459301931
   * </pre>
   *
   * @param shakeAroundDeviceBindPageQuery the shake around device bind page query
   * @return . wx error
   * @throws WxErrorException the wx error exception
   */
  WxError deviceBindPageQuery(WxMpShakeAroundDeviceBindPageQuery shakeAroundDeviceBindPageQuery) throws WxErrorException;

  /**
   * <pre>
   * 查询设备与页面的关联关系<br/>
   * 详情请见: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1443447914
   * </pre>
   *
   * @param shakeAroundRelationSearchQuery the shake around relation search query
   * @return . wx mp shake around relation search result
   * @throws WxErrorException the wx error exception
   */
  WxMpShakeAroundRelationSearchResult relationSearch(WxMpShakeAroundRelationSearchQuery shakeAroundRelationSearchQuery) throws WxErrorException;
}
