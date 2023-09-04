package me.chanjar.weixin.channel.api;

/**
 * The interface Wx Channel service
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface WxChannelService extends BaseWxChannelService {

  /**
   * 基础接口服务
   *
   * @return 基础接口服务
   */
  WxChannelBasicService getBasicService();

  /**
   * 商品类目服务
   *
   * @return 商品类目服务
   */
  WxChannelCategoryService getCategoryService();

  /**
   * 品牌服务
   *
   * @return 品牌服务
   */
  WxChannelBrandService getBrandService();

  /**
   * 商品服务
   *
   * @return 商品服务
   */
  WxChannelProductService getProductService();

  /**
   * 仓库服务
   *
   * @return 仓库服务
   */
  WxChannelWarehouseService getWarehouseService();

  /**
   * 订单服务
   *
   * @return 订单服务
   */
  WxChannelOrderService getOrderService();

  /**
   * 售后服务
   *
   * @return 售后服务
   */
  WxChannelAfterSaleService getAfterSaleService();

  /**
   * 运费模板服务
   *
   * @return 运费模板服务
   */
  WxChannelFreightTemplateService getFreightTemplateService();

  /**
   * 地址服务
   *
   * @return 地址服务
   */
  WxChannelAddressService getAddressService();

  /**
   * 优惠券服务
   *
   * @return 优惠券服务
   */
  WxChannelCouponService getCouponService();

  /**
   * 分享员服务
   *
   * @return 分享员服务
   */
  WxChannelSharerService getSharerService();

  /**
   * 资金服务
   *
   * @return 资金服务
   */
  WxChannelFundService getFundService();

  /**
   * 优选联盟-团长合作达人管理服务
   *
   * @return 团长合作达人管理服务
   */
  WxLeagueWindowService getLeagueWindowService();

  /**
   * 优选联盟-团长服务
   *
   * @return 团长服务
   */
  WxLeagueSupplierService getLeagueSupplierService();

  /**
   * 优选联盟-达人服务
   *
   * @return 达人服务
   */
  WxLeaguePromoterService getLeaguePromoterService();

  /**
   * 优选联盟-商品服务
   *
   * @return 商品服务
   */
  WxLeagueProductService getLeagueProductService();

}
