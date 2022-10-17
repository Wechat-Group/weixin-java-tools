package me.chanjar.weixin.cp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Wx cp xml out news message.
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpXmlOutNewsMessage extends WxCpXmlOutMessage {
  private static final long serialVersionUID = -5796178637883178826L;

  /**
   * The Articles.
   */
  @XStreamAlias("Articles")
  protected final List<Item> articles = new ArrayList<>();

  /**
   * The Article count.
   */
  @XStreamAlias("ArticleCount")
  protected int articleCount;

  /**
   * Instantiates a new Wx cp xml out news message.
   */
  public WxCpXmlOutNewsMessage() {
    this.msgType = WxConsts.XmlMsgType.NEWS;
  }


  /**
   * Add article.
   *
   * @param item the item
   */
  public void addArticle(Item item) {
    this.articles.add(item);
    this.articleCount = this.articles.size();
  }

  /**
   * The type Item.
   */
  @XStreamAlias("item")
  @Data
  public static class Item implements Serializable {
    private static final long serialVersionUID = -8672761162722733622L;

    @XStreamAlias("Title")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String title;

    @XStreamAlias("Description")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String description;

    @XStreamAlias("PicUrl")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String picUrl;

    @XStreamAlias("Url")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String url;

  }

}
