package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaSubscribeService;
import cn.binarywang.wx.miniapp.bean.template.WxMaPubTemplateTitleListResult;
import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-12-15
 */
@AllArgsConstructor
public class WxMaSubscribeServiceImpl implements WxMaSubscribeService {
  private WxMaService wxMaService;

  @Override
  public WxMaPubTemplateTitleListResult getPubTemplateTitleList(String[] ids, int start, int limit) throws WxErrorException {
    ImmutableMap<String, ? extends Serializable> params = ImmutableMap.of("ids", StringUtils.join(ids, ","),
      "start", start, "limit", limit);
    String responseText = this.wxMaService.get(GET_PUB_TEMPLATE_TITLE_LIST_URL,
      Joiner.on("&").withKeyValueSeparator("=").join(params));
    return WxMaPubTemplateTitleListResult.fromJson(responseText);
  }

  @Override
  public List<PubTemplateKeyword> getPubTemplateKeyWordsById(String id) throws WxErrorException {
    String responseText = this.wxMaService.get(GET_PUB_TEMPLATE_KEY_WORDS_BY_ID_URL,
      Joiner.on("&").withKeyValueSeparator("=").join(ImmutableMap.of("tid", id)));
    return WxMaGsonBuilder.create().fromJson(new JsonParser().parse(responseText).getAsJsonObject()
      .getAsJsonArray("data"), new TypeToken<List<PubTemplateKeyword>>() {
    }.getType());
  }

  @Override
  public String addTemplate(String id, List<Integer> keywordIdList, String sceneDesc) throws WxErrorException {
    String responseText = this.wxMaService.post(TEMPLATE_ADD_URL, ImmutableMap.of("tid", id,
      "kidList", keywordIdList.toArray(),
      "sceneDesc", sceneDesc));
    return new JsonParser().parse(responseText).getAsJsonObject().get("priTmplId").getAsString();
  }

  @Override
  public List<TemplateInfo> getTemplateList() throws WxErrorException {
    String responseText = this.wxMaService.get(TEMPLATE_LIST_URL, null);
    return WxMaGsonBuilder.create().fromJson(new JsonParser().parse(responseText).getAsJsonObject()
      .getAsJsonArray("data"), new TypeToken<List<TemplateInfo>>() {
    }.getType());
  }

  @Override
  public boolean delTemplate(String templateId) throws WxErrorException {
    this.wxMaService.post(TEMPLATE_DEL_URL, ImmutableMap.of("priTmplId", templateId));
    return true;
  }

  @Override
  public List<CategoryData> getCategory() throws WxErrorException {
    String responseText = this.wxMaService.get(GET_CATEGORY_URL, null);
    return WxMaGsonBuilder.create().fromJson(new JsonParser().parse(responseText).getAsJsonObject()
      .getAsJsonArray("data"), new TypeToken<List<CategoryData>>() {
    }.getType());
  }
}
