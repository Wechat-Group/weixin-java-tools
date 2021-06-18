package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.AbstractWxMaQrcodeWrapper;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author wenqiang
 * @since 2020/12/25
 */
public class OkHttpQrcodeFileRequestExecutor extends QrcodeRequestExecutor<OkHttpClient, OkHttpProxyInfo> {

  private final String filePath;

  public OkHttpQrcodeFileRequestExecutor(RequestHttp<OkHttpClient, OkHttpProxyInfo> requestHttp, String filePath) {
    super(requestHttp);
    this.filePath = filePath;
  }

  /**
   * 执行http请求.
   *
   * @param uri           uri
   * @param qrcodeWrapper 数据
   * @param wxType        微信模块类型
   * @return 响应结果
   * @throws WxErrorException 自定义异常
   * @throws IOException      io异常
   */
  @Override
  public File execute(String uri, AbstractWxMaQrcodeWrapper qrcodeWrapper, WxType wxType) throws WxErrorException, IOException {
    RequestBody body = RequestBody.Companion.create(qrcodeWrapper.toJson(), MediaType.parse("text/plain; charset=utf-8"));
    Request request = new Request.Builder().url(uri).post(body).build();
    Response response = requestHttp.getRequestHttpClient().newCall(request).execute();
    String contentTypeHeader = response.header("Content-Type");
    if ("text/plain".equals(contentTypeHeader)) {
      String responseContent = response.body().string();
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MP));
    }

    try (InputStream inputStream = response.body().byteStream()) {
      if (StringUtils.isBlank(filePath)) {
        return FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), "jpg");
      }
      return FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), "jpg", Paths.get(filePath).toFile());
    }
  }
}
