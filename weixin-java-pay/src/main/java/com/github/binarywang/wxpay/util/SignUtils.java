package com.github.binarywang.wxpay.util;

import com.github.binarywang.wxpay.constant.WxPayConstants.SignType;
import com.google.common.collect.Lists;
import me.chanjar.weixin.common.util.BeanUtils;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <pre>
 * 签名相关工具类
 * Created by Binary Wang on 2017-3-23.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
public class SignUtils {
  private static final Logger log = LoggerFactory.getLogger(SignUtils.class);

  /**
   * 微信公众号支付签名算法(详见:https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=4_3)
   *
   * @param xmlBean          Bean里的属性如果存在XML注解，则使用其作为key，否则使用变量名
   * @param signType         签名类型，如果为空，则默认为MD5
   * @param signKey          签名Key
   * @param isIgnoreSignType 签名时，是否忽略signType
   * @return 签名字符串
   */
  public static String createSign(Object xmlBean, String signType, String signKey, boolean isIgnoreSignType) {
    return createSign(BeanUtils.xmlBean2Map(xmlBean), signType, signKey, isIgnoreSignType);
  }

  /**
   * 微信公众号支付签名算法(详见:https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=4_3)
   *
   * @param params           参数信息
   * @param signType         签名类型，如果为空，则默认为MD5
   * @param signKey          签名Key
   * @param isIgnoreSignType 签名时，是否忽略signType
   * @return 签名字符串
   */
  public static String createSign(Map<String, String> params, String signType, String signKey, boolean isIgnoreSignType) {
    SortedMap<String, String> sortedMap = new TreeMap<>(params);

    StringBuilder toSign = new StringBuilder();
    for (String key : sortedMap.keySet()) {
      String value = params.get(key);
      boolean shouldSign = false;
      if (isIgnoreSignType && "sign_type".equals(key)) {
        shouldSign = false;
      } else if (StringUtils.isNotEmpty(value)
        && !Lists.newArrayList("sign", "key").contains(key)) {
        shouldSign = true;
      }

      if (shouldSign) {
        toSign.append(key).append("=").append(value).append("&");
      }
    }

    toSign.append("key=").append(signKey);
    if (SignType.HMAC_SHA256.equals(signType)) {
      return createHMACSha256Sign(toSign.toString(), signKey);
    } else {
      return DigestUtils.md5Hex(toSign.toString()).toUpperCase();
    }
  }

  private static String createHMACSha256Sign(String message, String key) {
    try {
      Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
      SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
      hmacSHA256.init(secretKeySpec);
      byte[] bytes = hmacSHA256.doFinal(message.getBytes());
      return Hex.encodeHexString(bytes).toUpperCase();
    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
      log.error(e.getMessage(), e);
    }

    return null;
  }

  /**
   * 校验签名是否正确
   *
   * @param xmlBean  Bean需要标记有XML注解
   * @param signType 签名类型，如果为空，则默认为MD5
   * @param signKey  校验的签名Key
   * @return true - 签名校验成功，false - 签名校验失败
   * @see #checkSign(Map, String, String)
   */
  public static boolean checkSign(Object xmlBean, String signType, String signKey) {
    return checkSign(BeanUtils.xmlBean2Map(xmlBean), signType, signKey);
  }

  /**
   * 校验签名是否正确
   *
   * @param params   需要校验的参数Map
   * @param signType 签名类型，如果为空，则默认为MD5
   * @param signKey  校验的签名Key
   * @return true - 签名校验成功，false - 签名校验失败
   * @see #checkSign(Map, String, String)
   */
  public static boolean checkSign(Map<String, String> params, String signType, String signKey) {
    String sign = createSign(params, signType, signKey, false);
    return sign.equals(params.get("sign"));
  }
}
