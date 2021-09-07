package me.chanjar.weixin.mp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author plw on 2021/9/7 10:39 AM.
 * @version 1.0
 */
@XStreamAlias("ArticleUrlResult")
@Data
public class ArticleUrlResult implements Serializable {

  @XStreamAlias("ResultList")
  private List<Item> resultList;

  @XStreamAlias("Count")
  private Long count;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  @XStreamAlias("item")
  @Data
  public static class Item implements Serializable {

    @XStreamAlias("ArticleIdx")
    private String articleIdx;

    @XStreamAlias("ArticleUrl")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String articleUrl;

    @Override
    public String toString() {
      return WxMpGsonBuilder.create().toJson(this);
    }

  }
}
