package me.chanjar.weixin.cp.util.crypto;

import com.google.common.base.CharMatcher;
import com.google.common.io.BaseEncoding;
import me.chanjar.weixin.common.util.crypto.WxCryptUtil;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;

/**
 * @author someone
 */
public class WxCpTpCryptUtil extends WxCryptUtil {
  /**
   * 构造函数.
   */
  public WxCpTpCryptUtil(WxCpTpConfigStorage wxCpTpConfigStorage) {
    /*
     * @param token          公众平台上，开发者设置的token
     * @param encodingAesKey 公众平台上，开发者设置的EncodingAESKey
     * @param appidOrCorpid          公众平台corpId
     */
    String encodingAesKey = wxCpTpConfigStorage.getAesKey();
    String token = wxCpTpConfigStorage.getToken();
    String corpId = wxCpTpConfigStorage.getCorpId();

    this.token = token;
    this.appidOrCorpid = corpId;
    this.aesKey = BaseEncoding.base64().decode(CharMatcher.whitespace().removeFrom(encodingAesKey));
  }


}
