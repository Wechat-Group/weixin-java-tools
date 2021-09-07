package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaShortLinkService;
import cn.binarywang.wx.miniapp.bean.shortlink.GenerateShortLinkRequest;
import cn.binarywang.wx.miniapp.bean.urllink.GenerateUrlLinkRequest;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Link.GENERATE_URLLINK_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.ShortLink.GENERATE_SHORT_LINK_URL;

/**
 * 获取小程序 Short Link接口
 * 接口文档: https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/short-link/shortlink.generate.html
 */
@AllArgsConstructor
public class WxMaShortLinkServiceImpl implements WxMaShortLinkService {

  private final WxMaService wxMaService;

  @Override
  public String generate(GenerateShortLinkRequest request) throws WxErrorException {
    String result = this.wxMaService.post(GENERATE_SHORT_LINK_URL,request);
    String linkField = "link";
    JsonObject jsonObject = GsonParser.parse(result);
    if(jsonObject.has(linkField)){
      return jsonObject.get(linkField).toString();
    }
    throw new WxErrorException("无link");
  }
  }
