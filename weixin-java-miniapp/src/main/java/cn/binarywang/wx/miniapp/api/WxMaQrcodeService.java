package cn.binarywang.wx.miniapp.api;

import me.chanjar.weixin.common.exception.WxErrorException;

import java.io.File;

/**
 * <pre>
 * 二维码相关操作接口
 * 文档地址：https://mp.weixin.qq.com/debug/wxadoc/dev/api/qrcode.html
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMaQrcodeService {

  /**
   * <pre>
   * 获取小程序页面二维码
   * 适用于需要的码数量较少的业务场景
   * 通过该接口，仅能生成已发布的小程序的二维码。
   * 可以在开发者工具预览时生成开发版的带参二维码。
   * 带参二维码只有 100000 个，请谨慎调用。
   * </pre>
   *
   * @param path  不能为空，最大长度 128 字节
   * @param width 默认430 二维码的宽度
   */
  File createQrcode(String path, int width) throws WxErrorException;
}
