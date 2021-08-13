package me.chanjar.weixin.cp.api.impl;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;

/**
 * <pre>
 *  默认接口实现类，使用apache httpclient实现，配合第三方应用service使用
 * Created by zhenjun cai.
 * </pre>
 *
 * @author zhenjun cai
 * @deprecated 错误的类, 测试通过后会被删除
 */
@Deprecated
@RequiredArgsConstructor
public class WxCpServiceOnTpImpl extends WxCpServiceApacheHttpClientImpl {
  private final WxCpTpService wxCpTpService;

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    if (!this.configStorage.isAccessTokenExpired() && !forceRefresh) {
      return this.configStorage.getAccessToken();
    }
    //access token通过第三方应用service获取
    //corpSecret对应企业永久授权码
    // 写了个啥?
    WxAccessToken accessToken = wxCpTpService.getCorpToken(this.configStorage.getCorpId(), this.configStorage.getCorpSecret());

    this.configStorage.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
    return this.configStorage.getAccessToken();
  }

}
