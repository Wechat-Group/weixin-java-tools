package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.invoice.InvoiceScanTitleResult;

public interface WxMpCardInvoiceService {
  String INVOICE_SCANTITLE = "https://api.weixin.qq.com/card/invoice/scantitle";

  InvoiceScanTitleResult getInvoiceTitle(String scanText) throws WxErrorException;

  String getInvoiceTitleString(String scanText) throws WxErrorException;
}
