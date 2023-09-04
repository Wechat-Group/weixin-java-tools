package me.chanjar.weixin.mp.api.impl;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.bean.subscribemsg.CategoryData;
import me.chanjar.weixin.common.bean.subscribemsg.PubTemplateKeyword;
import me.chanjar.weixin.common.bean.subscribemsg.PubTemplateTitleListResult;
import me.chanjar.weixin.common.bean.subscribemsg.TemplateInfo;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.URIUtil;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpSubscribeMsgService;
import me.chanjar.weixin.mp.bean.subscribe.WxMpSubscribeMessage;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

import static me.chanjar.weixin.common.api.WxConsts.ERR_CODE;
import static me.chanjar.weixin.mp.enums.WxMpApiUrl.SubscribeMsg.*;

/**
 * 订阅消息接口.
 *
 * @author Mklaus
 * created on  2018-01-22 上午11:19
 */
@RequiredArgsConstructor
public class WxMpSubscribeMsgServiceImpl implements WxMpSubscribeMsgService {
  private final WxMpService service;

  @Override
  public String subscribeMsgAuthorizationUrl(String redirectUri, int scene, String reserved) {
    WxMpConfigStorage storage = this.service.getWxMpConfigStorage();
    return String.format(SUBSCRIBE_MESSAGE_AUTHORIZE_URL.getUrl(storage), storage.getAppId(), scene, storage.getTemplateId(),
      URIUtil.encodeURIComponent(redirectUri), reserved);
  }

  @Override
  public boolean sendOnce(WxMpSubscribeMessage message) throws WxErrorException {
    if (message.getTemplateId() == null) {
      message.setTemplateId(this.service.getWxMpConfigStorage().getTemplateId());
    }

    String responseContent = this.service.post(SEND_MESSAGE_ONCE_URL, message.toJson());
    return responseContent != null;
  }

  @Override
  public PubTemplateTitleListResult getPubTemplateTitleList(String[] ids, int start, int limit) throws WxErrorException {
    ImmutableMap<String, ? extends Serializable> params = ImmutableMap.of("ids", StringUtils.join(ids, ","),
      "start", start, "limit", limit);
    String responseText = this.service.get(GET_PUB_TEMPLATE_TITLE_LIST_URL,
      Joiner.on("&").withKeyValueSeparator("=").join(params));
    return PubTemplateTitleListResult.fromJson(responseText);
  }

  @Override
  public List<PubTemplateKeyword> getPubTemplateKeyWordsById(String id) throws WxErrorException {
    String responseText = this.service.get(GET_PUB_TEMPLATE_KEY_WORDS_BY_ID_URL,
      Joiner.on("&").withKeyValueSeparator("=").join(ImmutableMap.of("tid", id)));
    return WxMpGsonBuilder.create().fromJson(GsonParser.parse(responseText)
      .getAsJsonArray("data"), new TypeToken<List<PubTemplateKeyword>>() {
    }.getType());
  }

  @Override
  public String addTemplate(String id, List<Integer> keywordIdList, String sceneDesc) throws WxErrorException {
    String responseText = this.service.post(TEMPLATE_ADD_URL, ImmutableMap.of("tid", id,
      "kidList", keywordIdList.toArray(),
      "sceneDesc", sceneDesc));
    return GsonParser.parse(responseText).get("priTmplId").getAsString();
  }

  @Override
  public List<TemplateInfo> getTemplateList() throws WxErrorException {
    String responseText = this.service.get(TEMPLATE_LIST_URL, null);
    return WxMpGsonBuilder.create().fromJson(GsonParser.parse(responseText)
      .getAsJsonArray("data"), new TypeToken<List<TemplateInfo>>() {
    }.getType());
  }

  @Override
  public boolean delTemplate(String templateId) throws WxErrorException {
    this.service.post(TEMPLATE_DEL_URL, ImmutableMap.of("priTmplId", templateId));
    return true;
  }

  @Override
  public List<CategoryData> getCategory() throws WxErrorException {
    String responseText = this.service.get(GET_CATEGORY_URL, null);
    return WxMpGsonBuilder.create().fromJson(GsonParser.parse(responseText)
      .getAsJsonArray("data"), new TypeToken<List<CategoryData>>() {
    }.getType());
  }

  @Override
  public void send(WxMpSubscribeMessage subscribeMessage) throws WxErrorException {
    String responseContent = this.service.post(SEND_SUBSCRIBE_MESSAGE_URL, subscribeMessage.toJson());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MP));
    }
  }
}
