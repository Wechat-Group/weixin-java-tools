package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopCatGetResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 小程序交易组件-接入商品前必需接口
 *
 * @author liming1019
 */
public interface WxMaShopCatService {
  /**
   * 获取商品类目
   *
   * @return WxMaShopCatGetResponse
   * @throws WxErrorException
   */
  WxMaShopCatGetResponse getCat() throws WxErrorException;
}
