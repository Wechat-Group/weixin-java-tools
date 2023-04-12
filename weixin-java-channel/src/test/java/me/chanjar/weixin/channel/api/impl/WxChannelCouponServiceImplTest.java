package me.chanjar.weixin.channel.api.impl;

import static org.testng.Assert.*;

import com.google.inject.Inject;
import me.chanjar.weixin.channel.api.WxChannelCouponService;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.coupon.CouponIdResponse;
import me.chanjar.weixin.channel.bean.coupon.CouponInfoResponse;
import me.chanjar.weixin.channel.bean.coupon.CouponListParam;
import me.chanjar.weixin.channel.bean.coupon.CouponListResponse;
import me.chanjar.weixin.channel.bean.coupon.CouponParam;
import me.chanjar.weixin.channel.bean.coupon.UserCouponListParam;
import me.chanjar.weixin.channel.bean.coupon.UserCouponListResponse;
import me.chanjar.weixin.channel.bean.coupon.UserCouponResponse;
import me.chanjar.weixin.channel.test.ApiTestModule;
import me.chanjar.weixin.common.error.WxErrorException;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxChannelCouponServiceImplTest {

  @Inject
  private WxChannelService channelService;

  @Test
  public void testCreateCoupon() throws WxErrorException {
    WxChannelCouponService couponService = channelService.getCouponService();
    CouponParam param = new CouponParam();
    // ...
    CouponIdResponse response = couponService.createCoupon(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUpdateCoupon() throws WxErrorException {
    WxChannelCouponService couponService = channelService.getCouponService();
    CouponParam param = new CouponParam();
    // ...
    CouponIdResponse response = couponService.updateCoupon(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testUpdateCouponStatus() throws WxErrorException {
    WxChannelCouponService couponService = channelService.getCouponService();
    String couponId = "";
    Integer status = 2;
    WxChannelBaseResponse response = couponService.updateCouponStatus(couponId, status);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetCoupon() throws WxErrorException {
    WxChannelCouponService couponService = channelService.getCouponService();
    String couponId = "";
    CouponInfoResponse response = couponService.getCoupon(couponId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetCouponList() throws WxErrorException {
    WxChannelCouponService couponService = channelService.getCouponService();
    CouponListParam param = new UserCouponListParam();
    CouponListResponse response = couponService.getCouponList(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetUserCoupon() throws WxErrorException {
    WxChannelCouponService couponService = channelService.getCouponService();
    String couponId = "";
    String userCouponId = "";
    UserCouponResponse response = couponService.getUserCoupon(couponId, userCouponId);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }

  @Test
  public void testGetUserCouponList() throws WxErrorException {
    WxChannelCouponService couponService = channelService.getCouponService();
    UserCouponListParam param = new UserCouponListParam();
    UserCouponListResponse response = couponService.getUserCouponList(param);
    assertNotNull(response);
    assertTrue(response.isSuccess());
  }
}
