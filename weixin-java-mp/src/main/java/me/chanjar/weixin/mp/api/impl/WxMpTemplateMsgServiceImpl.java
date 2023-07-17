package me.chanjar.weixin.mp.api.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplate;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateIndustry;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.util.Collections;
import java.util.List;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.TemplateMsg.*;

/**
 * <pre>
 * Created by Binary Wang on 2016-10-14.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RequiredArgsConstructor
public class WxMpTemplateMsgServiceImpl implements WxMpTemplateMsgService {
  private final WxMpService wxMpService;

  @Override
  public String sendTemplateMsg(WxMpTemplateMessage templateMessage) throws WxErrorException {
    String responseContent = this.wxMpService.post(MESSAGE_TEMPLATE_SEND, templateMessage.toJson());
    final JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxConsts.ERR_CODE).getAsInt() == 0) {
      return jsonObject.get("msgid").getAsString();
    }
    throw new WxErrorException(WxError.fromJson(responseContent, WxType.MP));
  }

  @Override
  public boolean setIndustry(WxMpTemplateIndustry wxMpIndustry) throws WxErrorException {
    if (null == wxMpIndustry.getPrimaryIndustry() || null == wxMpIndustry.getPrimaryIndustry().getCode()
      || null == wxMpIndustry.getSecondIndustry() || null == wxMpIndustry.getSecondIndustry().getCode()) {
      throw new IllegalArgumentException("行业Id不能为空，请核实");
    }

    this.wxMpService.post(TEMPLATE_API_SET_INDUSTRY, wxMpIndustry.toJson());
    return true;
  }

  @Override
  public WxMpTemplateIndustry getIndustry() throws WxErrorException {
    String responseContent = this.wxMpService.get(TEMPLATE_GET_INDUSTRY, null);
    return WxMpTemplateIndustry.fromJson(responseContent);
  }

  @Override
  public String addTemplate(String shortTemplateId) throws WxErrorException {
    return this.addTemplate(shortTemplateId, Collections.emptyList());
  }

  @Override
  public String addTemplate(String shortTemplateId, List<String> keywordNameList) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    Gson gson = new Gson();
    jsonObject.addProperty("template_id_short", shortTemplateId);
    jsonObject.addProperty("keyword_name_list", gson.toJson(keywordNameList));
    String responseContent = this.wxMpService.post(TEMPLATE_API_ADD_TEMPLATE, jsonObject.toString());
    final JsonObject result = GsonParser.parse(responseContent);
    if (result.get(WxConsts.ERR_CODE).getAsInt() == 0) {
      return result.get("template_id").getAsString();
    }

    throw new WxErrorException(WxError.fromJson(responseContent, WxType.MP));
  }

  @Override
  public List<WxMpTemplate> getAllPrivateTemplate() throws WxErrorException {
    return WxMpTemplate.fromJson(this.wxMpService.get(TEMPLATE_GET_ALL_PRIVATE_TEMPLATE, null));
  }

  @Override
  public boolean delPrivateTemplate(String templateId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("template_id", templateId);
    String responseContent = this.wxMpService.post(TEMPLATE_DEL_PRIVATE_TEMPLATE, jsonObject.toString());
    WxError error = WxError.fromJson(responseContent, WxType.MP);
    if (error.getErrorCode() == 0) {
      return true;
    }

    throw new WxErrorException(error);
  }

}
