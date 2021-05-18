package me.chanjar.weixin.mp.api.impl;

import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.bean.invoice.reimburse.*;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Test(groups = "invoiceAPI")
@Guice(modules = ApiTestModule.class)
public class WxMpReimburseInvoiceServiceImplTest {

  @Inject
  protected WxMpService wxService;


  public void getInvoiceInfo() throws WxErrorException {
    InvoiceInfoRequest request = InvoiceInfoRequest.builder()
      .cardId("pnAsy0sHQukID3E8d2IUdh2DbzZ4")
      .encryptCode("O/mPnGTpBu22a1szmK2ogzhFPBh9eYzv2p70L8yzyynlTOEE9fSC4PXvOGuLIWfqZQXA0yBPVcbELCLySWjiLH0RYjMqE4S2bekki6Z2VUjWHGp+shbOkYZ4y9zR4SpGVT6Dyha0ezDMVw6dFMatoA==")
      .build();

    InvoiceInfoResponse response = this.wxService.getReimburseInvoiceService().getInvoiceInfo(request);

    log.info("response: {}", new GsonBuilder().create().toJson(response));
  }


  public void getInvoiceBatch() throws WxErrorException {
    List<InvoiceInfoRequest> invoices = new ArrayList<>();
    InvoiceInfoRequest r = InvoiceInfoRequest.builder()
      .cardId("pnAsy0sHQukID3E8d2IUdh2DbzZ4")
      .encryptCode("O/mPnGTpBu22a1szmK2ogzhFPBh9eYzv2p70L8yzyynlTOEE9fSC4PXvOGuLIWfqZQXA0yBPVcbELCLySWjiLH0RYjMqE4S2bekki6Z2VUjWHGp+shbOkYZ4y9zR4SpGVT6Dyha0ezDMVw6dFMatoA==")
      .build();
    invoices.add(r);
    r = InvoiceInfoRequest.builder()
      .cardId("pnAsy0sHQukID3E8d2IUdh2DbzZ4")
      .encryptCode("O/mPnGTpBu22a1szmK2ogzhFPBh9eYzv2p70L8yzyynlTOEE9fSC4PXvOGuLIWfqd+8BRcn/yz1GmRwW4LAccaL/dRsSc9RWXektgTHKnoHWHGp+shbOkYZ4y9zR4SpGVT6Dyha0ezDMVw6dFMatoA==")
      .build();
    invoices.add(r);

    InvoiceBatchRequest request = InvoiceBatchRequest.builder().itemList(invoices).build();

    List<InvoiceInfoResponse> responses = this.wxService.getReimburseInvoiceService().getInvoiceBatch(request);
    log.info("responses: {}",new GsonBuilder().create().toJson(responses));
  }


  public void updateInvoiceStatus() throws WxErrorException {
    UpdateInvoiceStatusRequest request = UpdateInvoiceStatusRequest.builder()
      .cardId("**************")
      .encryptCode("**************")
      .reimburseStatus("INVOICE_REIMBURSE_INIT")
      .build();

    this.wxService.getReimburseInvoiceService().updateInvoiceStatus(request);
  }

  public void updateStatusBatch() throws WxErrorException {
    List<InvoiceInfoRequest> invoices = new ArrayList<>();
    InvoiceInfoRequest r = InvoiceInfoRequest.builder()
      .cardId("**************")
      .encryptCode("**************")
      .build();
    invoices.add(r);

    UpdateStatusBatchRequest request = UpdateStatusBatchRequest.builder()
      .invoiceList(invoices)
      .openid("**************")
      .reimburseStatus("INVOICE_REIMBURSE_INIT")
      .build();

    this.wxService.getReimburseInvoiceService().updateStatusBatch(request);
  }

}
