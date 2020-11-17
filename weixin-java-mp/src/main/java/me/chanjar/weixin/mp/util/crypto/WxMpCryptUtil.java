/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 *
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 * <p>
 * 针对org.apache.commons.codec.binary.Base64，
 * 需要导入架包commons-codec-1.9（或commons-codec-1.8等其他版本）
 * 官方下载地址：http://commons.apache.org/proper/commons-codec/download_codec.cgi
 */

// ------------------------------------------------------------------------

/**
 * 针对org.apache.commons.codec.binary.Base64，
 * 需要导入架包commons-codec-1.9（或commons-codec-1.8等其他版本）
 * 官方下载地址：http://commons.apache.org/proper/commons-codec/download_codec.cgi
 */
package me.chanjar.weixin.mp.util.crypto;

import com.google.common.base.CharMatcher;
import com.google.common.io.BaseEncoding;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;

public class WxMpCryptUtil extends me.chanjar.weixin.common.util.crypto.WxCryptUtil {

  /**
   * 构造函数
   *
   * @param wxMpConfigStorage
   */
  public WxMpCryptUtil(WxMpConfigStorage wxMpConfigStorage) {
    /*
     * @param token          公众平台上，开发者设置的token
     * @param encodingAesKey 公众平台上，开发者设置的EncodingAESKey
     * @param appId          公众平台appid
     */
    String encodingAesKey = wxMpConfigStorage.getAesKey();
    String token = wxMpConfigStorage.getToken();
    String appId = wxMpConfigStorage.getAppId();

    this.token = token;
    this.appidOrCorpid = appId;
    this.aesKey = BaseEncoding.base64().decode(CharMatcher.whitespace().removeFrom(encodingAesKey));
  }

}
