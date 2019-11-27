package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaExpressService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.express.*;
import cn.binarywang.wx.miniapp.bean.express.request.WxMaExpressAddOrderRequest;
import cn.binarywang.wx.miniapp.bean.express.request.WxMaExpressBindAccountRequest;
import cn.binarywang.wx.miniapp.bean.express.request.WxMaExpressPrinterUpdateRequest;
import cn.binarywang.wx.miniapp.bean.express.result.WxMaExpressAddOrderResult;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import cn.binarywang.wx.miniapp.test.ApiTestModule;
import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.inject.Inject;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


@Guice(modules = ApiTestModule.class)
public class WxMaExpressServiceImplTest {

  @Inject
  private WxMaService wxMaService;

  @Test
  public void testGetAllDelivery() throws WxErrorException {
    final WxMaExpressService service = wxMaService.getExpressService();
    List<WxMaExpressDelivery> list = service.getAllDelivery();
    System.out.println(WxMaGsonBuilder.create().toJson(list));
  }

  @Test
  public void testGetAllAccount() throws WxErrorException {
    final WxMaExpressService service = wxMaService.getExpressService();
    List<WxMaExpressAccount> list = service.getAllAccount();
    System.out.println(WxMaGsonBuilder.create().toJson(list));
  }

  @Test
  public void testBindAccount() throws WxErrorException {
    final WxMaExpressService service = wxMaService.getExpressService();
    WxMaExpressBindAccountRequest request = WxMaExpressBindAccountRequest.builder()
      .deliveryId("YUNDA")
      .bizId("******")
      .password("*********")
      .remarkContent("####")
      .type(WxMaConstants.BindAccountType.BIND)
      .build();
    service.bindAccount(request);
  }

  @Test
  public void testUpdatePrinter() throws WxErrorException {
    final WxMaExpressService service = wxMaService.getExpressService();
    WxMaExpressPrinterUpdateRequest request = WxMaExpressPrinterUpdateRequest.builder()
      .openid("*************")
      .updateType(WxMaConstants.BindAccountType.UNBIND)
      .build();
    service.updatePrinter(request);
  }

  @Test
  public void testGetPrinter() throws WxErrorException {
    final WxMaExpressService service = wxMaService.getExpressService();
    WxMaExpressPrinter printer = service.getPrinter();
    System.out.println(WxMaGsonBuilder.create().toJson(printer));
  }

  @Test
  public void testAddOrder() throws WxErrorException {
    final WxMaExpressService service = wxMaService.getExpressService();

    WxMaExpressOrderPerson sender = new WxMaExpressOrderPerson();
    sender.setName("张三");
    sender.setMobile("177*****809");
    sender.setProvince("北京市");
    sender.setCity("朝阳区");
    sender.setArea("朝阳区");
    sender.setAddress("西坝河****C-102");

    WxMaExpressOrderPerson receiver = new WxMaExpressOrderPerson();
    receiver.setName("李四");
    receiver.setMobile("180*****772");
    receiver.setProvince("北京市");
    receiver.setCity("朝阳区");
    receiver.setArea("朝阳区");
    receiver.setAddress("西坝河******单元101");


    WxMaExpressOrderCargo cargo = new WxMaExpressOrderCargo();
    List<WxMaExpressOrderCargoDetail> detailList = new ArrayList<>();
    List<String> shopNames = new ArrayList<>();
    Integer goodsCount = 0;
    for (int i = 0; i < 4; i++) {
      WxMaExpressOrderCargoDetail detail = new WxMaExpressOrderCargoDetail();
      String shopName = "商品_"+i;
      detail.setName(shopName);
      detail.setCount(1);
      detailList.add(detail);
      shopNames.add(shopName);
      goodsCount ++;
    }
    cargo.setCount(detailList.size());
    cargo.setWeight(5);
    cargo.setSpaceHeight(10);
    cargo.setSpaceLength(10);
    cargo.setSpaceWidth(10);
    cargo.setDetailList(detailList);


    WxMaExpressOrderShop shop = new WxMaExpressOrderShop();
    shop.setWxaPath("/index/index?from=waybill&id=01234567890123456789");
    shop.setGoodsName(StringUtils.join(shopNames,"&"));
    shop.setGoodsCount(goodsCount);
    shop.setImgUrl("https://mmbiz.qpic.cn/mmbiz_png/OiaFLUqewuIDNQnTiaCInIG8ibdosYHhQHPbXJUrqYSNIcBL60vo4LIjlcoNG1QPkeH5GWWEB41Ny895CokeAah8A/640");

    WxMaExpressDelivery.ServiceType serviceType = new WxMaExpressDelivery.ServiceType();
    serviceType.setServiceName("test_service_name");
    serviceType.setServiceType(1);

    WxMaExpressAddOrderRequest request = WxMaExpressAddOrderRequest.builder()
      .addSource(0)
      .orderId("test201911271429003")
      .openid("*****")
      .deliveryId("TEST")
      .bizId("test_biz_id")
      .customRemark("")
      .expectTime(0L)
      .sender(sender)
      .receiver(receiver)
      .cargo(cargo)
      .shop(shop)
      .insured(new WxMaExpressOrderInsured())
      .service(serviceType)
      .build();

    WxMaExpressAddOrderResult result = service.addOrder(request);
    System.out.println(WxMaGsonBuilder.create().toJson(result));
  }
}
