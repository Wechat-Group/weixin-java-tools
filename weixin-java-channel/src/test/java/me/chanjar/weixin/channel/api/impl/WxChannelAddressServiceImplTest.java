package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import com.google.inject.Inject;
import me.chanjar.weixin.channel.api.WxChannelAddressService;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.address.AddressDetail;
import me.chanjar.weixin.channel.bean.address.AddressIdResponse;
import me.chanjar.weixin.channel.bean.address.AddressInfoResponse;
import me.chanjar.weixin.channel.bean.address.AddressListResponse;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxChannelAddressServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testListAddress() throws WxErrorException {
    WxChannelAddressService addressService = channelService.getAddressService();
    AddressListResponse response = addressService.listAddress(0, 10);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetAddress() throws WxErrorException {
    WxChannelAddressService addressService = channelService.getAddressService();
    String addressId = "";
    AddressInfoResponse response = addressService.getAddress(addressId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testAddAddress() throws WxErrorException {
    WxChannelAddressService addressService = channelService.getAddressService();
    AddressDetail addressDetail = new AddressDetail();
    // ...
    AddressIdResponse response = addressService.addAddress(addressDetail);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUpdateAddress() throws WxErrorException {
    WxChannelAddressService addressService = channelService.getAddressService();
    AddressDetail addressDetail = new AddressDetail();
    // ...
    WxChannelBaseResponse response = addressService.updateAddress(addressDetail);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testDeleteAddress() throws WxErrorException {
    WxChannelAddressService addressService = channelService.getAddressService();
    String addressId = "";
    WxChannelBaseResponse response = addressService.deleteAddress(addressId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }
}
