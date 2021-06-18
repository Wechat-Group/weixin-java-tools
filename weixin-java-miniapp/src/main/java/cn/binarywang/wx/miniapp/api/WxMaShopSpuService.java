package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.shop.WxMaShopSpuInfo;
import cn.binarywang.wx.miniapp.bean.shop.WxMaShopSpuWithoutAuditInfo;
import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopSpuPageRequest;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAddSpuResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopGetSpuListResponse;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopGetSpuResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 小程序交易组件-商品服务
 *
 * @author boris
 */
public interface WxMaShopSpuService {
  WxMaShopAddSpuResponse addSpu(WxMaShopSpuInfo spuInfo) throws WxErrorException;

  WxMaShopBaseResponse deleteSpu(Integer productId, String outProductId) throws WxErrorException;

  WxMaShopGetSpuResponse getSpu(Integer productId, String outProductId, Integer needEditSpu)
    throws WxErrorException;

  WxMaShopGetSpuListResponse getSpuList(WxMaShopSpuPageRequest request)
    throws WxErrorException;

  WxMaShopAddSpuResponse updateSpu(WxMaShopSpuInfo spuInfo) throws WxErrorException;

  WxMaShopAddSpuResponse updateSpuWithoutAudit(WxMaShopSpuWithoutAuditInfo spuInfo)
    throws WxErrorException;

  WxMaShopBaseResponse listingSpu(Integer productId, String outProductId)
    throws WxErrorException;

  WxMaShopBaseResponse delistingSpu(Integer productId, String outProductId)
    throws WxErrorException;
}
