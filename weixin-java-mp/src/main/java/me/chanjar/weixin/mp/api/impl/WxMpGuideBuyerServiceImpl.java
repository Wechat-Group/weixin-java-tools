package me.chanjar.weixin.mp.api.impl;

import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.api.WxMpGuideBuyerService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.guide.*;
import me.chanjar.weixin.mp.enums.WxMpApiUrl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * @date 2021/5/13/013
 */
@AllArgsConstructor
public class WxMpGuideBuyerServiceImpl implements WxMpGuideBuyerService {
  private static final String ACCOUNT = "guide_account";
  private static final String OPENID = "guide_openid";
  private final WxMpService mpService;

  @Override
  public List<WxMpGuideBuyerResp> addGuideBuyerRelation(String account, String openid, List<WxMpAddGuideBuyerInfo> infos) throws WxErrorException {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put(ACCOUNT, account);
    body.put(OPENID, openid);
    body.put("buyer_list", infos);

    String json = this.mpService.post(WxMpApiUrl.Guide.ADD_GUIDE_BUYER_RELATION, body);
    return WxGsonBuilder.create().fromJson(GsonParser.parse(json).getAsJsonArray("buyer_resp"),
      new TypeToken<List<WxMpGuideBuyerResp>>() {
      }.getType());
  }

  @Override
  public void addGuideBuyerRelation(String account, String openid, String userOpenid, String nickname) throws WxErrorException {
    this.mpService.post(WxMpApiUrl.Guide.ADD_GUIDE_BUYER_RELATION,
      GsonHelper.buildJsonObject(ACCOUNT, account, OPENID, openid, "openid", userOpenid, "buyer_nickname", nickname));
  }

  @Override
  public List<WxMpGuideBuyerResp> delGuideBuyerRelation(String account, String openid, List<String> buyerOpenIds) throws WxErrorException {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put(ACCOUNT, account);
    body.put(OPENID, openid);
    body.put("openid_list", buyerOpenIds);

    String json = this.mpService.post(WxMpApiUrl.Guide.DEL_GUIDE_BUYER_RELATION, body);
    return WxGsonBuilder.create().fromJson(GsonParser.parse(json).getAsJsonArray("buyer_resp"),
      new TypeToken<List<WxMpGuideBuyerResp>>() {
      }.getType());
  }

  @Override
  public void delGuideBuyerRelation(String account, String openid, String userOpenid) throws WxErrorException {
    this.mpService.post(WxMpApiUrl.Guide.DEL_GUIDE_BUYER_RELATION,
      GsonHelper.buildJsonObject(ACCOUNT, account, OPENID, openid, "openid", userOpenid));
  }

  @Override
  public WxMpGuideBuyerInfoList getGuideBuyerRelationList(String account, String openid, int page, int num) throws WxErrorException {
    return WxMpGuideBuyerInfoList.fromJson(this.mpService.post(WxMpApiUrl.Guide.GET_GUIDE_BUYER_RELATION_LIST,
      GsonHelper.buildJsonObject(ACCOUNT, account, OPENID, openid, "page", page, "num", num)));
  }

  @Override
  public List<WxMpGuideBuyerResp> rebindGuideAcctForBuyer(String oldAccount, String oldOpenid, String account, String openid, List<String> buyerOpenIds) throws WxErrorException {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("old_guide_account", oldAccount);
    body.put("old_guide_openid", oldOpenid);
    body.put("new_guide_account", account);
    body.put("new_guide_openid", openid);
    body.put("openid_list", buyerOpenIds);

    String json = this.mpService.post(WxMpApiUrl.Guide.REBIND_GUIDE_ACCT_FOR_BUYER, body);
    return WxGsonBuilder.create().fromJson(GsonParser.parse(json).getAsJsonArray("buyer_resp"),
      new TypeToken<List<WxMpGuideBuyerResp>>() {
      }.getType());
  }

  @Override
  public void rebindGuideAcctForBuyer(String oldAccount, String oldOpenid, String account, String openid, String userOpenid) throws WxErrorException {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("old_guide_account", oldAccount);
    body.put("old_guide_openid", oldOpenid);
    body.put("new_guide_account", account);
    body.put("new_guide_openid", openid);
    body.put("openid", userOpenid);

    this.mpService.post(WxMpApiUrl.Guide.REBIND_GUIDE_ACCT_FOR_BUYER, body);
  }

  @Override
  public void updateGuideBuyerRelation(String account, String openid, String userOpenid, String nickname) throws WxErrorException {
    this.mpService.post(WxMpApiUrl.Guide.UPDATE_GUIDE_BUYER_RELATION,
      GsonHelper.buildJsonObject(ACCOUNT, account, OPENID, openid,
        "openid", userOpenid, "buyer_nickname", nickname));
  }

  @Override
  public WxMpGuideBuyerRelation getGuideBuyerRelationByBuyer(String openid) throws WxErrorException {
    return WxMpGuideBuyerRelation.fromJson(this.mpService.post(WxMpApiUrl.Guide.GET_GUIDE_BUYER_RELATION_BY_BUYER,
      GsonHelper.buildJsonObject("openid", openid)));
  }

  @Override
  public WxMpGuideBuyerInfo getGuideBuyerRelation(String account, String openid, String userOpenid) throws WxErrorException {
    return WxMpGuideBuyerInfo.fromJson(this.mpService.post(WxMpApiUrl.Guide.GET_GUIDE_BUYER_RELATION,
      GsonHelper.buildJsonObject(ACCOUNT, account, OPENID, openid,
        "openid", userOpenid)));
  }
}
