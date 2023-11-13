package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaLinkService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.shortlink.GenerateShortLinkRequest;
import cn.binarywang.wx.miniapp.bean.urllink.GenerateUrlLinkRequest;
import cn.binarywang.wx.miniapp.bean.urllink.request.QueryUrlLinkRequest;
import cn.binarywang.wx.miniapp.bean.urllink.response.QueryUrlLinkResponse;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Link.GENERATE_URLLINK_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Link.QUERY_URLLINK_URL;
import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.ShortLink.GENERATE_SHORT_LINK_URL;

/**
 * 获取小程序 URL Link接口实现
 * 接口文档: https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/url-link/urllink.generate.html
 *
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2021-06-10
 */
@AllArgsConstructor
public class WxMaLinkServiceImpl implements WxMaLinkService {
  private final WxMaService wxMaService;

  @Override
  public String generateUrlLink(GenerateUrlLinkRequest request) throws WxErrorException {
    String result = this.wxMaService.post(GENERATE_URLLINK_URL, request);
    String linkField = "url_link";
    JsonObject jsonObject = GsonParser.parse(result);
    if (jsonObject.has(linkField)) {
      return jsonObject.get(linkField).getAsString();
    }
    throw new WxErrorException("无url_link");
  }

  @Override
  public String generateShortLink(GenerateShortLinkRequest request) throws WxErrorException {
    String result = this.wxMaService.post(GENERATE_SHORT_LINK_URL, request);
    String linkField = "link";
    JsonObject jsonObject = GsonParser.parse(result);
    if (jsonObject.has(linkField)) {
      return jsonObject.get(linkField).getAsString();
    }
    throw new WxErrorException("无link");
  }

  @Override
  public QueryUrlLinkResponse queryUrlLink(QueryUrlLinkRequest request) throws WxErrorException {
    String result = this.wxMaService.post(QUERY_URLLINK_URL, request);
    return WxGsonBuilder.create().fromJson(result, QueryUrlLinkResponse.class);
  }
}
