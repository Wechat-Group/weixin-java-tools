package com.github.binarywang.wxpay.bean.result;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import com.github.binarywang.wxpay.util.XmlConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.util.SignUtils;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;

import org.w3c.dom.*;

/**
 * <pre>
 * 微信支付结果共用属性类.
 * Created by Binary Wang on 2016-10-24.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
public abstract class BaseWxPayResult implements Serializable {
  /**
   * 返回状态码.
   */
  @XStreamAlias("return_code")
  protected String returnCode;
  /**
   * 返回信息.
   */
  @XStreamAlias("return_msg")
  protected String returnMsg;

  //当return_code为SUCCESS的时候，还会包括以下字段：

  /**
   * 业务结果.
   */
  @XStreamAlias("result_code")
  private String resultCode;
  /**
   * 错误代码.
   */
  @XStreamAlias("err_code")
  private String errCode;
  /**
   * 错误代码描述.
   */
  @XStreamAlias("err_code_des")
  private String errCodeDes;
  /**
   * 公众账号ID.
   */
  @XStreamAlias("appid")
  private String appid;
  /**
   * 商户号.
   */
  @XStreamAlias("mch_id")
  private String mchId;
  /**
   * 服务商模式下的子公众账号ID.
   */
  @XStreamAlias("sub_appid")
  private String subAppId;
  /**
   * 服务商模式下的子商户号.
   */
  @XStreamAlias("sub_mch_id")
  private String subMchId;
  /**
   * 随机字符串.
   */
  @XStreamAlias("nonce_str")
  private String nonceStr;
  /**
   * 签名.
   */
  @XStreamAlias("sign")
  private String sign;

  //以下为辅助属性
  /**
   * xml字符串.
   */
  private String xmlString;

  /**
   * xml的Document对象，用于解析xml文本.
   * make xmlDoc transient to ensure toString() can work.
   */
  private transient Document xmlDoc;

  /**
   * 将单位分转换成单位圆.
   *
   * @param fen 将要被转换为元的分的数值
   * @return the string
   */
  public static String fenToYuan(Integer fen) {
    return BigDecimal.valueOf(Double.valueOf(fen) / 100).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
  }

  /**
   * 从xml字符串创建bean对象.
   *
   * @param <T>       the type parameter
   * @param xmlString the xml string
   * @param clz       the clz
   * @return the t
   */
  public static <T extends BaseWxPayResult> T fromXML(String xmlString, Class<T> clz) {
    if (XmlConfig.fastMode) {
      try {
        BaseWxPayResult t = clz.newInstance();
        t.setXmlString(xmlString);
        Document doc = t.getXmlDoc();
        t.loadBasicXML(doc);
        t.loadXML(doc);
        return (T) t;
      } catch (Exception e) {
        throw new RuntimeException("parse xml error", e);
      }
    }
    XStream xstream = XStreamInitializer.getInstance();
    xstream.processAnnotations(clz);
    T result = (T) xstream.fromXML(xmlString);
    result.setXmlString(xmlString);
    return result;
  }

  /**
   * 从XML文档中加载属性,供子类覆盖加载额外的属性
   *
   * @param d Document
   */
  protected abstract void loadXML(Document d);

  /**
   * 从XML文档中加载基础属性
   *
   * @param d Document
   */
  private void loadBasicXML(Document d) {
    returnCode = readXMLString(d, "return_code");
    returnMsg = readXMLString(d, "return_msg");
    resultCode = readXMLString(d, "result_code");
    errCode = readXMLString(d, "err_code");
    errCodeDes = readXMLString(d, "err_code_des");
    appid = readXMLString(d, "appid");
    mchId = readXMLString(d, "mch_id");
    subAppId = readXMLString(d, "sub_appid");
    subMchId = readXMLString(d, "sub_mch_id");
    nonceStr = readXMLString(d, "nonce_str");
    sign = readXMLString(d, "sign");
  }

  protected Integer readXMLInteger(Node d, String tagName) {
    String content = readXMLString(d, tagName);
    if (content == null || content.trim().length() == 0) return null;
    return Integer.parseInt(content);
  }

  protected String readXMLString(Node d, String tagName) {
    if (!d.hasChildNodes()) return null;
    NodeList childNodes = d.getChildNodes();
    for (int i = 0, j = childNodes.getLength(); i < j; i++) {
      Node node = childNodes.item(i);
      if (tagName.equals(node.getNodeName())) {
        if (!node.hasChildNodes()) return null;
        return node.getFirstChild().getNodeValue();
      }
    }
    return null;
  }

  protected String readXMLString(Document d, String tagName) {
    NodeList elements = d.getElementsByTagName(tagName);
    if (elements == null || elements.getLength() == 0) {
      return null;
    }

    Node node = elements.item(0).getFirstChild();
    if (node == null) {
      return null;
    }
    return node.getNodeValue();
  }

  protected Integer readXMLInteger(Document d, String tagName) {
    String content = readXMLString(d, tagName);
    if (content == null || content.trim().length() == 0) return null;
    return Integer.parseInt(content);
  }

  /**
   * Gets logger.
   *
   * @return the logger
   */
  protected Logger getLogger() {
    return LoggerFactory.getLogger(this.getClass());
  }

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }

  /**
   * 将bean通过保存的xml字符串转换成map.
   *
   * @return the map
   */
  public Map<String, String> toMap() {
    if (StringUtils.isBlank(this.xmlString)) {
      throw new RuntimeException("xml数据有问题，请核实！");
    }

    Map<String, String> result = Maps.newHashMap();
    Document doc = this.getXmlDoc();

    try {
      NodeList list = (NodeList) XPathFactory.newInstance().newXPath()
        .compile("/xml/*")
        .evaluate(doc, XPathConstants.NODESET);
      int len = list.getLength();
      for (int i = 0; i < len; i++) {
        result.put(list.item(i).getNodeName(), list.item(i).getTextContent());
      }
    } catch (XPathExpressionException e) {
      throw new RuntimeException("非法的xml文本内容：" + xmlString);
    }

    return result;
  }

  /**
   * 将xml字符串转换成Document对象，以便读取其元素值.
   */
  private Document getXmlDoc() {
    if (this.xmlDoc != null) {
      return this.xmlDoc;
    }

    try {
      final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setExpandEntityReferences(false);
      factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
      this.xmlDoc = factory.newDocumentBuilder()
        .parse(new ByteArrayInputStream(this.xmlString.getBytes(StandardCharsets.UTF_8)));
      return xmlDoc;
    } catch (Exception e) {
      throw new RuntimeException("非法的xml文本内容：\n" + this.xmlString, e);
    }

  }

  /**
   * 获取xml中元素的值.
   *
   * @param path the path
   * @return the xml value
   */
  String getXmlValue(String... path) {
    Document doc = this.getXmlDoc();
    String expression = String.format("/%s//text()", Joiner.on("/").join(path));
    try {
      return (String) XPathFactory
        .newInstance()
        .newXPath()
        .compile(expression)
        .evaluate(doc, XPathConstants.STRING);
    } catch (XPathExpressionException e) {
      throw new RuntimeException("未找到相应路径的文本：" + expression);
    }
  }

  /**
   * 获取xml中元素的值，作为int值返回.
   *
   * @param path the path
   * @return the xml value as int
   */
  Integer getXmlValueAsInt(String... path) {
    String result = this.getXmlValue(path);
    if (StringUtils.isBlank(result)) {
      return null;
    }

    return Integer.valueOf(result);
  }

  /**
   * 校验返回结果签名.
   *
   * @param wxPayService the wx pay service
   * @param signType     签名类型
   * @param checkSuccess 是否同时检查结果是否成功
   * @throws WxPayException the wx pay exception
   */
  public void checkResult(WxPayService wxPayService, String signType, boolean checkSuccess) throws WxPayException {
    //校验返回结果签名
    Map<String, String> map = toMap();
    if (getSign() != null && !SignUtils.checkSign(map, signType, wxPayService.getConfig().getMchKey())) {
      this.getLogger().debug("校验结果签名失败，参数：{}", map);
      throw new WxPayException("参数格式校验错误！");
    }

    //校验结果是否成功
    if (checkSuccess) {
      List<String> successStrings = Lists.newArrayList(WxPayConstants.ResultCode.SUCCESS, "");
      if (!successStrings.contains(StringUtils.trimToEmpty(getReturnCode()).toUpperCase())
        || !successStrings.contains(StringUtils.trimToEmpty(getResultCode()).toUpperCase())) {
        StringBuilder errorMsg = new StringBuilder();
        if (getReturnCode() != null) {
          errorMsg.append("返回代码：").append(getReturnCode());
        }
        if (getReturnMsg() != null) {
          errorMsg.append("，返回信息：").append(getReturnMsg());
        }
        if (getResultCode() != null) {
          errorMsg.append("，结果代码：").append(getResultCode());
        }
        if (getErrCode() != null) {
          errorMsg.append("，错误代码：").append(getErrCode());
        }
        if (getErrCodeDes() != null) {
          errorMsg.append("，错误详情：").append(getErrCodeDes());
        }

        this.getLogger().error("\n结果业务代码异常，返回结果：{},\n{}", map, errorMsg.toString());
        throw WxPayException.from(this);
      }
    }
  }
}
