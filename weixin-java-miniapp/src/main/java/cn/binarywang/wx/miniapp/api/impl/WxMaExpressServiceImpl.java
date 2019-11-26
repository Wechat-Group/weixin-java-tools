package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaExpressService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.express.WxMaExpressAccount;
import cn.binarywang.wx.miniapp.bean.express.WxMaExpressBindAccountRequest;
import cn.binarywang.wx.miniapp.bean.express.WxMaExpressDelivery;
import cn.binarywang.wx.miniapp.bean.express.WxMaExpressPrinterUpdateRequest;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.List;

/**
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2019-11-26
 */
@AllArgsConstructor
public class WxMaExpressServiceImpl implements WxMaExpressService {

  private WxMaService wxMaService;

  @Override
  public List<WxMaExpressDelivery> getAllDelivery() throws WxErrorException {
    String responseContent = this.wxMaService.get(ALL_DELIVERY_URL, null);
    return WxMaExpressDelivery.fromJson(responseContent);
  }

  @Override
  public List<WxMaExpressAccount> getAllAccount() throws WxErrorException {
    String responseContent = this.wxMaService.get(ALL_ACCOUNT_URL, null);
    return WxMaExpressAccount.fromJson(responseContent);
  }

  @Override
  public void bindAccount(WxMaExpressBindAccountRequest wxMaExpressBindAccountRequest) throws WxErrorException {
    this.wxMaService.post(BIND_ACCOUNT_URL,wxMaExpressBindAccountRequest.toJson());
  }

  @Override
  public void updatePrinter(WxMaExpressPrinterUpdateRequest wxMaExpressPrinterUpdateRequest) throws WxErrorException {
    this.wxMaService.post(UPDATE_PRINTER_URL,wxMaExpressPrinterUpdateRequest.toJson());
  }
}
