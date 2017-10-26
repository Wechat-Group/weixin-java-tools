package us.wili.jason.weixin.open.api.test;

import us.wili.jason.weixin.open.api.impl.WxOpenServiceAbstractApacheHttpClientImpl;

import java.util.Map;

/**
 * Created by JasonY on 17/10/25.
 */
public class WxOpenServiceImpl extends WxOpenServiceAbstractApacheHttpClientImpl {

  private Map<String, String> refreshTokenMap;

  @Override
  public String getAuthorizerRefreshToken(String appid) {
    return refreshTokenMap.get(appid);
  }

  public Map<String, String> getRefreshTokenMap() {
    return refreshTokenMap;
  }

  public void setRefreshTokenMap(Map<String, String> refreshTokenMap) {
    this.refreshTokenMap = refreshTokenMap;
  }
}
