package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaSecCheckService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaMediaAsyncCheckResult;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * <pre>
 *
 * Created by Binary Wang on 2018/11/24.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@AllArgsConstructor
public class WxMaSecCheckServiceImpl implements WxMaSecCheckService {
  private WxMaService service;

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
  public WxMaMediaAsyncCheckResult mediaCheckAsync(String mediaUrl, int mediaType)
    throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("media_url", mediaUrl);
    jsonObject.addProperty("media_type", mediaType);

    return WxMaMediaAsyncCheckResult
      .fromJson(this.service.post(MEDIA_CHECK_ASYNC_URL, jsonObject.toString()));
  }

}
