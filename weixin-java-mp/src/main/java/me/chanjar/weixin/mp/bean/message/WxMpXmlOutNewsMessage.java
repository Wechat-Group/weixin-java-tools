package me.chanjar.weixin.mp.bean.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

/**
 * 被动回复的图文消息xml.
 * @author chanjarster
 */
@Data
@XStreamAlias("xml")
@JacksonXmlRootElement(localName = "xml")
@EqualsAndHashCode(callSuper = true)
public class WxMpXmlOutNewsMessage extends WxMpXmlOutMessage {
  private static final long serialVersionUID = -4604402850905714772L;

  /**
   * 图文消息信息.
   * 注意，如果图文数超过限制，则将只发限制内的条数
   */
  @XStreamAlias("Articles")
  @JacksonXmlProperty(localName = "Articles")
  protected final List<Item> articles = new ArrayList<>();
  /**
   * 图文消息个数.
   * 当用户发送文本、图片、视频、图文、地理位置这五种消息时，开发者只能回复1条图文消息；其余场景最多可回复8条图文消息
   */
  @XStreamAlias("ArticleCount")
  @JacksonXmlProperty(localName = "ArticleCount")
  protected int articleCount;

  public WxMpXmlOutNewsMessage() {
    this.msgType = WxConsts.XmlMsgType.NEWS;
  }

  public void addArticle(Item item) {
    this.articles.add(item);
    this.articleCount = this.articles.size();
  }

  @Data
  @XStreamAlias("item")
  @JacksonXmlRootElement(localName = "item")
  public static class Item implements Serializable {
    private static final long serialVersionUID = -4971456355028904754L;

    /**
     * 图文消息标题.
     */
    @XStreamAlias("Title")
    @XStreamConverter(value = XStreamCDataConverter.class)
    @JacksonXmlProperty(localName = "Title")
    @JacksonXmlCData
    private String title;

    /**
     * 图文消息描述.
     */
    @XStreamAlias("Description")
    @XStreamConverter(value = XStreamCDataConverter.class)
    @JacksonXmlProperty(localName = "Description")
    @JacksonXmlCData
    private String description;

    /**
     * 图片链接.
     * 支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     */
    @XStreamAlias("PicUrl")
    @XStreamConverter(value = XStreamCDataConverter.class)
    @JacksonXmlProperty(localName = "PicUrl")
    @JacksonXmlCData
    private String picUrl;

    /**
     * 点击图文消息跳转链接.
     */
    @XStreamAlias("Url")
    @XStreamConverter(value = XStreamCDataConverter.class)
    @JacksonXmlProperty(localName = "Url")
    @JacksonXmlCData
    private String url;

  }

}
