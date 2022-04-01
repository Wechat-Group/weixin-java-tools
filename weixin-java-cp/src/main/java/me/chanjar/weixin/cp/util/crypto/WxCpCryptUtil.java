package me.chanjar.weixin.cp.util.crypto;

import com.google.common.base.CharMatcher;
import com.google.common.io.BaseEncoding;
import me.chanjar.weixin.common.util.crypto.WxCryptUtil;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

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

  /**
   * 会话存档接口解密私钥
   * 企业获取的会话内容将用公钥加密，企业用自行保存的私钥解开会话内容数据
   *
   * @param encryptRandomKey
   * @param msgAuditPriKey
   * @return
   * @throws Exception
   */
  public static String decryptByPriKey(String encryptRandomKey, String msgAuditPriKey) throws Exception {
    String privateKey = msgAuditPriKey.replaceAll("\\n", "")
      .replace("-----BEGIN PRIVATE KEY-----", "")
      .replace("-----END PRIVATE KEY-----", "")
      .replaceAll(" ", "");

    byte[] keyByte = Base64.decodeBase64(privateKey);
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyByte);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    cipher.init(Cipher.DECRYPT_MODE, priKey);
    byte[] utf8 = cipher.doFinal(Base64.decodeBase64(encryptRandomKey));

    return new String(utf8, "UTF-8");
  }

}
