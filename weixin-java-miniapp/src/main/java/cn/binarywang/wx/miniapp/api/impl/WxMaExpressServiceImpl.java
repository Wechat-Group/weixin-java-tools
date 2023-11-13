package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaExpressService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.express.WxMaExpressAccount;
import cn.binarywang.wx.miniapp.bean.express.WxMaExpressDelivery;
import cn.binarywang.wx.miniapp.bean.express.WxMaExpressPath;
import cn.binarywang.wx.miniapp.bean.express.WxMaExpressPrinter;
import cn.binarywang.wx.miniapp.bean.express.request.*;
import cn.binarywang.wx.miniapp.bean.express.result.WxMaExpressInfoResult;
import cn.binarywang.wx.miniapp.bean.express.result.WxMaExpressOrderInfoResult;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Express.*;

/**
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2019-11-26
 */
@RequiredArgsConstructor
public class WxMaExpressServiceImpl implements WxMaExpressService {
  private final WxMaService service;

  @Override
  public List<WxMaExpressDelivery> getAllDelivery() throws WxErrorException {
    String responseContent = this.service.get(ALL_DELIVERY_URL, null);
    return WxMaExpressDelivery.fromJson(responseContent);
  }

  @Override
  public List<WxMaExpressAccount> getAllAccount() throws WxErrorException {
    String responseContent = this.service.get(ALL_ACCOUNT_URL, null);
    return WxMaExpressAccount.fromJsonList(responseContent);
  }

  @Override
  public WxMaExpressInfoResult bindAccount(WxMaExpressBindAccountRequest wxMaExpressBindAccountRequest) throws WxErrorException {
    return WxMaExpressInfoResult.fromJson(this.service.post(BIND_ACCOUNT_URL, wxMaExpressBindAccountRequest.toJson()));
  }

  @Override
  public Integer getQuota(WxMaExpressBindAccountRequest wxMaExpressBindAccountRequest) throws WxErrorException {
    String responseContent = this.service.post(GET_QUOTA_URL, wxMaExpressBindAccountRequest.toJson());
    WxMaExpressAccount account = WxMaExpressAccount.fromJson(responseContent);
    return account.getQuotaNum();
  }

  @Override
  public void updatePrinter(WxMaExpressPrinterUpdateRequest wxMaExpressPrinterUpdateRequest) throws WxErrorException {
    this.service.post(UPDATE_PRINTER_URL, wxMaExpressPrinterUpdateRequest.toJson());
  }

  @Override
  public WxMaExpressPrinter getPrinter() throws WxErrorException {
    String responseContent = this.service.get(GET_PRINTER_URL, null);
    return WxMaExpressPrinter.fromJson(responseContent);
  }

  @Override
  public WxMaExpressOrderInfoResult addOrder(WxMaExpressAddOrderRequest wxMaExpressAddOrderRequest) throws WxErrorException {
    String responseContent = this.service.post(ADD_ORDER_URL, wxMaExpressAddOrderRequest.toJson());
    return WxMaExpressOrderInfoResult.fromJson(responseContent);
  }

  @Override
  public List<WxMaExpressOrderInfoResult> batchGetOrder(List<WxMaExpressGetOrderRequest> requests) throws WxErrorException {
    Map<String, Object> param = new HashMap<>(1);
    param.put("order_list", requests);
    String responseContent = this.service.post(BATCH_GET_ORDER_URL, WxMaGsonBuilder.create().toJson(param));
    return WxMaExpressOrderInfoResult.toList(responseContent);
  }

  @Override
  public void cancelOrder(WxMaExpressGetOrderRequest wxMaExpressGetOrderRequest) throws WxErrorException {
    this.service.post(CANCEL_ORDER_URL, wxMaExpressGetOrderRequest.toJson());
  }

  @Override
  public WxMaExpressOrderInfoResult getOrder(WxMaExpressGetOrderRequest wxMaExpressGetOrderRequest) throws WxErrorException {
    String responseContent = this.service.post(GET_ORDER_URL, wxMaExpressGetOrderRequest.toJson());
    return WxMaExpressOrderInfoResult.fromJson(responseContent);
  }

  @Override
  public WxMaExpressPath getPath(WxMaExpressGetOrderRequest wxMaExpressGetOrderRequest) throws WxErrorException {
    String responseContent = this.service.post(GET_PATH_URL, wxMaExpressGetOrderRequest.toJson());
    return WxMaExpressPath.fromJson(responseContent);
  }

  @Override
  public void testUpdateOrder(WxMaExpressTestUpdateOrderRequest wxMaExpressTestUpdateOrderRequest) throws WxErrorException {
    this.service.post(TEST_UPDATE_ORDER_URL, wxMaExpressTestUpdateOrderRequest.toJson());
  }
}
