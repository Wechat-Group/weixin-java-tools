package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonObject;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpOaWeDriveService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.oa.wedrive.*;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Oa.*;

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

  @Override
  public WxCpBaseResp spaceRename(@NonNull WxCpSpaceRenameRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(SPACE_RENAME);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp spaceDismiss(@NonNull String userId, @NonNull String spaceId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(SPACE_DISMISS);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("userid", userId);
    jsonObject.addProperty("spaceid", spaceId);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpSpaceInfo spaceInfo(@NonNull String userId, @NonNull String spaceId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(SPACE_INFO);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("userid", userId);
    jsonObject.addProperty("spaceid", spaceId);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpSpaceInfo.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp spaceAclAdd(@NonNull WxCpSpaceAclAddRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(SPACE_ACL_ADD);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

}
