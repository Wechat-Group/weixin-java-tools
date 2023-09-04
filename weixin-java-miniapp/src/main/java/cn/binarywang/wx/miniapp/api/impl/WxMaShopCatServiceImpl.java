package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaShopCatService;
import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopCatGetResponse;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Shop.Cat.GET_CAT;
import static me.chanjar.weixin.common.api.WxConsts.ERR_CODE;

/**
 * @author liming1019
 */
@RequiredArgsConstructor
@Slf4j
public class WxMaShopCatServiceImpl implements WxMaShopCatService {
  private final WxMaService wxMaService;

  @Override
  public WxMaShopCatGetResponse getCat() throws WxErrorException {
    String responseContent = this.wxMaService.post(GET_CAT, new JsonObject());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(ERR_CODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaGsonBuilder.create().fromJson(responseContent, WxMaShopCatGetResponse.class);
  }
}
