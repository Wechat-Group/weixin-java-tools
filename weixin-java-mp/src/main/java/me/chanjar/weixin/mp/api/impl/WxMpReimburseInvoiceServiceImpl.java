package me.chanjar.weixin.mp.api.impl;

import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpReimburseInvoiceService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.invoice.reimburse.*;

import java.util.List;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Invoice.*;

/**
 * 电子发票报销方相关接口实现
 * 接口文档: https://developers.weixin.qq.com/doc/offiaccount/WeChat_Invoice/E_Invoice/Reimburser_API_List.html
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2021-03-23
 */
@AllArgsConstructor
public class WxMpReimburseInvoiceServiceImpl implements WxMpReimburseInvoiceService {

  private final WxMpService wxMpService;

  @Override
  public InvoiceInfoResponse getInvoiceInfo(InvoiceInfoRequest request) throws WxErrorException {
    return InvoiceInfoResponse.fromJson(this.wxMpService.post(GET_INVOICE_INFO,request.toJson()));
  }

  @Override
  public List<InvoiceInfoResponse> getInvoiceBatch(InvoiceBatchRequest request) throws WxErrorException {
    return InvoiceInfoResponse.toList(this.wxMpService.post(GET_INVOICE_BATCH,request.toJson()));
  }

  @Override
  public void updateInvoiceStatus(UpdateInvoiceStatusRequest request) throws WxErrorException {
    this.wxMpService.post(UPDATE_INVOICE_STATUS,request.toJson());
  }

  @Override
  public void updateStatusBatch(UpdateStatusBatchRequest request) throws WxErrorException {
    this.wxMpService.post(UPDATE_STATUS_BATCH,request.toJson());
  }
}
