package me.chanjar.weixin.mp.api;


import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.invoice.merchant.*;

/**
 * 商户电子发票相关的接口
 * <p>
 * 重要!!!, 根据不同开票平台, 以下错误码可能开票成功(开票,冲红), 内部暂时未处理:
 * 73105: 开票平台开票中，请使用相同的发票请求流水号重试开票
 * 73107: 发票请求流水正在被处理，请通过查询接口获取结果
 * 73100: 开票平台错误
 * <p>
 * 流程文档: https://developers.weixin.qq.com/doc/offiaccount/WeChat_Invoice/E_Invoice/Vendor_and_Invoicing_Platform_Mode_Instruction.html
 * 接口文档: https://developers.weixin.qq.com/doc/offiaccount/WeChat_Invoice/E_Invoice/Vendor_API_List.html
 */
public interface WxMpMerchantInvoiceService {

  /**
   * 获取开票授权页链接
   */
  InvoiceAuthPageResult getAuthPageUrl(InvoiceAuthPageRequest params) throws WxErrorException;

  /**
   * 获得用户授权数据
   */
  InvoiceAuthDataResult getAuthData(InvoiceAuthDataRequest params) throws WxErrorException;

  /**
   * 拒绝开票
   * <p>
   * 场景: 用户授权填写数据无效
   * 结果: 用户会收到一条开票失败提示
   */
  void rejectInvoice(InvoiceRejectRequest params) throws WxErrorException;

  /**
   * 开具电子发票
   */
  void makeOutInvoice(MakeOutInvoiceRequest params) throws WxErrorException;

  /**
   * 发票冲红
   */
  void clearOutInvoice(ClearOutInvoiceRequest params) throws WxErrorException;

  /**
   * 查询发票信息
   *
   * @param fpqqlsh 发票请求流水号
   * @param nsrsbh  纳税人识别号
   */
  InvoiceResult queryInvoiceInfo(String fpqqlsh, String nsrsbh) throws WxErrorException;

  /**
   * 设置商户联系方式, 获取授权链接前需要设置商户联系信息
   */
  void setMerchantContactInfo(MerchantContactInfo contact) throws WxErrorException;

  /**
   * 获取商户联系方式
   */
  MerchantContactInfo getMerchantContactInfo() throws WxErrorException;

  /**
   * 配置授权页面字段
   */
  void setAuthPageSetting(InvoiceAuthPageSetting authPageSetting) throws WxErrorException;

  /**
   * 获取授权页面配置
   */
  InvoiceAuthPageSetting getAuthPageSetting() throws WxErrorException;

  /**
   * 设置商户开票平台信息
   */
  void setMerchantInvoicePlatform(MerchantInvoicePlatformInfo merchantInvoicePlatformInfo) throws WxErrorException;

  /**
   * 获取商户开票平台信息
   */
  MerchantInvoicePlatformInfo getMerchantInvoicePlatform(MerchantInvoicePlatformInfo merchantInvoicePlatformInfo) throws WxErrorException;
}
