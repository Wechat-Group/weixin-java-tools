package me.chanjar.weixin.open.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.util.xml.XStreamTransformer;
import me.chanjar.weixin.open.util.crypto.WxOpenCryptUtil;
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

  public static WxMpXmlMessage fromXml(InputStream is) {
    return XStreamTransformer.fromXml(WxMpXmlMessage.class, is);
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
}
