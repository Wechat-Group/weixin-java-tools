package me.chanjar.weixin.open.api.impl;

import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.api.WxOpenMinishopGoodsService;
import me.chanjar.weixin.open.bean.minishopGoods.AddMinishopGoodsSPU;
import me.chanjar.weixin.open.bean.minishopGoods.GoodsCatList;
import me.chanjar.weixin.open.bean.minishopGoods.ParentCatId;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

@Slf4j
public class WxOpenMinishopGoodsServiceImpl extends WxMaServiceImpl implements WxOpenMinishopGoodsService {

  @Override
  public GoodsCatList getMinishopGoodsCat(ParentCatId dto) throws WxErrorException {
    String response = post(getMinishopGoodsCatUrl, dto.toJsonObject().toString());
    log.info(response);
    return null;
  }

  @Override
  public WxOpenResult addMinishopGoodsSPU(AddMinishopGoodsSPU dto) throws WxErrorException {
    String response = post(addMinishopGoodsSPUUrl, dto.toJsonObject().toString());
    return null;
  }








}
