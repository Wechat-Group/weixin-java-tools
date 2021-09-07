package cn.binarywang.wx.miniapp.api;


import cn.binarywang.wx.miniapp.bean.shortlink.GenerateShortLinkRequest;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 获取小程序 Short Link接口
 * 接口文档: https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/short-link/shortlink.generate.html
 */
public interface WxMaShortLinkService {

  String generate(GenerateShortLinkRequest request) throws WxErrorException;
}
