package us.wili.jason.weixin.open.api.impl;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.WxMpShakeInfoResult;
import me.chanjar.weixin.mp.bean.WxMpShakeQuery;
import us.wili.jason.weixin.open.api.WxOpenService;
import us.wili.jason.weixin.open.api.WxOpenShakeService;

/**
 * Created by rememberber on 2017/6/5.
 * @author rememberber
 */
public class WxOpenShakeServiceImpl implements WxOpenShakeService {

  private WxOpenService wxOpenService;

  public WxOpenShakeServiceImpl(WxOpenService wxOpenService) {
    this.wxOpenService = wxOpenService;
  }

  /**
   * <pre>
   * 获取设备及用户信息<br/>
   * 获取设备信息，包括UUID、major、minor，以及距离、openID等信息。
   * 详情请见: https://mp.weixin.qq.com/wiki?action=doc&id=mp1443447963
   * http请求方式: POST（请使用https协议）
   * 接口地址：https://api.weixin.qq.com/shakearound/user/getshakeinfo?access_token=ACCESS_TOKE
   * </pre>
   *
   * @param wxMpShakeQuery  查询参数
   */
  @Override
  public WxMpShakeInfoResult getShakeInfo(String appid, WxMpShakeQuery wxMpShakeQuery) throws WxErrorException {
    String url = "https://api.weixin.qq.com/shakearound/user/getshakeinfo";
    String postData = wxMpShakeQuery.toJsonString();
    String responseContent = this.wxOpenService.post(appid, url, postData);
    return WxMpShakeInfoResult.fromJson(responseContent);
  }
}
