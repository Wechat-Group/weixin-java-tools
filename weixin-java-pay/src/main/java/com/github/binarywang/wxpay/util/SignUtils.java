package com.github.binarywang.wxpay.util;

import me.chanjar.weixin.common.util.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

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

  /**
   * 微信公众号支付签名算法(详见:https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=4_3)
   *
   * @param xmlBean Bean需要标记有XML注解
   * @param signKey 签名Key
   * @return 签名字符串
   */
  public static String createSign(Object xmlBean, String signKey) {
    return createSign(BeanUtils.xmlBean2Map(xmlBean), signKey);
  }

  /**
   * 微信公众号支付签名算法(详见:https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=4_3)
   *
   * @param params  参数信息
   * @param signKey 签名Key
   * @return 签名字符串
   */
  public static String createSign(Map<String, String> params, String signKey) {
//    if (this.getConfig().useSandbox()) {
//      //使用仿真测试环境
//      //TODO 目前测试发现，以下两行代码都会出问题，所以暂不建议使用仿真测试环境
//      signKey = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456";
//      //return "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456";
//    }

    SortedMap<String, String> sortedMap = new TreeMap<>(params);

    StringBuilder toSign = new StringBuilder();
    for (String key : sortedMap.keySet()) {
      String value = params.get(key);
      if (StringUtils.isNotEmpty(value) && !"sign".equals(key) && !"key".equals(key)) {
        toSign.append(key).append("=").append(value).append("&");
      }
    }

    toSign.append("key=").append(signKey);
    return DigestUtils.md5Hex(toSign.toString()).toUpperCase();
  }

  /**
   * 校验签名是否正确
   *
   * @param xmlBean Bean需要标记有XML注解
   * @param signKey 校验的签名Key
   * @return true - 签名校验成功，false - 签名校验失败
   * @see #checkSign(Map, String)
   */
  public static boolean checkSign(Object xmlBean, String signKey) {
    return checkSign(BeanUtils.xmlBean2Map(xmlBean), signKey);
  }

  /**
   * 校验签名是否正确
   *
   * @param params  需要校验的参数Map
   * @param signKey 校验的签名Key
   * @return true - 签名校验成功，false - 签名校验失败
   * @see #checkSign(Map, String)
   */
  public static boolean checkSign(Map<String, String> params, String signKey) {
    String sign = createSign(params, signKey);
    return sign.equals(params.get("sign"));
  }
}
