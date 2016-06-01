package me.chanjar.weixin.mp.util.http;

import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.Utf8ResponseHandler;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.bean.WxMpMaterial;
import me.chanjar.weixin.mp.bean.result.WxMpMaterialUploadResult;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.*;
import java.util.Map;

public class MaterialUploadRequestExecutor implements RequestExecutor<WxMpMaterialUploadResult, WxMpMaterial> {

  public WxMpMaterialUploadResult execute(CloseableHttpClient httpclient, HttpHost httpProxy, String uri, WxMpMaterial material) throws WxErrorException, ClientProtocolException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (httpProxy != null) {
      RequestConfig response = RequestConfig.custom().setProxy(httpProxy).build();
      httpPost.setConfig(response);
    }

    if (material != null) {
      File file = material.getFile();
      if (file == null || !file.exists()) {
        throw new FileNotFoundException();
      }
      BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
      MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
      multipartEntityBuilder
          .addPart("media", new InputStreamBody(bufferedInputStream, material.getName()))
          .setMode(HttpMultipartMode.RFC6532);
      Map<String, String> form = material.getForm();
      if (material.getForm() != null) {
        multipartEntityBuilder.addTextBody("description", WxGsonBuilder.create().toJson(form));
      }
      httpPost.setEntity(multipartEntityBuilder.build());
      httpPost.setHeader("Content-Type", ContentType.MULTIPART_FORM_DATA.toString());
    }

    CloseableHttpResponse response = httpclient.execute(httpPost);
    String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    } else {
      return WxMpMaterialUploadResult.fromJson(responseContent);
    }
  }

}
