package us.wili.jason.weixin.open.util.crypto;

import me.chanjar.weixin.common.util.crypto.WxCryptUtil;
import org.apache.commons.codec.binary.Base64;
import us.wili.jason.weixin.open.api.WxOpenConfigStorage;

/**
 * Created by JasonY on 17/10/24.
 */
public class WxOpenCryptUtil extends WxCryptUtil {

  public WxOpenCryptUtil(WxOpenConfigStorage wxOpenConfigStorage) {
    String encodingAesKey = wxOpenConfigStorage.getAesKey();
    String token = wxOpenConfigStorage.getToken();
    String appid = wxOpenConfigStorage.getAppId();

    this.token = token;
    this.appidOrCorpid = appid;
    this.aesKey = Base64.decodeBase64(encodingAesKey + "=");
  }

}
