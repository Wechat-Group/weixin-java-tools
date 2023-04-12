package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.*;

import com.google.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZoneId;
import me.chanjar.weixin.channel.api.WxChannelFundService;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.fund.AccountInfo;
import me.chanjar.weixin.channel.bean.fund.AccountInfoResponse;
import me.chanjar.weixin.channel.bean.fund.BalanceInfoResponse;
import me.chanjar.weixin.channel.bean.fund.FlowListResponse;
import me.chanjar.weixin.channel.bean.fund.FundsFlowResponse;
import me.chanjar.weixin.channel.bean.fund.FundsListParam;
import me.chanjar.weixin.channel.bean.fund.WithdrawDetailResponse;
import me.chanjar.weixin.channel.bean.fund.WithdrawListResponse;
import me.chanjar.weixin.channel.bean.fund.WithdrawSubmitResponse;
import me.chanjar.weixin.channel.bean.fund.bank.BankCityResponse;
import me.chanjar.weixin.channel.bean.fund.bank.BankInfoResponse;
import me.chanjar.weixin.channel.bean.fund.bank.BankListResponse;
import me.chanjar.weixin.channel.bean.fund.bank.BankProvinceResponse;
import me.chanjar.weixin.channel.bean.fund.bank.BranchInfoResponse;
import me.chanjar.weixin.channel.bean.fund.qrcode.QrCheckResponse;
import me.chanjar.weixin.channel.bean.fund.qrcode.QrCodeResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxChannelFundServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testGetBalance() throws WxErrorException {
    WxChannelFundService fundService = channelService.getFundService();
    BalanceInfoResponse response = fundService.getBalance();
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetBankAccount() throws WxErrorException {
    WxChannelFundService fundService = channelService.getFundService();
    AccountInfoResponse response = fundService.getBankAccount();
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetFundsFlowDetail() throws WxErrorException {
    WxChannelFundService fundService = channelService.getFundService();
    String flowId = "";
    FundsFlowResponse response = fundService.getFundsFlowDetail(flowId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testListFundsFlow() throws WxErrorException {
    WxChannelFundService fundService = channelService.getFundService();
    FundsListParam param = new FundsListParam();
    FlowListResponse response = fundService.listFundsFlow(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetWithdrawDetail() throws WxErrorException {
    WxChannelFundService fundService = channelService.getFundService();
    String withdrawId = "";
    WithdrawDetailResponse response = fundService.getWithdrawDetail(withdrawId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testListWithdraw() throws WxErrorException {
    WxChannelFundService fundService = channelService.getFundService();
    Integer pageNum = 1;
    Integer pageSize = 10;
    Long startTime = LocalDateTime.now().minusDays(7).atZone(ZoneId.systemDefault()).toEpochSecond();
    Long endTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
    WithdrawListResponse response = fundService.listWithdraw(pageNum, pageSize, startTime, endTime);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testSetBankAccount() throws WxErrorException {
    WxChannelFundService fundService = channelService.getFundService();
    AccountInfo accountInfo = new AccountInfo();
    // ...
    WxChannelBaseResponse response = fundService.setBankAccount(accountInfo);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testSubmitWithdraw() throws WxErrorException {
    WxChannelFundService fundService = channelService.getFundService();
    Integer amount = 1;
    String remark = "test";
    String bankMemo = "-";
    WithdrawSubmitResponse response = fundService.submitWithdraw(amount, remark, bankMemo);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetBankInfoByCardNo() throws WxErrorException {
    WxChannelFundService fundService = channelService.getFundService();
    String accountNumber = "";
    BankInfoResponse response = fundService.getBankInfoByCardNo(accountNumber);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testSearchBankList() throws WxErrorException {
    WxChannelFundService fundService = channelService.getFundService();
    Integer offset = 0;
    Integer limit = 20;
    String keywords = "";
    Integer bankType = 1;
    BankListResponse response = fundService.searchBankList(offset, limit, keywords, bankType);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testSearchCityList() throws WxErrorException {
    WxChannelFundService fundService = channelService.getFundService();
    String provinceCode = "0";
    BankCityResponse response = fundService.searchCityList(provinceCode);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetProvinceList() throws WxErrorException {
    WxChannelFundService fundService = channelService.getFundService();
    BankProvinceResponse response = fundService.getProvinceList();
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testSearchBranchList() throws WxErrorException {
    WxChannelFundService fundService = channelService.getFundService();
    String bankCode = "";
    String cityCode = "";
    Integer offset = 0;
    Integer limit = 20;
    BranchInfoResponse response = fundService.searchBranchList(bankCode, cityCode, offset, limit);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetQrCode() throws WxErrorException {
    WxChannelFundService fundService = channelService.getFundService();
    String qrcodeTicket = "";
    QrCodeResponse response = fundService.getQrCode(qrcodeTicket);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testCheckQrStatus() throws WxErrorException {
    WxChannelFundService fundService = channelService.getFundService();
    String qrcodeTicket = "";
    QrCheckResponse response = fundService.checkQrStatus(qrcodeTicket);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }
}
