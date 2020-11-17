package me.chanjar.weixin.cp.util.crypto;

import com.google.common.base.CharMatcher;
import com.google.common.io.BaseEncoding;
import me.chanjar.weixin.common.util.crypto.WxCryptUtil;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;

public class WxCpCryptUtil extends WxCryptUtil {
  public WxCpCryptUtil(WxCpConfigStorage wxCpConfigStorage) {
    /*
     * @param token          公众平台上，开发者设置的token
     * @param encodingAesKey 公众平台上，开发者设置的EncodingAESKey
     * @param appidOrCorpid          公众平台appid
     */
    String encodingAesKey = wxCpConfigStorage.getAesKey();
    String token = wxCpConfigStorage.getToken();
    String corpId = wxCpConfigStorage.getCorpId();

    this.token = token;
    this.appidOrCorpid = corpId;
    this.aesKey = BaseEncoding.base64().decode(CharMatcher.whitespace().removeFrom(encodingAesKey));
  }

}
