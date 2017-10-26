package us.wili.jason.weixin.open.api.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.template.WxMpTemplate;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateIndustry;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import us.wili.jason.weixin.open.api.WxOpenTemplateMsgService;
import us.wili.jason.weixin.open.api.WxOpenService;

import java.util.List;

/**
 * <pre>
 * Created by Binary Wang on 2016-10-14.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
public class WxOpenTemplateMsgServiceImpl implements WxOpenTemplateMsgService {
  public static final String API_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/template";
  private static final JsonParser JSON_PARSER = new JsonParser();

  private WxOpenService wxOpenService;

  public WxOpenTemplateMsgServiceImpl(WxOpenService wxOpenService) {
    this.wxOpenService = wxOpenService;
  }

  @Override
  public String sendTemplateMsg(String appid, WxMpTemplateMessage templateMessage) throws WxErrorException {
    String url = "https://api.weixin.qq.com/cgi-bin/message/template/send";
    String responseContent = this.wxOpenService.post(appid, url, templateMessage.toJson());
    final JsonObject jsonObject = JSON_PARSER.parse(responseContent).getAsJsonObject();
    if (jsonObject.get("errcode").getAsInt() == 0) {
      return jsonObject.get("msgid").getAsString();
    }
    throw new WxErrorException(WxError.fromJson(responseContent));
  }

  @Override
  public boolean setIndustry(String appid, WxMpTemplateIndustry wxMpIndustry) throws WxErrorException {
    if (null == wxMpIndustry.getPrimaryIndustry() || null == wxMpIndustry.getPrimaryIndustry().getId()
      || null == wxMpIndustry.getSecondIndustry() || null == wxMpIndustry.getSecondIndustry().getId()) {
      throw new IllegalArgumentException("行业Id不能为空，请核实");
    }

    String url = API_URL_PREFIX + "/api_set_industry";
    this.wxOpenService.post(appid, url, wxMpIndustry.toJson());
    return true;
  }

  @Override
  public WxMpTemplateIndustry getIndustry(String appid) throws WxErrorException {
    String url = API_URL_PREFIX + "/get_industry";
    String responseContent = this.wxOpenService.get(appid, url, null);
    return WxMpTemplateIndustry.fromJson(responseContent);
  }

  @Override
  public String addTemplate(String appid, String shortTemplateId) throws WxErrorException {
    String url = API_URL_PREFIX + "/api_add_template";
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("template_id_short", shortTemplateId);
    String responseContent = this.wxOpenService.post(appid, url, jsonObject.toString());
    final JsonObject result = JSON_PARSER.parse(responseContent).getAsJsonObject();
    if (result.get("errcode").getAsInt() == 0) {
      return result.get("template_id").getAsString();
    }

    throw new WxErrorException(WxError.fromJson(responseContent));
  }

  @Override
  public List<WxMpTemplate> getAllPrivateTemplate(String appid) throws WxErrorException {
    String url = API_URL_PREFIX + "/get_all_private_template";
    return WxMpTemplate.fromJson(this.wxOpenService.get(appid, url, null));
  }

  @Override
  public boolean delPrivateTemplate(String appid, String templateId) throws WxErrorException {
    String url = API_URL_PREFIX + "/del_private_template";
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("template_id", templateId);
    String responseContent = this.wxOpenService.post(appid, url, jsonObject.toString());
    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() == 0) {
      return true;
    }

    throw new WxErrorException(error);
  }

}
