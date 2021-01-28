package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.scheme.WxMaGenerateSchemeRequest;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * <pre>
 * 小程序Scheme码相关操作接口.
 *
 * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/url-scheme/urlscheme.generate.html
 * </pre>
 *
 * @author : cofedream
 * @date : 2021-01-26
 */
public interface WxMaSchemeService {
  String GENERATE_SCHEME_URL = "https://api.weixin.qq.com/wxa/generatescheme";

  /**
   * 获取小程序scheme码
   *
   * @param request 请求参数
   * @throws WxErrorException 生成失败时抛出，具体错误码请看文档
   */
  String generate(WxMaGenerateSchemeRequest request) throws WxErrorException;
}
