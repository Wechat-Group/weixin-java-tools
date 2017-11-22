package me.chanjar.weixin.mp.util.http.okhttp;

import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;
import me.chanjar.weixin.mp.util.http.MaterialNewsInfoRequestExecutor;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ecoolper on 2017/5/5.
 */
public class OkhttpMaterialNewsInfoRequestExecutor extends MaterialNewsInfoRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
  private static final MediaType JSON = MediaType.parse("application/json");

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  public OkhttpMaterialNewsInfoRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMpMaterialNews execute(String uri, String materialId) throws WxErrorException, IOException {

    //得到httpClient
    OkHttpClient client = requestHttp.getRequestHttpClient();

    Map<String, String> params = new HashMap<>();
    params.put("media_id", materialId);
    RequestBody requestBody = RequestBody.create(JSON, WxGsonBuilder.create().toJson(params));
    Request request = new Request.Builder().url(uri).post(requestBody).build();
    Response response = client.newCall(request).execute();
    String responseContent = response.body().string();
    this.logger.debug("响应原始数据：{}", responseContent);

    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    } else {
      return WxMpGsonBuilder.create().fromJson(responseContent, WxMpMaterialNews.class);
    }
  }
}
