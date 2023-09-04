package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.xpay.*;
import me.chanjar.weixin.common.error.WxErrorException;

public interface WxMaXPayService {

  WxMaXPayQueryUserBalanceResponse queryUserBalance(WxMaXPayQueryUserBalanceRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  WxMaXPayCurrencyPayResponse currencyPay(WxMaXPayCurrencyPayRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  WxMaXPayQueryOrderResponse queryOrder(WxMaXPayQueryOrderRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  WxMaXPayCancelCurrencyPayResponse cancelCurrencyPay(WxMaXPayCancelCurrencyPayRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  boolean notifyProvideGoods(WxMaXPayNotifyProvideGoodsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  WxMaXPayPresentCurrencyResponse presentCurrency(WxMaXPayPresentCurrencyRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;


  WxMaXPayDownloadBillResponse downloadBill(WxMaXPayDownloadBillRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  WxMaXPayRefundOrderResponse refundOrder(WxMaXPayRefundOrderRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  WxMaXPayCreateWithdrawOrderResponse createWithdrawOrder(WxMaXPayCreateWithdrawOrderRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  WxMaXPayQueryWithdrawOrderResponse queryWithdrawOrder(WxMaXPayQueryWithdrawOrderRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  boolean startUploadGoods(WxMaXPayStartUploadGoodsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  WxMaXPayQueryUploadGoodsResponse queryUploadGoods(WxMaXPayQueryUploadGoodsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  boolean startPublishGoods(WxMaXPayStartPublishGoodsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

  WxMaXPayQueryPublishGoodsResponse queryPublishGoods(WxMaXPayQueryPublishGoodsRequest request, WxMaXPaySigParams sigParams) throws WxErrorException;

}
