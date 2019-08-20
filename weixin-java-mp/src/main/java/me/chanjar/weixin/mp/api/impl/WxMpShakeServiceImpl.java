package me.chanjar.weixin.mp.api.impl;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpShakeService;
import me.chanjar.weixin.mp.bean.WxMpShakeInfoResult;
import me.chanjar.weixin.mp.bean.WxMpShakeQuery;
import me.chanjar.weixin.mp.bean.shake.*;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.ShakeAround.*;

/**
 * Created by rememberber on 2017/6/5.
 *
 * @author rememberber
 */
@RequiredArgsConstructor
public class WxMpShakeServiceImpl implements WxMpShakeService {
  private final WxMpService wxMpService;

  @Override
  public WxMpShakeInfoResult getShakeInfo(WxMpShakeQuery wxMpShakeQuery) throws WxErrorException {
    String postData = wxMpShakeQuery.toJsonString();
    String responseContent = this.wxMpService.post(SHAKEAROUND_USER_GETSHAKEINFO, postData);
    return WxMpShakeInfoResult.fromJson(responseContent);
  }

  @Override
  public WxMpShakeAroundPageAddResult pageAdd(WxMpShakeAroundPageAddQuery shakeAroundPageAddQuery)
    throws WxErrorException {
    String postData = shakeAroundPageAddQuery.toJsonString();
    String responseContent = this.wxMpService.post(SHAKEAROUND_PAGE_ADD, postData);
    return WxMpShakeAroundPageAddResult.fromJson(responseContent);
  }

  @Override
  public WxError deviceBindPageQuery(WxMpShakeAroundDeviceBindPageQuery shakeAroundDeviceBindPageQuery)
    throws WxErrorException {
    String postData = shakeAroundDeviceBindPageQuery.toJsonString();
    String responseContent = this.wxMpService.post(SHAKEAROUND_DEVICE_BINDPAGE, postData);
    return WxError.fromJson(responseContent, WxType.MP);
  }

  @Override
  public WxMpShakeAroundRelationSearchResult relationSearch(WxMpShakeAroundRelationSearchQuery searchQuery)
    throws WxErrorException {
    String postData = searchQuery.toJsonString();
    String responseContent = this.wxMpService.post(SHAKEAROUND_RELATION_SEARCH, postData);
    return WxMpShakeAroundRelationSearchResult.fromJson(responseContent);
  }
}
