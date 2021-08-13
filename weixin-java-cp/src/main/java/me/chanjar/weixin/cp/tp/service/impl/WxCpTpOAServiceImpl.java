package me.chanjar.weixin.cp.tp.service.impl;

import com.google.gson.JsonObject;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.bean.oa.WxCpApprovalDetailResult;
import me.chanjar.weixin.cp.bean.oa.WxCpOaApplyEventRequest;
import me.chanjar.weixin.cp.bean.oa.WxCpTemplateResult;
import me.chanjar.weixin.cp.tp.service.WxCpTpOAService;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Oa.*;

/**
 * 企业微信 OA 接口实现
 *
 * @author Element
 * @date 2019-04-06 11:20
 */
@RequiredArgsConstructor
public class WxCpTpOAServiceImpl implements WxCpTpOAService {
  private final WxCpTpService mainService;


  @Override
  public String apply(String corpId, WxCpOaApplyEventRequest request) throws WxErrorException {
    String url = mainService.getCorpApiUrl(APPLY_EVENT, corpId);

    String responseContent = this.mainService.post(url, request.toJson());
    return GsonParser.parse(responseContent).get("sp_no").getAsString();
  }

  @Override
  public WxCpTemplateResult getTemplateDetail(String corpId, @NonNull String templateId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("template_id", templateId);
    String url = mainService.getCorpApiUrl(GET_TEMPLATE_DETAIL, corpId);
    String responseContent = this.mainService.post(url, jsonObject.toString());
    return WxCpGsonBuilder.create().fromJson(responseContent, WxCpTemplateResult.class);
  }

  @Override
  public String copyTemplate(String corpId, @NonNull String openTemplateId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("open_template_id", openTemplateId);
    String url = mainService.getCorpApiUrl(COPY_TEMPLATE, corpId);
    String responseContent = this.mainService.post(url, jsonObject.toString());
    return GsonParser.parse(responseContent).get("template_id").getAsString();
  }

  @Override
  public WxCpApprovalDetailResult getApprovalDetail(String corpId, @NonNull String spNo) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("sp_no", spNo);
    final String url = mainService.getCorpApiUrl(GET_APPROVAL_DETAIL, corpId);
    String responseContent = this.mainService.post(url, jsonObject.toString());
    return WxCpGsonBuilder.create().fromJson(responseContent, WxCpApprovalDetailResult.class);
  }
}
