package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopSearchSharerResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopSharerBindResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopSharerDataSummaryResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopSharerListResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopSharerLiveOrderListResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopSharerLiveSummaryListResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopSharerUnbindResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 分享员
 * @author leiin
 * created on  2022/6/18 2:48 下午
 */
public interface WxMaShopSharerService {

  /**
   * 绑定分享员
   * 用来批量邀请分享员
   * @param openids
   * @return
   * @throws WxErrorException
   */
  WxMaShopSharerBindResponse bindSharer(String[] openids) throws WxErrorException;

  /**
   * 获取分享员的总带货数据
   * @param openid
   * @return
   * @throws WxErrorException
   */
  WxMaShopSharerDataSummaryResponse getSharerDataSummary(String openid) throws WxErrorException;

  /**
   * 获取已经绑定的分享员列表
   * @param page
   * @param pageSize
   * @return
   * @throws WxErrorException
   */
  WxMaShopSharerListResponse getSharerList(Integer page, Integer pageSize) throws WxErrorException;

  /**
   * 获取分享员的直播间订单汇总
   * @param openid
   * @param liveExportId
   * @param page
   * @param pageSize
   * @return
   * @throws WxErrorException
   */
  WxMaShopSharerLiveOrderListResponse getSharerLiveOrderList(String openid, String liveExportId,
    Integer page, Integer pageSize) throws WxErrorException;

  /**
   * 获取分享员的直播间带货数据汇总
   * @param openid
   * @param page
   * @param pageSize
   * @return
   * @throws WxErrorException
   */
  WxMaShopSharerLiveSummaryListResponse getSharerLiveSummaryList(String openid,
    Integer page, Integer pageSize) throws WxErrorException;

  /**
   * 查看分享员
   * @param openid
   * @return
   * @throws WxErrorException
   */
  WxMaShopSearchSharerResponse searchSharer(String openid) throws WxErrorException;

  /**
   * 解绑分享员
   * @param openids
   * @return
   * @throws WxErrorException
   */
  WxMaShopSharerUnbindResponse unbindSharer(String[] openids) throws WxErrorException;
}
