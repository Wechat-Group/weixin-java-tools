package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.shop.WxMaShopCouponInfo;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopCouponListResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopCouponResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopUserCouponListResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * @author leiin
 * created on  2022/7/1 2:49 下午
 */
public interface WxMaShopCouponService {

  WxMaShopBaseResponse addCoupon(WxMaShopCouponInfo couponInfo) throws WxErrorException;

  WxMaShopCouponResponse getCoupon(String outCouponId) throws WxErrorException;

  WxMaShopCouponListResponse getCouponList(Integer pageSize,
    Integer offset) throws WxErrorException;

  WxMaShopBaseResponse updateCoupon(WxMaShopCouponInfo couponInfo) throws WxErrorException;

  WxMaShopBaseResponse updateCouponStatus(String outCouponId,
    Integer status) throws WxErrorException;

  WxMaShopBaseResponse updateCouponStock(String outCouponId, Integer isUsedNum, Integer receiveNum) throws WxErrorException;

  WxMaShopBaseResponse addUserCoupon(String openid, String outUserCouponId,
    String outCouponId, Integer status, Long recvTime) throws WxErrorException;

  WxMaShopUserCouponListResponse getUserCouponList(Integer pageSize, Integer offset, String openid) throws WxErrorException;

  WxMaShopBaseResponse updateUserCoupon(String openid, String outUserCouponId,
    String outCouponId, Long useTime, Long recvTime) throws WxErrorException;

  WxMaShopBaseResponse updateUserCouponStatus(String openid, String outUserCouponId,
    String outCouponId, Integer status) throws WxErrorException;
}
