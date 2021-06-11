package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaLinkService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.urllink.GenerateUrlLinkRequest;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Link.GENERATE_URLLINK_URL;

/**
 * 获取小程序 URL Link接口实现
 * 接口文档: https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/url-link/urllink.generate.html
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2021-06-10
 */
@AllArgsConstructor
public class WxMaLinkServiceImpl implements WxMaLinkService {

  private final WxMaService wxMaService;

  @Override
  public String generate(GenerateUrlLinkRequest request) throws WxErrorException {
    String result = this.wxMaService.post(GENERATE_URLLINK_URL,request);
    String linkField = "url_link";
    JsonObject jsonObject = GsonParser.parse(result);
    if(jsonObject.has(linkField)){
      return jsonObject.get(linkField).toString();
    }
    throw new WxErrorException("无url_link");
  }
  }
