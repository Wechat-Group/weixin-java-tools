package com.github.binarywang.wxpay.bean.result;

import com.google.common.collect.Maps;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.internal.path.xml.NodeImpl;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;
import io.restassured.path.xml.exception.XmlPathException;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

/**
 * <pre>
 * 微信支付结果共用属性类
 * Created by Binary Wang on 2016-10-24.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
public abstract class WxPayBaseResult {
  /**
   * 返回状态码
   */
  @XStreamAlias("return_code")
  protected String returnCode;
  /**
   * 返回信息
   */
  @XStreamAlias("return_msg")
  protected String returnMsg;
  private String xmlString;
  /**
   * 业务结果
   */
  @XStreamAlias("result_code")
  private String resultCode;
  /**
   * 错误代码
   */
  @XStreamAlias("err_code")
  private String errCode;
  /**
   * 错误代码描述
   */
  @XStreamAlias("err_code_des")
  private String errCodeDes;
  /**
   * 公众账号ID
   */
  @XStreamAlias("appid")
  private String appid;
  /**
   * 商户号
   */
  @XStreamAlias("mch_id")
  private String mchId;
  /**
   * 服务商模式下的子公众账号ID
   */
  @XStreamAlias("appid")
  private String subAppId;
  /**
   * 服务商模式下的子商户号
   */
  @XStreamAlias("sub_mch_id")
  private String subMchId;
  /**
   * 随机字符串
   */
  @XStreamAlias("nonce_str")
  private String nonceStr;
  /**
   * 签名
   */
  @XStreamAlias("sign")
  private String sign;

  /**
   * 将单位分转换成单位圆
   *
   * @param fee 将要被转换为元的分的数值
   */
  public static String feeToYuan(Integer fee) {
    return new BigDecimal(Double.valueOf(fee) / 100).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
  }

  /**
   * 从xml字符串创建bean对象
   */
  public static <T extends WxPayBaseResult> T fromXML(String xmlString, Class<T> clz) {
    XStream xstream = XStreamInitializer.getInstance();
    xstream.processAnnotations(clz);
    T result = (T) xstream.fromXML(xmlString);
    result.setXmlString(xmlString);
    return result;
  }

  public String getXmlString() {
    return this.xmlString;
  }

  public void setXmlString(String xmlString) {
    this.xmlString = xmlString;
  }

  protected Logger getLogger() {
    return LoggerFactory.getLogger(this.getClass());
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public String getReturnCode() {
    return this.returnCode;
  }

  public void setReturnCode(String returnCode) {
    this.returnCode = returnCode;
  }

  public String getReturnMsg() {
    return this.returnMsg;
  }

  public void setReturnMsg(String returnMsg) {
    this.returnMsg = returnMsg;
  }

  public String getResultCode() {
    return this.resultCode;
  }

  public void setResultCode(String resultCode) {
    this.resultCode = resultCode;
  }

  public String getErrCode() {
    return this.errCode;
  }

  public void setErrCode(String errCode) {
    this.errCode = errCode;
  }

  public String getErrCodeDes() {
    return this.errCodeDes;
  }

  public void setErrCodeDes(String errCodeDes) {
    this.errCodeDes = errCodeDes;
  }

  public String getAppid() {
    return this.appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public String getMchId() {
    return this.mchId;
  }

  public void setMchId(String mchId) {
    this.mchId = mchId;
  }

  public String getNonceStr() {
    return this.nonceStr;
  }

  public void setNonceStr(String nonceStr) {
    this.nonceStr = nonceStr;
  }

  public String getSign() {
    return this.sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public String getSubAppId() {
    return subAppId;
  }

  public void setSubAppId(String subAppId) {
    this.subAppId = subAppId;
  }

  public String getSubMchId() {
    return subMchId;
  }

  public void setSubMchId(String subMchId) {
    this.subMchId = subMchId;
  }

  /**
   * 将bean通过保存的xml字符串转换成map
   */
  public Map<String, String> toMap() {
    Map<String, String> result = Maps.newHashMap();
    XmlPath xmlPath = new XmlPath(this.xmlString);
    NodeImpl rootNode = null;
    try {
      rootNode = xmlPath.get("xml");
    } catch (XmlPathException e) {
      throw new RuntimeException("xml数据有问题，请核实！");
    }

    if (rootNode == null) {
      throw new RuntimeException("xml数据有问题，请核实！");
    }

    Iterator<Node> iterator = rootNode.children().nodeIterator();
    while (iterator.hasNext()) {
      Node node = iterator.next();
      result.put(node.name(), node.value());
    }

    return result;
  }

  private String getXmlValue(XmlPath xmlPath, String path) {
    if (xmlPath.get(path) instanceof NodeChildrenImpl) {
      if (((NodeChildrenImpl) xmlPath.get(path)).size() == 0) {
        return null;
      }
    }

    return xmlPath.getString(path);
  }

  protected <T> T getXmlValue(XmlPath xmlPath, String path, Class<T> clz) {
    String value = this.getXmlValue(xmlPath, path);
    if (value == null) {
      return null;
    }

    switch (clz.getSimpleName()) {
      case "String":
        return (T) value;
      case "Integer":
        return (T) Integer.valueOf(value);
    }

    throw new UnsupportedOperationException("暂时不支持此种类型的数据");
  }
}
