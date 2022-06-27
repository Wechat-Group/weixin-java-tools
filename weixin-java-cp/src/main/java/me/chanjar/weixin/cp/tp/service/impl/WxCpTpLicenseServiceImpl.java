package me.chanjar.weixin.cp.tp.service.impl;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.license.WxCpTpLicenseActiveAccount;
import me.chanjar.weixin.cp.bean.license.WxCpTpLicenseTransfer;
import me.chanjar.weixin.cp.bean.license.account.*;
import me.chanjar.weixin.cp.bean.license.order.*;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.tp.service.WxCpTpLicenseService;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;

import java.util.*;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.License.*;

/**
 * @author Totoro
 * @date 2022/6/27 11:03
 */
@RequiredArgsConstructor
public class WxCpTpLicenseServiceImpl implements WxCpTpLicenseService {

  private final WxCpTpService mainService;

  @Override
  public WxCpTpLicenseCreateOrderResp createNewOrder(WxCpTpLicenseNewOrderRequest licenseNewOrderRequest) throws WxErrorException {
    String resultText = mainService.post(getWxCpTpConfigStorage().getApiUrl(CREATE_NEW_ORDER) +
      getProviderAccessToken(), licenseNewOrderRequest.toJson());
    return WxCpTpLicenseCreateOrderResp.fromJson(resultText);
  }


  @Override
  public WxCpTpLicenseRenewOrderJobResp createRenewOrderJob(WxCpTpLicenseRenewOrderJobRequest licenseRenewOrderJobRequest) throws WxErrorException {
    String resultText = mainService.post(getWxCpTpConfigStorage().getApiUrl(CREATE_RENEW_ORDER_JOB) +
      getProviderAccessToken(), licenseRenewOrderJobRequest.toJson());
    return WxCpTpLicenseRenewOrderJobResp.fromJson(resultText);
  }


  @Override
  public WxCpTpLicenseCreateOrderResp submitRenewOrder(WxCpTpLicenseRenewOrderRequest licenseRenewOrderRequest) throws WxErrorException {
    String resultText = mainService.post(getWxCpTpConfigStorage().getApiUrl(SUBMIT_ORDER_JOB) +
      getProviderAccessToken(), licenseRenewOrderRequest.toJson());
    return WxCpTpLicenseCreateOrderResp.fromJson(resultText);
  }


