package me.chanjar.weixin.cp.api.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpExternalContactService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpUserExternalContactInfo;
import me.chanjar.weixin.cp.bean.WxCpUserExternalContactList;
import me.chanjar.weixin.cp.bean.WxCpUserWithExternalPermission;

import java.util.List;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.ExternalContact.*;

public class WxCpExternalContactServiceImpl implements WxCpExternalContactService {
  private WxCpService mainService;

  public WxCpExternalContactServiceImpl(WxCpService mainService) {
    this.mainService = mainService;
  }

  @Override
  public WxCpUserExternalContactInfo getExternalContact(String userId) throws WxErrorException {
    String responseContent = this.mainService.get(this.mainService.getWxCpConfigStorage().getApiUrl(GET_EXTERNAL_CONTACT + userId), null);

    return WxCpUserExternalContactInfo.fromJson(responseContent);
  }

  @Override
  public List<String> listExternalContacts(String userId) throws WxErrorException {
    String responseContent = this.mainService.get(this.mainService.getWxCpConfigStorage().getApiUrl(LIST_EXTERNAL_CONTACT + userId), null);
    WxCpUserExternalContactList list = WxCpUserExternalContactList.fromJson(responseContent);
    return list.getExternalUserId();
  }

  @Override
  public List<String> listFollowUser() throws WxErrorException {
    String responseContent = this.mainService.get(this.mainService.getWxCpConfigStorage().getApiUrl(GET_FOLLOW_USER_LIST), null);
    WxCpUserWithExternalPermission list = WxCpUserWithExternalPermission.fromJson(responseContent);
    return list.getFollowUser();
  }
}
