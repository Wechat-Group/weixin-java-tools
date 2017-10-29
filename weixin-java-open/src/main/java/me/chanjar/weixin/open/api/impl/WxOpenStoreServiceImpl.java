package me.chanjar.weixin.open.api.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.BeanUtils;
import me.chanjar.weixin.mp.bean.store.WxMpStoreBaseInfo;
import me.chanjar.weixin.mp.bean.store.WxMpStoreInfo;
import me.chanjar.weixin.mp.bean.store.WxMpStoreListResult;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.api.WxOpenStoreService;

import java.util.List;

/**
 * Created by Binary Wang on 2016/9/26.
 *
 * @author binarywang (https://github.com/binarywang)
 */
public class WxOpenStoreServiceImpl implements WxOpenStoreService {
  private static final String API_BASE_URL = "http://api.weixin.qq.com/cgi-bin/poi";

  private WxOpenService wxOpenService;

  public WxOpenStoreServiceImpl(WxOpenService wxOpenService) {
    this.wxOpenService = wxOpenService;
  }

  @Override
  public void add(String appid, WxMpStoreBaseInfo request) throws WxErrorException {
    BeanUtils.checkRequiredFields(request);

    String url = API_BASE_URL + "/addpoi";
    String response = this.wxOpenService.post(appid, url, request.toJson());
    WxError wxError = WxError.fromJson(response);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public WxMpStoreBaseInfo get(String appid, String poiId) throws WxErrorException {
    String url = API_BASE_URL + "/getpoi";
    JsonObject paramObject = new JsonObject();
    paramObject.addProperty("poi_id", poiId);
    String response = this.wxOpenService.post(appid, url, paramObject.toString());
    WxError wxError = WxError.fromJson(response);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
    return WxMpStoreBaseInfo.fromJson(new JsonParser().parse(response).getAsJsonObject()
      .get("business").getAsJsonObject().get("base_info").toString());
  }

  @Override
  public void delete(String appid, String poiId) throws WxErrorException {
    String url = API_BASE_URL + "/delpoi";
    JsonObject paramObject = new JsonObject();
    paramObject.addProperty("poi_id", poiId);
    String response = this.wxOpenService.post(appid, url, paramObject.toString());
    WxError wxError = WxError.fromJson(response);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public WxMpStoreListResult list(String appid, int begin, int limit)
    throws WxErrorException {
    String url = API_BASE_URL + "/getpoilist";
    JsonObject params = new JsonObject();
    params.addProperty("begin", begin);
    params.addProperty("limit", limit);
    String response = this.wxOpenService.post(appid, url, params.toString());

    WxError wxError = WxError.fromJson(response);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }

    return WxMpStoreListResult.fromJson(response);
  }

  @Override
  public List<WxMpStoreInfo> listAll(String appid) throws WxErrorException {
    int limit = 50;
    WxMpStoreListResult list = this.list(appid, 0, limit);
    List<WxMpStoreInfo> stores = list.getBusinessList();
    if (list.getTotalCount() > limit) {
      int begin = limit;
      WxMpStoreListResult followingList = this.list(appid, begin, limit);
      while (followingList.getBusinessList().size() > 0) {
        stores.addAll(followingList.getBusinessList());
        begin += limit;
        if (begin >= list.getTotalCount()) {
          break;
        }
        followingList = this.list(appid, begin, limit);
      }
    }

    return stores;
  }

  @Override
  public void update(String appid, WxMpStoreBaseInfo request) throws WxErrorException {
    String url = API_BASE_URL + "/updatepoi";
    String response = this.wxOpenService.post(appid, url, request.toJson());
    WxError wxError = WxError.fromJson(response);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public List<String> listCategories(String appid) throws WxErrorException {
    String url = API_BASE_URL + "/getwxcategory";
    String response = this.wxOpenService.get(appid, url, null);
    WxError wxError = WxError.fromJson(response);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }

    return WxMpGsonBuilder.create().fromJson(
      new JsonParser().parse(response).getAsJsonObject().get("category_list"),
      new TypeToken<List<String>>() {
      }.getType());
  }

}
