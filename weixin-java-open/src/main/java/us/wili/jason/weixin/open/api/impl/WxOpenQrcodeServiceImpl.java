package us.wili.jason.weixin.open.api.impl;

import com.google.gson.JsonObject;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpQrcodeService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import me.chanjar.weixin.mp.util.http.QrCodeRequestExecutor;
import org.apache.commons.lang3.StringUtils;
import us.wili.jason.weixin.open.api.WxOpenQrcodeService;
import us.wili.jason.weixin.open.api.WxOpenService;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Created by Binary Wang on 2016/7/21.
 */
public class WxOpenQrcodeServiceImpl implements WxOpenQrcodeService {
  private static final String API_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/qrcode";

  private WxOpenService wxOpenService;

  public WxOpenQrcodeServiceImpl(WxOpenService wxOpenService) {
    this.wxOpenService = wxOpenService;
  }

  @Override
  public WxMpQrCodeTicket qrCodeCreateTmpTicket(String appid, int sceneId, Integer expireSeconds) throws WxErrorException {
    if (sceneId == 0) {
      throw new WxErrorException(WxError.newBuilder().setErrorCode(-1).setErrorMsg("临时二维码场景值不能为0！").build());
    }

    //expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
    if (expireSeconds != null && expireSeconds > 2592000) {
      throw new WxErrorException(WxError.newBuilder().setErrorCode(-1)
        .setErrorMsg("临时二维码有效时间最大不能超过2592000（即30天）！").build());
    }

    if (expireSeconds == null) {
      expireSeconds = 30;
    }

    String url = API_URL_PREFIX + "/create";
    JsonObject json = new JsonObject();
    json.addProperty("action_name", "QR_SCENE");
    json.addProperty("expire_seconds", expireSeconds);

    JsonObject actionInfo = new JsonObject();
    JsonObject scene = new JsonObject();
    scene.addProperty("scene_id", sceneId);
    actionInfo.add("scene", scene);
    json.add("action_info", actionInfo);
    String responseContent = this.wxOpenService.post(appid, url, json.toString());
    return WxMpQrCodeTicket.fromJson(responseContent);
  }


  @Override
  public WxMpQrCodeTicket qrCodeCreateTmpTicket(String appid, String sceneStr, Integer expireSeconds) throws WxErrorException {
    if (StringUtils.isBlank(sceneStr)) {
      throw new WxErrorException(WxError.newBuilder().setErrorCode(-1).setErrorMsg("临时二维码场景值不能为空！").build());
    }

    //expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
    if (expireSeconds != null && expireSeconds > 2592000) {
      throw new WxErrorException(WxError.newBuilder().setErrorCode(-1)
        .setErrorMsg("临时二维码有效时间最大不能超过2592000（即30天）！").build());
    }

    if (expireSeconds == null) {
      expireSeconds = 30;
    }

    String url = API_URL_PREFIX + "/create";
    JsonObject json = new JsonObject();
    json.addProperty("action_name", "QR_STR_SCENE");
    json.addProperty("expire_seconds", expireSeconds);

    JsonObject actionInfo = new JsonObject();
    JsonObject scene = new JsonObject();
    scene.addProperty("scene_str", sceneStr);
    actionInfo.add("scene", scene);
    json.add("action_info", actionInfo);
    String responseContent = this.wxOpenService.post(appid, url, json.toString());
    return WxMpQrCodeTicket.fromJson(responseContent);
  }


  @Override
  public WxMpQrCodeTicket qrCodeCreateLastTicket(String appid, int sceneId) throws WxErrorException {
    if (sceneId < 1 || sceneId > 100000) {
      throw new WxErrorException(WxError.newBuilder().setErrorCode(-1).setErrorMsg("永久二维码的场景值目前只支持1--100000！").build());
    }

    String url = API_URL_PREFIX + "/create";
    JsonObject json = new JsonObject();
    json.addProperty("action_name", "QR_LIMIT_SCENE");
    JsonObject actionInfo = new JsonObject();
    JsonObject scene = new JsonObject();
    scene.addProperty("scene_id", sceneId);
    actionInfo.add("scene", scene);
    json.add("action_info", actionInfo);
    String responseContent = this.wxOpenService.post(appid, url, json.toString());
    return WxMpQrCodeTicket.fromJson(responseContent);
  }

  @Override
  public WxMpQrCodeTicket qrCodeCreateLastTicket(String appid, String sceneStr) throws WxErrorException {
    String url = API_URL_PREFIX + "/create";
    JsonObject json = new JsonObject();
    json.addProperty("action_name", "QR_LIMIT_STR_SCENE");
    JsonObject actionInfo = new JsonObject();
    JsonObject scene = new JsonObject();
    scene.addProperty("scene_str", sceneStr);
    actionInfo.add("scene", scene);
    json.add("action_info", actionInfo);
    String responseContent = this.wxOpenService.post(appid, url, json.toString());
    return WxMpQrCodeTicket.fromJson(responseContent);
  }

  @Override
  public File qrCodePicture(String appid, WxMpQrCodeTicket ticket) throws WxErrorException {
    String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode";
    return this.wxOpenService.execute(appid, QrCodeRequestExecutor.create(this.wxOpenService.getRequestHttp()), url, ticket);
  }

  @Override
  public String qrCodePictureUrl(String appid, String ticket, boolean needShortUrl) throws WxErrorException {
    String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%s";
    try {
      String resultUrl = String.format(url,
        URLEncoder.encode(ticket, StandardCharsets.UTF_8.name()));
      if (needShortUrl) {
        return this.wxOpenService.shortUrl(appid, resultUrl);
      }

      return resultUrl;
    } catch (UnsupportedEncodingException e) {
      WxError error = WxError.newBuilder().setErrorCode(-1)
        .setErrorMsg(e.getMessage()).build();
      throw new WxErrorException(error);
    }
  }

  @Override
  public String qrCodePictureUrl(String appid, String ticket) throws WxErrorException {
    return qrCodePictureUrl(appid, ticket, false);
  }

}