  @Override
  public WxCpTpLicenseOrderListResp getOrderList(String corpId, Date startTime, Date endTime, String cursor, int limit) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("corpid", corpId);
    jsonObject.addProperty("cursor", cursor);
    jsonObject.addProperty("limit", limit);
    if(startTime != null) {
      jsonObject.addProperty("start_time", startTime.getTime() / 1000);
    }
    if(endTime != null) {
      jsonObject.addProperty("end_time", endTime.getTime() / 1000);
    }
    String resultText = mainService.post(getWxCpTpConfigStorage().getApiUrl(LIST_ORDER) +
      getProviderAccessToken(), jsonObject.toString());
    return WxCpTpLicenseOrderListResp.fromJson(resultText);
  }


  @Override
  public WxCpTpLicenseOrderInfoResp getOrderInfo(String orderId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("order_id", orderId);
    String resultText = mainService.post(getWxCpTpConfigStorage().getApiUrl(GET_ORDER) +
      getProviderAccessToken(), jsonObject.toString());
    return WxCpTpLicenseOrderInfoResp.fromJson(resultText);
  }

  @Override
  public WxCpTpLicenseOrderAccountListResp getOrderAccountList(String orderId, int limit, String cursor) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("order_id", orderId);
    jsonObject.addProperty("cursor", cursor);
    jsonObject.addProperty("limit", limit);
    String resultText = mainService.post(getWxCpTpConfigStorage().getApiUrl(LIST_ORDER_ACCOUNT) +
      getProviderAccessToken(), jsonObject.toString());
    return WxCpTpLicenseOrderAccountListResp.fromJson(resultText);
  }

  @Override
  public WxCpBaseResp activeCode(String code, String corpId, String userId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("active_code", code);
    jsonObject.addProperty("corpid", corpId);
    jsonObject.addProperty("userid", userId);
    String resultText = mainService.post(getWxCpTpConfigStorage().getApiUrl(ACTIVE_ACCOUNT) +
      getProviderAccessToken(), jsonObject.toString());
    return WxCpBaseResp.fromJson(resultText);
  }

  @Override
  public WxCpTpLicenseBatchActiveResultResp batchActiveCode(String corpId, List<WxCpTpLicenseActiveAccount> activeAccountList) throws WxErrorException {
    Map<String, Object> map = new HashMap<>(2);
    map.put("corpid", corpId);
    map.put("active_list", activeAccountList);
    GsonBuilder gsonBuilder = new GsonBuilder();
    String resultText = mainService.post(getWxCpTpConfigStorage().getApiUrl(BATCH_ACTIVE_ACCOUNT) +
      getProviderAccessToken(), gsonBuilder.create().toJson(map));
    return WxCpTpLicenseBatchActiveResultResp.fromJson(resultText);
  }

  @Override
  public WxCpTpLicenseCodeInfoResp getActiveInfoByCode(String code, String corpId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("active_code", code);
    jsonObject.addProperty("corpid", corpId);
    String resultText = mainService.post(getWxCpTpConfigStorage().getApiUrl(GET_ACTIVE_INFO_BY_CODE) +
      getProviderAccessToken(), jsonObject.toString());
    return WxCpTpLicenseCodeInfoResp.fromJson(resultText);
  }

  @Override
  public WxCpTpLicenseBatchCodeInfoResp batchGetActiveInfoByCode(Collection<String> codes, String corpId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    JsonArray list = new JsonArray();
    for (String code : codes) {
      list.add(new JsonPrimitive(code));
    }
    jsonObject.add("active_code_list", list);
    jsonObject.addProperty("corpid", corpId);
    String resultText = mainService.post(getWxCpTpConfigStorage().getApiUrl(BATCH_GET_ACTIVE_INFO_BY_CODE) +
      getProviderAccessToken(), jsonObject.toString());
    return WxCpTpLicenseBatchCodeInfoResp.fromJson(resultText);
  }

  @Override
  public WxCpTpLicenseCorpAccountListResp getCorpAccountList(String corpId, int limit, String cursor) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("corpid", corpId);
    jsonObject.addProperty("cursor", cursor);
    jsonObject.addProperty("limit", limit);
    String resultText = mainService.post(getWxCpTpConfigStorage().getApiUrl(LIST_ACTIVED_ACCOUNT) +
      getProviderAccessToken(), jsonObject.toString());
    return WxCpTpLicenseCorpAccountListResp.fromJson(resultText);
  }

  @Override
  public WxCpTpLicenseActiveInfoByUserResp getActiveInfoByUser(String corpId, String userId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("corpid", corpId);
    jsonObject.addProperty("userid", userId);
    String resultText = mainService.post(getWxCpTpConfigStorage().getApiUrl(GET_ACTIVE_INFO_BY_USER) +
      getProviderAccessToken(), jsonObject.toString());
    return WxCpTpLicenseActiveInfoByUserResp.fromJson(resultText);
  }

  @Override
  public WxCpTpLicenseBatchTransferResp batchTransferLicense(String corpId, List<WxCpTpLicenseTransfer> transferList) throws WxErrorException {
    Map<String, Object> map = new HashMap<>(2);
    map.put("corpid", corpId);
    map.put("transfer_list", transferList);
    GsonBuilder gsonBuilder = new GsonBuilder();
    String resultText = mainService.post(getWxCpTpConfigStorage().getApiUrl(BATCH_TRANSFER_LICENSE) +
      getProviderAccessToken(), gsonBuilder.create().toJson(map));
    return WxCpTpLicenseBatchTransferResp.fromJson(resultText);
  }


  /**
   * 获取服务商token的拼接参数
   * @return url
   * @throws WxErrorException /
   */
  private String getProviderAccessToken() throws WxErrorException {
    return "?provider_access_token=" + mainService.getWxCpProviderToken();
  }


  /**
   * 获取tp参数配置
   * @return config
   */
  private WxCpTpConfigStorage getWxCpTpConfigStorage() {
    return mainService.getWxCpTpConfigStorage();
  }




}
