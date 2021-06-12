package cn.binarywang.wx.miniapp.api;


import cn.binarywang.wx.miniapp.bean.urllink.GenerateUrlLinkRequest;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 获取小程序 URL Link接口
 * 接口文档: https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/url-link/urllink.generate.html
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2021-06-10
 */
public interface WxMaLinkService {

  String generate(GenerateUrlLinkRequest request) throws WxErrorException;
}
