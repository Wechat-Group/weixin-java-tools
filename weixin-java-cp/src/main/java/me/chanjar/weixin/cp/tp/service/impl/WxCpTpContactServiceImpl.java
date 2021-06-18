package me.chanjar.weixin.cp.tp.service.impl;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpTpContactSearch;
import me.chanjar.weixin.cp.bean.WxCpTpContactSearchResp;
import me.chanjar.weixin.cp.tp.service.WxCpTpContactService;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Tp.CONTACT_SEARCH;

/**
 * @author uianz
 * @description
 * @since 2020/12/23 下午 02:39
 */
@RequiredArgsConstructor
public class WxCpTpContactServiceImpl implements WxCpTpContactService {

  private final WxCpTpService mainService;

  @Override
  public WxCpTpContactSearchResp contactSearch(WxCpTpContactSearch wxCpTpContactSearch) throws WxErrorException {
    String responseText =
      mainService.post(mainService.getWxCpTpConfigStorage().getApiUrl(CONTACT_SEARCH) + "?provider_access_token=" + mainService.getWxCpProviderToken(), wxCpTpContactSearch.toJson());
    return WxCpTpContactSearchResp.fromJson(responseText);
  }

}
