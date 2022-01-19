package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.delivery.AbnormalConfirmRequest;
import cn.binarywang.wx.miniapp.bean.delivery.AbnormalConfirmResponse;
import cn.binarywang.wx.miniapp.bean.delivery.AddOrderRequest;
import cn.binarywang.wx.miniapp.bean.delivery.AddOrderResponse;
import cn.binarywang.wx.miniapp.bean.delivery.BindAccountResponse;
import cn.binarywang.wx.miniapp.bean.delivery.CancelOrderRequest;
import cn.binarywang.wx.miniapp.bean.delivery.CancelOrderResponse;
import cn.binarywang.wx.miniapp.bean.delivery.GetOrderRequest;
import cn.binarywang.wx.miniapp.bean.delivery.GetOrderResponse;
import cn.binarywang.wx.miniapp.bean.delivery.MockUpdateOrderRequest;
import cn.binarywang.wx.miniapp.bean.delivery.MockUpdateOrderResponse;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.math.BigDecimal;

/**
 * 微信小程序即时配送服务测试.
 *
 * @author Luo
 * @version 1.0
 * @date 2021-10-14 11:48
 */
@Guice(modules = ApiTestModule.class)
public class WxMaImmediateDeliveryServiceImplTest {

  /**
   * 对应配送公司的appKey.
   */
  private static final String SHOP_ID = "***";

  /**
   * 对应配送公司appSecret.
   */
  private static final String APP_SECRET = "****";

  /**
   * 商家门店编号.
   */
  private static final String SHOP_NO = "***";

  /**
   * 快递公司Id.
   */
  private static final String DELIVERY_ID = "SFTC";

  @Inject
  private WxMaService wxMaService;

  /**
   * 测试拉取已绑定账号接口.
   *
   * @throws WxErrorException 异常
   */
  @Test
  public void testGetBindAccount() throws WxErrorException {
    BindAccountResponse response = wxMaService.getWxMaImmediateDeliveryService().getBindAccount();
    System.out.println("response = " + response);
  }

  /**
   * 测试下配送单接口.
   *
   * @throws WxErrorException 异常
   */
  @Test
  public void testAddOrder() throws WxErrorException {
    AddOrderRequest request = new AddOrderRequest();
    // 下单用户的openid
    request.setOpenid("*****");
    // 微信平台字段，对应配送公司的appkey
    request.setShopId(SHOP_ID);
    // 对应配送公司appSecret
    request.setAppSecret(APP_SECRET);
    // 商家门店编号，在配送公司登记，如果只有一个门店，美团闪送必填, 值为店铺id
    // 商家对不同门店进行的编号，需要在配送公司系统有过登记，比如商家自己门店系统中有100个门店，编号是1-100，在顺丰同城的系统中有登记过这100个门店，且在顺丰同城登记的编号也是1-100，那么下单的时候传shop_no
    // =1，就是编号为1 的门店下的配送单
    request.setShopNo(SHOP_NO);
    // 配送公司Id
    request.setDeliveryId(DELIVERY_ID);
    // 唯一标识订单的 ID，由商户生成, 不超过 128 字节
    String shopOrderId = String.valueOf(System.currentTimeMillis());
    request.setShopOrderId(shopOrderId);

    // 订单信息
    AddOrderRequest.OrderInfo orderInfo = new AddOrderRequest.OrderInfo();
    orderInfo.setOrderTime(System.currentTimeMillis() / 1000L);
    request.setOrderInfo(orderInfo);

    // 发件人信息
    AddOrderRequest.Sender sender = new AddOrderRequest.Sender();
    sender.setCity("上海市").setAddress("***").setAddressDetail("****");
    sender.setName("***").setPhone("166****8829");
    sender.setLng(new BigDecimal("121.281379")).setLat(new BigDecimal("31.049363"));
    request.setSender(sender);

    // 收件人信息
    AddOrderRequest.Receiver receiver = new AddOrderRequest.Receiver().setCoordinateType(1);
    receiver.setCity("北京市").setAddress("海淀区").setAddressDetail("北京市海淀区学清嘉创大厦A座15层");
    receiver.setName("顺丰同城").setPhone("166****8829");
    receiver.setLng(new BigDecimal("116.359442")).setLat(new BigDecimal("40.020407"));
    request.setReceiver(receiver);

    // 商品信息
    AddOrderRequest.Cargo cargo = new AddOrderRequest.Cargo();
    cargo.setCargoFirstClass("电商").setCargoSecondClass("线上商城");
    cargo.setGoodsHeight(BigDecimal.valueOf(1)).setGoodsLength(BigDecimal.valueOf(3));
    cargo.setGoodsValue(BigDecimal.valueOf(5)).setGoodsWeight(BigDecimal.valueOf(1)).setGoodsWidth(BigDecimal.valueOf(2));
    // 商品列表
    AddOrderRequest.Cargo.GoodsDetail goodsDetail = new AddOrderRequest.Cargo.GoodsDetail();
    AddOrderRequest.Cargo.GoodsDetail.Goods goods1 = new AddOrderRequest.Cargo.GoodsDetail.Goods();
    goods1.setGoodCount(1).setGoodName("水果").setGoodPrice(new BigDecimal(10));
    AddOrderRequest.Cargo.GoodsDetail.Goods goods2 = new AddOrderRequest.Cargo.GoodsDetail.Goods();
    goods2.setGoodCount(2).setGoodName("蔬菜").setGoodPrice(new BigDecimal(20));
    goodsDetail.setGoods(Lists.newArrayList(goods1, goods2));
    cargo.setGoodsDetail(goodsDetail);
    request.setCargo(cargo);

    // 店铺信息
    AddOrderRequest.Shop shop = new AddOrderRequest.Shop();
    int sum =
      request.getCargo().getGoodsDetail().getGoods().stream().mapToInt(AddOrderRequest.Cargo.GoodsDetail.Goods::getGoodCount).sum();
    shop.setGoodsCount(sum).setGoodsName("商品");
    shop.setImgUrl("https://").setWxaPath("pages/index/index");
    request.setShop(shop);

    AddOrderResponse response = wxMaService.getWxMaImmediateDeliveryService().addOrder(request);
    System.out.println("response = " + response);

  }

