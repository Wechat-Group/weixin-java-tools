package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaReimburseInvoiceService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.invoice.reimburse.*;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.List;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Invoice.*;

/**
 * 电子发票报销方相关接口实现
 * 接口文档: https://developers.weixin.qq.com/doc/offiaccount/WeChat_Invoice/E_Invoice/Reimburser_API_List.html
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2021-06-10
 */
@AllArgsConstructor
public class WxMaReimburseInvoiceServiceImpl implements WxMaReimburseInvoiceService {

  private final WxMaService wxMaService;

  @Override
  public InvoiceInfoResponse getInvoiceInfo(InvoiceInfoRequest request) throws WxErrorException {
    return InvoiceInfoResponse.fromJson(this.wxMaService.post(GET_INVOICE_INFO,request.toJson()));
  }

  @Override
  public List<InvoiceInfoResponse> getInvoiceBatch(InvoiceBatchRequest request) throws WxErrorException {
    return InvoiceInfoResponse.toList(this.wxMaService.post(GET_INVOICE_BATCH,request.toJson()));
  }

  @Override
  public void updateInvoiceStatus(UpdateInvoiceStatusRequest request) throws WxErrorException {
    this.wxMaService.post(UPDATE_INVOICE_STATUS,request.toJson());
  }

  @Override
  public void updateStatusBatch(UpdateStatusBatchRequest request) throws WxErrorException {
    this.wxMaService.post(UPDATE_STATUS_BATCH,request.toJson());
  }

}
