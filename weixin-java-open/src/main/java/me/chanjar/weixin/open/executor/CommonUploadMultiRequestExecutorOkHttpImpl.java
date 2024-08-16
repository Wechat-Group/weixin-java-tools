package me.chanjar.weixin.open.executor;

import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.bean.CommonUploadData;
import me.chanjar.weixin.open.bean.CommonUploadMultiParam;
import me.chanjar.weixin.common.bean.CommonUploadParam;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import okhttp3.*;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author xzh
 * @Description
 * @createTime 2024/08/14 16:48
 */
public class CommonUploadMultiRequestExecutorOkHttpImpl extends CommonUploadMultiRequestExecutor<OkHttpClient, OkHttpProxyInfo> {

  public CommonUploadMultiRequestExecutorOkHttpImpl(RequestHttp<OkHttpClient, OkHttpProxyInfo> requestHttp) {
    super(requestHttp);
  }

  @Override
  public String execute(String uri, CommonUploadMultiParam param, WxType wxType) throws WxErrorException, IOException {
    MultipartBody.Builder builder = new MultipartBody.Builder()
      .setType(MediaType.get("multipart/form-data"));

    List<CommonUploadMultiParam.NormalParam> normalParams = param.getNormalParams();
    if (!CollectionUtils.isEmpty(normalParams)) {
      for (CommonUploadMultiParam.NormalParam normalParam : normalParams) {
        builder.addFormDataPart(normalParam.getName(), normalParam.getValue());
      }
    }

    CommonUploadParam uploadParam = param.getUploadParam();
    if (uploadParam != null) {
      RequestBody requestBody = new CommonUpdateDataToRequestBodyAdapter(uploadParam.getData());
      builder.addFormDataPart(uploadParam.getName(), uploadParam.getData().getFileName(), requestBody);
    }

    Request request = new Request.Builder().url(uri).post(builder.build()).build();

    try (Response response = requestHttp.getRequestHttpClient().newCall(request).execute()) {
      ResponseBody responseBody = response.body();
      String responseContent = responseBody == null ? "" : responseBody.string();
      if (responseContent.isEmpty()) {
        throw new WxErrorException(String.format("上传失败，服务器响应空 url:%s param:%s", uri, param));
      }
      WxError error = WxError.fromJson(responseContent, wxType);
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error);
      }
      return responseContent;
    }
  }

  /**
   * 通用上传输入 到 OkHttp 请求提 适配器
   */
  @AllArgsConstructor
  public static class CommonUpdateDataToRequestBodyAdapter extends RequestBody {

    private static final MediaType CONTENT_TYPE = MediaType.get("application/octet-stream");

    private CommonUploadData data;

    @Override
    public long contentLength() {
      return data.getLength();
    }

    @Nullable
    @Override
    public MediaType contentType() {
      return CONTENT_TYPE;
    }

    @Override
    public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {
      InputStream inputStream = data.getInputStream();
      int count;
      byte[] buffer = new byte[4096];
      while ((count = inputStream.read(buffer)) != -1) {
        bufferedSink.write(buffer, 0, count);
      }
      inputStream.close();
    }

    @Override
    public boolean isOneShot() {
      return true;
    }
  }
}
