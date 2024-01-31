package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaSecurityService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaMediaAsyncCheckResult;
import cn.binarywang.wx.miniapp.bean.safety.request.WxMaUserSafetyRiskRankRequest;
import cn.binarywang.wx.miniapp.bean.safety.response.WxMaUserSafetyRiskRankResponse;
import cn.binarywang.wx.miniapp.bean.security.WxMaMediaSecCheckCheckRequest;
import cn.binarywang.wx.miniapp.bean.security.WxMaMsgSecCheckCheckRequest;
import cn.binarywang.wx.miniapp.bean.security.WxMaMsgSecCheckCheckResponse;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.json.GsonParser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.SecCheck.*;
import static me.chanjar.weixin.common.api.WxConsts.ERR_CODE;

/**
 * <pre>
 * 小程序安全接口
 * Created by Binary Wang on 2018/11/24.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RequiredArgsConstructor
public class WxMaSecurityServiceImpl implements WxMaSecurityService {
  private final WxMaService service;

  @Override
  public boolean checkImage(File file) throws WxErrorException {
    WxMediaUploadResult result = this.service.execute(MediaUploadRequestExecutor
      .create(this.service.getRequestHttp()), IMG_SEC_CHECK_URL, file);
    return result != null;
  }

  @Override
  public boolean checkImage(String fileUrl) throws WxErrorException {
    File file = new File(FileUtils.getTempDirectory(), System.currentTimeMillis() + ".tmp");
    try {
      URL url = new URL(fileUrl);
      FileUtils.copyURLToFile(url, file);
    } catch (IOException e) {
      throw new WxErrorException(WxError.builder().errorCode(-1).errorMsg("文件地址读取异常").build(), e);
    }

    return this.checkImage(file);
  }

  @Override
  public boolean checkMessage(String msgString) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("content", msgString);

    this.service.post(MSG_SEC_CHECK_URL, jsonObject.toString());

    return true;
  }

  @Override
  public WxMaMsgSecCheckCheckResponse checkMessage(WxMaMsgSecCheckCheckRequest msgRequest) throws WxErrorException {
    String response = this.service.post(MSG_SEC_CHECK_URL, msgRequest);
    parseErrorResponse(response);
    return WxMaGsonBuilder.create().fromJson(response, WxMaMsgSecCheckCheckResponse.class);
  }

  @Override
  public WxMaMediaAsyncCheckResult mediaCheckAsync(String mediaUrl, int mediaType)
    throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("media_url", mediaUrl);
    jsonObject.addProperty("media_type", mediaType);

    return WxMaMediaAsyncCheckResult
      .fromJson(this.service.post(MEDIA_CHECK_ASYNC_URL, jsonObject.toString()));
  }

  @Override
  public WxMaMediaAsyncCheckResult mediaCheckAsync(WxMaMediaSecCheckCheckRequest request) throws WxErrorException {
    String response = this.service.post(MEDIA_CHECK_ASYNC_URL, request);
    parseErrorResponse(response);
    return WxMaGsonBuilder.create().fromJson(response,WxMaMediaAsyncCheckResult.class);
  }

  @Override
  public WxMaUserSafetyRiskRankResponse getUserRiskRank(WxMaUserSafetyRiskRankRequest wxMaUserSafetyRiskRankRequest) throws WxErrorException {
    String responseContent = this.service.post(GET_USER_RISK_RANK, wxMaUserSafetyRiskRankRequest.toJson());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaUserSafetyRiskRankResponse.fromJson(responseContent);
  }

  private void parseErrorResponse(String response) throws WxErrorException {
    JsonObject jsonObject = GsonParser.parse(response);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(response, WxType.MiniApp));
    }
  }
}
