package me.chanjar.weixin.open.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import me.chanjar.weixin.open.util.crypto.WxOpenCryptUtil;
import me.chanjar.weixin.open.util.xml.XStreamTransformer;
import org.apache.commons.io.IOUtils;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * Created by JasonY on 17/10/24.
 */
@XStreamAlias("xml")
public class WxOpenXmlMessage  implements Serializable {

    @XStreamAlias("AppId")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String appid;

    @XStreamAlias("CreateTime")
    private Long createTime;

    @XStreamAlias("InfoType")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String infoType;

    @XStreamAlias("ComponentVerifyTicket")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String componentVerifyTicket;

    @XStreamAlias("AuthorizerAppid")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String authorizerAppid;

    @XStreamAlias("AuthorizationCode")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String authorizationCode;

    @XStreamAlias("AuthorizationCodeExpiredTime")
    private Long authorizationCodeExpiredTime;

    @XStreamAlias("PreAuthCode")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String preAuthCode;

  public static WxOpenXmlMessage fromXml(String xml) {
    return XStreamTransformer.fromXml(WxOpenXmlMessage.class, xml);
  }

  public static WxOpenXmlMessage fromXml(InputStream is) {
    return XStreamTransformer.fromXml(WxOpenXmlMessage.class, is);
  }

    /**
     * 从加密字符串转换
     *
     * @param encryptedXml 密文
     * @param wxOpenConfigStorage   配置存储器对象
     * @param timestamp    时间戳
     * @param nonce        随机串
     * @param msgSignature 签名串
     */
    public static WxOpenXmlMessage fromEncryptedXml(String encryptedXml,
                                                    WxOpenConfigStorage wxOpenConfigStorage, String timestamp, String nonce,
                                                    String msgSignature) {
        String plainText = new WxOpenCryptUtil(wxOpenConfigStorage).decrypt(msgSignature, timestamp, nonce, encryptedXml);
        return fromXml(plainText);
    }

    public static WxOpenXmlMessage fromEncryptedXml(InputStream is, WxOpenConfigStorage wxOpenConfigStorage, String timestamp,
                                               String nonce, String msgSignature) {
        try {
            return fromEncryptedXml(IOUtils.toString(is, StandardCharsets.UTF_8), wxOpenConfigStorage,
                    timestamp, nonce, msgSignature);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public Long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

  public String getInfoType() {
    return infoType;
  }

  public void setInfoType(String infoType) {
    this.infoType = infoType;
  }

  public String getComponentVerifyTicket() {
    return componentVerifyTicket;
  }

  public void setComponentVerifyTicket(String componentVerifyTicket) {
    this.componentVerifyTicket = componentVerifyTicket;
  }

  public String getAuthorizerAppid() {
    return authorizerAppid;
  }

  public void setAuthorizerAppid(String authorizerAppid) {
    this.authorizerAppid = authorizerAppid;
  }

  public String getAuthorizationCode() {
    return authorizationCode;
  }

  public void setAuthorizationCode(String authorizationCode) {
    this.authorizationCode = authorizationCode;
  }

  public Long getAuthorizationCodeExpiredTime() {
    return authorizationCodeExpiredTime;
  }

  public void setAuthorizationCodeExpiredTime(Long authorizationCodeExpiredTime) {
    this.authorizationCodeExpiredTime = authorizationCodeExpiredTime;
  }

  public String getPreAuthCode() {
    return preAuthCode;
  }

  public void setPreAuthCode(String preAuthCode) {
    this.preAuthCode = preAuthCode;
  }
}
