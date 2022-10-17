package cn.binarywang.wx.miniapp.test;

import cn.binarywang.wx.miniapp.bean.delivery.AddOrderRequest;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import org.testng.annotations.Test;

public class AddOrderJsonTest {

  /**
   * 验证转化Json时是否有deliverySign
   */
  @Test
  public void test(){
    AddOrderRequest request = new AddOrderRequest();
    request.setShopId("1");
    request.setAppSecret("2");
    request.getDeliverySign();
    System.out.printf(WxGsonBuilder.create().toJson(request));
  }
}
