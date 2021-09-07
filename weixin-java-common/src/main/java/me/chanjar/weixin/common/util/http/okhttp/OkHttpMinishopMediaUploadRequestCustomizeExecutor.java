package me.chanjar.weixin.common.util.http.okhttp;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.result.WxMinishopImageUploadCustomizeResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.MinishopUploadRequestCustomizeExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import okhttp3.*;

import java.io.File;
import java.io.IOException;

/**
 * @author liming1019
 * @date 2021/8/10
 */
@Slf4j
public class OkHttpMinishopMediaUploadRequestCustomizeExecutor extends MinishopUploadRequestCustomizeExecutor<OkHttpClient, OkHttpProxyInfo> {
  public OkHttpMinishopMediaUploadRequestCustomizeExecutor(RequestHttp requestHttp, String respType) {
    super(requestHttp, respType);
  }

  @Override
  public WxMinishopImageUploadCustomizeResult execute(String uri, File file, WxType wxType) throws WxErrorException, IOException {

    RequestBody body = new MultipartBody.Builder()
      .setType(MediaType.parse("multipart/form-data"))
      .addFormDataPart("media",
        file.getName(),
        RequestBody.create(MediaType.parse("application/octet-stream"), file))
      .build();
    Request request = new Request.Builder().url(uri).post(body).build();

    Response response = requestHttp.getRequestHttpClient().newCall(request).execute();
    String responseContent = response.body().string();
    WxError error = WxError.fromJson(responseContent, wxType);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    log.info("responseContent: " + responseContent);

    return WxMinishopImageUploadCustomizeResult.fromJson(responseContent);
  }

}
