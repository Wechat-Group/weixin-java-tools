package me.chanjar.weixin.mp.api.impl;

import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.mp.api.WxMpDraftService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.draft.WxMpAddDraft;
import me.chanjar.weixin.mp.bean.draft.WxMpDraftArticles;
import me.chanjar.weixin.mp.bean.draft.WxMpDraftInfo;
import me.chanjar.weixin.mp.bean.draft.WxMpDraftList;
import me.chanjar.weixin.mp.bean.draft.WxMpUpdateDraft;
import me.chanjar.weixin.mp.enums.WxMpApiUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * 草稿箱能力-service实现类.
 *
 * @author dragon
 * @date 2021-10-22
 */
@AllArgsConstructor
public class WxMpDraftServiceImpl implements WxMpDraftService {

  private static final String MEDIA_ID = "media_id";
  private static final String ERRCODE_SUCCESS = "0";
  private static final String ERRCODE = "errcode";
  private final WxMpService mpService;

  @Override
  public String addDraft(String title, String content, String thumbMediaId) throws WxErrorException {
    List<WxMpDraftArticles> draftArticleList = new ArrayList<>();
    WxMpDraftArticles draftArticle = WxMpDraftArticles.builder()
      .title(title).content(content).thumbMediaId(thumbMediaId).build();
    WxMpAddDraft addDraft = WxMpAddDraft.builder().articles(draftArticleList).build();
    draftArticleList.add(draftArticle);
    return addDraft(addDraft);
  }

  @Override
  public String addDraft(WxMpAddDraft addDraft) throws WxErrorException {
    String json = this.mpService.post(WxMpApiUrl.Draft.ADD_DRAFT, addDraft);
    return GsonParser.parse(json).get(MEDIA_ID).toString();
  }

  @Override
  public Boolean updateDraft(WxMpUpdateDraft updateDraftInfo) throws WxErrorException {
    String json = this.mpService.post(WxMpApiUrl.Draft.UPDATE_DRAFT, updateDraftInfo);
    return GsonParser.parse(json).get(ERRCODE).toString().equals(ERRCODE_SUCCESS);
  }

  @Override
  public WxMpDraftInfo getDraft(String mediaId) throws WxErrorException {
    return WxMpDraftInfo.fromJson(this.mpService.post(WxMpApiUrl.Draft.GET_DRAFT,
      GsonHelper.buildJsonObject(MEDIA_ID, mediaId)));
  }

  @Override
  public Boolean delDraft(String mediaId) throws WxErrorException {
    String json = this.mpService.post(WxMpApiUrl.Draft.DEL_DRAFT,
      GsonHelper.buildJsonObject(MEDIA_ID, mediaId));
    return GsonParser.parse(json).get(ERRCODE).toString().equals(ERRCODE_SUCCESS);
  }

  @Override
  public WxMpDraftList listDraft(int offset, int count, int noContent) throws WxErrorException {
    return WxMpDraftList.fromJson(this.mpService.post(WxMpApiUrl.Draft.LIST_DRAFT,
      GsonHelper.buildJsonObject("offset", offset, "count", count, "no_content", noContent)));
  }

  @Override
  public WxMpDraftList listDraft(int offset, int count) throws WxErrorException {
    return WxMpDraftList.fromJson(this.mpService.post(WxMpApiUrl.Draft.LIST_DRAFT,
      GsonHelper.buildJsonObject("offset", offset, "count", count)));
  }

  @Override
  public Long countDraft() throws WxErrorException {
    String json = this.mpService.get(WxMpApiUrl.Draft.COUNT_DRAFT, null);
    return Long.valueOf(GsonParser.parse(json).get("total_count").toString());
  }
}