  /**
   * 测试拉取配送单信息接口.
   *
   * @throws WxErrorException 异常
   */
  @Test
  public void testGetOrder() throws WxErrorException {
    GetOrderRequest request = new GetOrderRequest();
    request.setShopId(SHOP_ID).setShopNo(SHOP_NO).setAppSecret(APP_SECRET);
    request.setShopOrderId("1561399675737608193");
    GetOrderResponse response = wxMaService.getWxMaImmediateDeliveryService().getOrder(request);
    System.out.println("response = " + response);
  }

  /**
   * 测试取消配送单信息接口.
   *
   * @throws WxErrorException 异常
   */
  @Test
  public void testCancelOrder() throws WxErrorException {
    CancelOrderRequest request = new CancelOrderRequest();
    request.setShopId(SHOP_ID).setShopNo(SHOP_NO).setAppSecret(APP_SECRET);
    request.setDeliveryId(DELIVERY_ID);
    request.setCancelReasonId(1);
    request.setShopOrderId("1560365275348471809");
    request.setWaybillId("3427365636312065025");
    CancelOrderResponse response = wxMaService.getWxMaImmediateDeliveryService().cancelOrder(request);
    System.out.println("response = " + response);
  }

  /**
   * 测试异常件退回商家商家确认收货接口.
   *
   * @throws WxErrorException 异常
   */
  @Test
  public void testAbnormalConfirm() throws WxErrorException {
    AbnormalConfirmRequest request = new AbnormalConfirmRequest();
    request.setShopId(SHOP_ID).setShopNo(SHOP_NO).setAppSecret(APP_SECRET);
    request.setDeliveryId(DELIVERY_ID);
    request.setShopOrderId("1561399675737608193");
    request.setWaybillId("3427882855372591617");
    request.setRemark("测试签收异常订单");
    AbnormalConfirmResponse response = wxMaService.getWxMaImmediateDeliveryService().abnormalConfirm(request);
    System.out.println("response = " + response);
  }

  /**
   * 测试模拟配送公司更新配送单状态接口.
   *
   * @throws WxErrorException 异常
   */
  @Test
  public void testMockUpdateOrder() throws WxErrorException {
    // 请求参数
    MockUpdateOrderRequest request = new MockUpdateOrderRequest();
    request.setActionTime(System.currentTimeMillis() / 1000L);
    request.setOrderStatus(102);
    request.setShopOrderId("");
    MockUpdateOrderResponse response = wxMaService.getWxMaImmediateDeliveryService().mockUpdateOrder(request);
    System.out.println("response = " + response);
  }

}
