package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaInternetService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.internet.WxMaInternetResponse;
import cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 服务端网络相关接口
 *
 * @author <a href="https://github.com/chutian0124">chutian0124</a>
 * @文档地址: https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/internet/internet.getUserEncryptKey.html
 * @Date 2021-09-06
 */
@RequiredArgsConstructor
public class WxMaInternetServiceImpl implements WxMaInternetService {
  private final WxMaService wxMaService;

  @Override
  public WxMaInternetResponse getUserEncryptKey(String openid, String signature, String sigMethod) throws WxErrorException {
    String url = WxMaApiUrlConstants.Internet.GET_USER_ENCRYPT_KEY + "?access_token=" + this.wxMaService.getAccessToken()
      + "&openid=" + openid + "&signature" + signature + "&sig_method" + sigMethod;

    String responseContent = this.wxMaService.post(url, "");
    WxMaInternetResponse response = WxMaGsonBuilder.create().fromJson(responseContent, WxMaInternetResponse.class);
    if (response.getErrcode() == -1) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return response;
  }
}
