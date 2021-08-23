package me.chanjar.weixin.common.util.http.okhttp;

import cn.hutool.core.util.RandomUtil;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.ByteUtil;
import me.chanjar.weixin.common.util.http.MediaUploadBytesRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import okhttp3.*;

import java.io.IOException;

/**
 * @author caiqiyuan
 */
public class OkHttpMediaUploadBytesRequestExecutor extends MediaUploadBytesRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
  public OkHttpMediaUploadBytesRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMediaUploadResult execute(String uri, byte[] bytes, WxType wxType) throws WxErrorException, IOException {
    String ext = ByteUtil.getExt(bytes);
    RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), ByteUtil.getBody(bytes));

    RequestBody body = new MultipartBody.Builder()
      .setType(MediaType.parse("multipart/form-data"))
      .addFormDataPart("media", RandomUtil.randomString(16) + "." + ext, fileBody)
      .build();
    Request request = new Request.Builder().url(uri).post(body).build();

    Response response = requestHttp.getRequestHttpClient().newCall(request).execute();
    String responseContent = response.body().string();
    WxError error = WxError.fromJson(responseContent, wxType);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return WxMediaUploadResult.fromJson(responseContent);
  }

}
