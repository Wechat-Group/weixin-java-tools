package me.chanjar.weixin.cp.api.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpOaWeDriveService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.oa.wedrive.WxCpSpaceCreateData;
import me.chanjar.weixin.cp.bean.oa.wedrive.WxCpSpaceCreateRequest;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Oa.SPACE_CREATE;

/**
 * 企业微信微盘接口实现类.
 *
 * @author Wang_Wong
 * @date 2022-04-22
 */
@Slf4j
@RequiredArgsConstructor
public class WxCpOaWeDriveServiceImpl implements WxCpOaWeDriveService {
  private final WxCpService cpService;

  @Override
  public WxCpSpaceCreateData spaceCreate(@NonNull WxCpSpaceCreateRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(SPACE_CREATE);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpSpaceCreateData.fromJson(responseContent);
  }

}
