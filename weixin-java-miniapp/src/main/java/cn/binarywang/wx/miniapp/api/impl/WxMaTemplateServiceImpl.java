package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaTemplateService;
import cn.binarywang.wx.miniapp.bean.template.WxMaTemplateAddResult;
import cn.binarywang.wx.miniapp.bean.template.WxMaTemplateLibraryGetResult;
import cn.binarywang.wx.miniapp.bean.template.WxMaTemplateLibraryListResult;
import cn.binarywang.wx.miniapp.bean.template.WxMaTemplateListResult;
import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.util.List;
import java.util.Map;

/**
 * @author Binary
 */
@AllArgsConstructor
public class WxMaTemplateServiceImpl implements WxMaTemplateService {
  private WxMaService wxMaService;

  @Override
  public WxMaTemplateLibraryListResult findTemplateLibraryList(int offset, int count) throws WxErrorException {
    Map<String, Integer> params = ImmutableMap.of("offset", offset, "count", count);
    String responseText = this.wxMaService.post(TEMPLATE_LIBRARY_LIST_URL, WxGsonBuilder.create().toJson(params));
    return WxMaTemplateLibraryListResult.fromJson(responseText);
  }

  @Override
  public WxMaTemplateLibraryGetResult findTemplateLibraryKeywordList(String id) throws WxErrorException {
    String responseText = this.wxMaService.post(TEMPLATE_LIBRARY_KEYWORD_URL,
      WxGsonBuilder.create().toJson(ImmutableMap.of("id", id)));
    return WxMaTemplateLibraryGetResult.fromJson(responseText);
  }

  @Override
  public WxMaTemplateAddResult addTemplate(String id, List<Integer> keywordIdList) throws WxErrorException {
    String responseText = this.wxMaService.post(TEMPLATE_ADD_URL,
      WxGsonBuilder.create().toJson(ImmutableMap.of("id", id, "keyword_id_list", keywordIdList.toArray())));
    return WxMaTemplateAddResult.fromJson(responseText);
  }

  @Override
  public WxMaTemplateListResult findTemplateList(int offset, int count) throws WxErrorException {
    Map<String, Integer> params = ImmutableMap.of("offset", offset, "count", count);
    String responseText = this.wxMaService.post(TEMPLATE_LIST_URL, WxGsonBuilder.create().toJson(params));
    return WxMaTemplateListResult.fromJson(responseText);
  }

  @Override
  public boolean delTemplate(String templateId) throws WxErrorException {
    Map<String, String> params = ImmutableMap.of("template_id", templateId);
    this.wxMaService.post(TEMPLATE_DEL_URL, WxGsonBuilder.create().toJson(params));
    return true;
  }
}
