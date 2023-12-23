package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaOpenApiService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.openapi.WxMiniGetApiQuotaResult;
import cn.binarywang.wx.miniapp.bean.openapi.WxMiniGetRidInfoResult;
import cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;

import static me.chanjar.weixin.common.api.WxConsts.ERR_CODE;

/**
 * @author shuiyihan12
 * @since 2023/7/7 17:08
 */
@RequiredArgsConstructor
public class WxMaOpenApiServiceImpl implements WxMaOpenApiService {
  private final WxMaService wxMaService;

  private static final String REQUEST = "request";


  @Override
  public boolean clearQuota() throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("appid", this.wxMaService.getWxMaConfig().getAppid());
    String responseContent = this.wxMaService.post(WxMaApiUrlConstants.OpenApi.CLEAR_QUOTA, params.toString());
    parseErrorResponse(responseContent);
    return true;
  }

  @Override
  public WxMiniGetApiQuotaResult getApiQuota(String cgiPath) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("cgi_path", cgiPath);
    String responseContent = this.wxMaService.post(WxMaApiUrlConstants.OpenApi.GET_API_QUOTA, params.toString());
    parseErrorResponse(responseContent);
    return WxMaGsonBuilder.create().fromJson(GsonParser.parse(responseContent), WxMiniGetApiQuotaResult.class);
  }


  @Override
  public WxMiniGetRidInfoResult getRidInfo(String rid) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("rid", rid);
    String responseContent = this.wxMaService.post(WxMaApiUrlConstants.OpenApi.GET_RID_INFO, params.toString());
    parseErrorResponse(responseContent);
    JsonObject response = GsonParser.parse(responseContent);
    if (response.has(REQUEST)) {
      return WxMaGsonBuilder.create().fromJson(response.getAsJsonObject(REQUEST), WxMiniGetRidInfoResult.class);
    }
    return null;
  }

  @Override
  public boolean clearQuotaByAppSecret() throws WxErrorException {
    String url = String.format(WxMaApiUrlConstants.OpenApi.CLEAR_QUOTA_BY_APP_SECRET, this.wxMaService.getWxMaConfig().getAppid(), this.wxMaService.getWxMaConfig().getSecret());
    String responseContent = this.wxMaService.post(url, "");
    parseErrorResponse(responseContent);
    return true;
  }


  private void parseErrorResponse(String response) throws WxErrorException {
    JsonObject jsonObject = GsonParser.parse(response);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(response, WxType.MiniApp));
    }
  }
}
