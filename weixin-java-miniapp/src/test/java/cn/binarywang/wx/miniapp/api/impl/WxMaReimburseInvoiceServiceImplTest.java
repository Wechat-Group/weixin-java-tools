package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.invoice.reimburse.*;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class WxMaReimburseInvoiceServiceImplTest {

  @Inject
  private WxMaService wxMaService;

  @Test
  public void testGetInvoiceInfo() throws WxErrorException {

    InvoiceInfoRequest request = InvoiceInfoRequest.builder()
      .cardId("**********************")
      .encryptCode("**********************")
      .build();

    InvoiceInfoResponse response = this.wxMaService.getReimburseInvoiceService().getInvoiceInfo(request);

    log.info("response: {}", new GsonBuilder().create().toJson(response));
  }

  @Test
  public void testGetInvoiceBatch() throws WxErrorException {

    List<InvoiceInfoRequest> invoices = new ArrayList<>();
    InvoiceInfoRequest r = InvoiceInfoRequest.builder()
      .cardId("**********************")
      .encryptCode("********************************************")
      .build();
    invoices.add(r);
    r = InvoiceInfoRequest.builder()
      .cardId("**********************")
      .encryptCode("********************************************")
      .build();
    invoices.add(r);

    InvoiceBatchRequest request = InvoiceBatchRequest.builder().itemList(invoices).build();

    List<InvoiceInfoResponse> responses = this.wxMaService.getReimburseInvoiceService().getInvoiceBatch(request);
    log.info("responses: {}",new GsonBuilder().create().toJson(responses));
  }

  @Test
  public void testUpdateInvoiceStatus() throws WxErrorException {
    UpdateInvoiceStatusRequest request = UpdateInvoiceStatusRequest.builder()
      .cardId("**********************")
      .encryptCode("********************************************")
      .reimburseStatus("INVOICE_REIMBURSE_INIT")
      .build();

    this.wxMaService.getReimburseInvoiceService().updateInvoiceStatus(request);
  }

  @Test
  public void testUpdateStatusBatch() throws WxErrorException {

    List<InvoiceInfoRequest> invoices = new ArrayList<>();
    InvoiceInfoRequest r = InvoiceInfoRequest.builder()
      .cardId("**************")
      .encryptCode("**************")
      .build();
    invoices.add(r);

    UpdateStatusBatchRequest request = UpdateStatusBatchRequest.builder()
      .invoiceList(invoices)
      .openid("**************")
      .reimburseStatus("INVOICE_REIMBURSE_LOCK")
      .build();

    this.wxMaService.getReimburseInvoiceService().updateStatusBatch(request);
  }
}
