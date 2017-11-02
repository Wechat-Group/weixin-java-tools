package me.chanjar.weixin.mp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XStreamAlias("xml")
@Data
public class WxMpXmlOutNewsMessage extends WxMpXmlOutMessage {
  private static final long serialVersionUID = -4604402850905714772L;

  @XStreamAlias("Articles")
  protected final List<Item> articles = new ArrayList<>();
  @XStreamAlias("ArticleCount")
  protected int articleCount;

  public WxMpXmlOutNewsMessage() {
    this.msgType = WxConsts.XML_MSG_NEWS;
  }

  public void addArticle(Item item) {
    this.articles.add(item);
    this.articleCount = this.articles.size();
  }

  @XStreamAlias("item")
  @Data
  public static class Item implements Serializable {
    private static final long serialVersionUID = -4971456355028904754L;

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
