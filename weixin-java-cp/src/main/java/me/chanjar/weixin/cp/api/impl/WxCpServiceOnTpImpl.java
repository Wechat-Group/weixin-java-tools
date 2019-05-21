package me.chanjar.weixin.cp.api.impl;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpTpService;

/**
 * <pre>
 *  默认接口实现类，使用apache httpclient实现，配合第三方应用service使用
 * Created by zhenjun cai.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxCpServiceOnTpImpl extends WxCpServiceApacheHttpClientImpl {
  //第三方应用service	
  WxCpTpService wxCpTpService;
	
  public void setWxCpTpService(WxCpTpService wxCpTpService) {
    this.wxCpTpService = wxCpTpService;
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    if (!this.configStorage.isAccessTokenExpired() && !forceRefresh) {
      return this.configStorage.getAccessToken();
    }
    //access token通过第三方应用service获取
    //corpSecret对应企业永久授权码
    WxAccessToken accessToken = wxCpTpService.getCorpToken(this.configStorage.getCorpId(), this.configStorage.getCorpSecret());
    
    this.configStorage.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
    return this.configStorage.getAccessToken();
  }
	
	
}
