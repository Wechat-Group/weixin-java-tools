package cn.binarywang.wx.miniapp.api;


import cn.binarywang.wx.miniapp.bean.shortlink.GenerateShortLinkRequest;
import cn.binarywang.wx.miniapp.bean.urllink.GenerateUrlLinkRequest;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2021-06-10
 */
public interface WxMaLinkService {
  /**
   * 获取小程序 URL Link接口
   * 接口文档: https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/url-link/urllink.generate.html
   *
   * @param request 请求
   * @return link地址
   * @throws WxErrorException .
   */
  String generateUrlLink(GenerateUrlLinkRequest request) throws WxErrorException;

  /**
   * 获取小程序 Short Link接口
   * 接口文档: https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/short-link/shortlink.generate.html
   *
   * @param request 请求
   * @return link地址
   * @throws WxErrorException .
   */
  String generateShortLink(GenerateShortLinkRequest request) throws WxErrorException;
}
