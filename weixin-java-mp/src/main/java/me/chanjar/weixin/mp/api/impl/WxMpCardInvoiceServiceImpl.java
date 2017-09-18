package me.chanjar.weixin.mp.api.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.mp.api.WxMpCardInvoiceService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.invoice.InvoiceScanTitleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WxMpCardInvoiceServiceImpl implements WxMpCardInvoiceService{

  private final Logger log = LoggerFactory.getLogger(WxMpCardServiceImpl.class);

  private WxMpService wxMpService;

  public WxMpCardInvoiceServiceImpl(WxMpService wxMpService) {
    this.wxMpService = wxMpService;
  }

  @Override
  public InvoiceScanTitleResult getInvoiceTitle(String scanText) throws WxErrorException {
    String resultJsonString = getInvoiceTitleString(scanText);
    return InvoiceScanTitleResult.fromJson(resultJsonString);
  }

  @Override
  public String getInvoiceTitleString(String scanText) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("scan_text", scanText);
    String resultJsonString = wxMpService.post(INVOICE_SCANTITLE, jsonObject.toString());
    return resultJsonString;
  }
}
