package me.chanjar.weixin.mp.api.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.BeanUtils;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpStoreService;
import me.chanjar.weixin.mp.bean.store.WxMpStoreBaseInfo;
import me.chanjar.weixin.mp.bean.store.WxMpStoreInfo;
import me.chanjar.weixin.mp.bean.store.WxMpStoreListResult;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.util.List;

/**
 *  Created by Binary Wang on 2016/9/26.
 * @author binarywang (https://github.com/binarywang)
 *
 */
public class WxMpStoreServiceImpl implements WxMpStoreService {
  private static final String API_BASE_URL = "http://api.weixin.qq.com/cgi-bin/poi";

  private WxMpService wxMpService;

  public WxMpStoreServiceImpl(WxMpService wxMpService) {
    this.wxMpService = wxMpService;
  }

  @Override
  public void add(WxMpStoreBaseInfo request) throws WxErrorException {
    BeanUtils.checkRequiredFields(request);

    String url = API_BASE_URL + "/addpoi";
    String response = this.wxMpService.post(url, request.toJson());
    WxError wxError = WxError.fromJson(response);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public WxMpStoreBaseInfo get(String poiId) throws WxErrorException {
    String url = API_BASE_URL + "/getpoi";
    JsonObject paramObject = new JsonObject();
    paramObject.addProperty("poi_id",poiId);
    String response = this.wxMpService.post(url, paramObject.toString());
    WxError wxError = WxError.fromJson(response);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
    return WxMpStoreBaseInfo.fromJson(new JsonParser().parse(response).getAsJsonObject()
        .get("business").getAsJsonObject().get("base_info").toString());
  }

  @Override
  public void delete(String poiId) throws WxErrorException {
    String url = API_BASE_URL + "/delpoi";
    JsonObject paramObject = new JsonObject();
    paramObject.addProperty("poi_id",poiId);
    String response = this.wxMpService.post(url, paramObject.toString());
    WxError wxError = WxError.fromJson(response);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public WxMpStoreListResult list(int begin, int limit)
      throws WxErrorException {
    String url = API_BASE_URL + "/getpoilist";
    JsonObject params = new JsonObject();
    params.addProperty("begin", begin);
    params.addProperty("limit", limit);
    String response = this.wxMpService.post(url, params.toString());

    WxError wxError = WxError.fromJson(response);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }

    return WxMpStoreListResult.fromJson(response);
  }

  @Override
  public List<WxMpStoreInfo> listAll() throws WxErrorException {
    int limit = 50;
    WxMpStoreListResult list = this.list(0, limit);
    List<WxMpStoreInfo> stores = list.getBusinessList();
    if (list.getTotalCount() > limit) {
      int begin = limit;
      WxMpStoreListResult followingList = this.list(begin, limit);
      while (followingList.getBusinessList().size() > 0) {
        stores.addAll(followingList.getBusinessList());
        begin += limit;
        if (begin >= list.getTotalCount()) {
          break;
        }
        followingList = this.list(begin, limit);
      }
    }

    return stores;
  }

  @Override
  public void update(WxMpStoreBaseInfo request) throws WxErrorException {
    String url = API_BASE_URL + "/updatepoi";
    String response = this.wxMpService.post(url, request.toJson());
    WxError wxError = WxError.fromJson(response);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public List<String> listCategories() throws WxErrorException {
    String url = API_BASE_URL + "/getwxcategory";
    String response = this.wxMpService.get(url, null);
    WxError wxError = WxError.fromJson(response);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }

    return WxMpGsonBuilder.create().fromJson(
        new JsonParser().parse(response).getAsJsonObject().get("category_list"),
        new TypeToken<List<String>>(){}.getType());
  }

}
