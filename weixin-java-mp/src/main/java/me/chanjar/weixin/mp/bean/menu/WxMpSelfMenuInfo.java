package me.chanjar.weixin.mp.bean.menu;

import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.common.util.ToStringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Created by Binary Wang on 2016-11-25.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMpSelfMenuInfo implements Serializable {
  private static final long serialVersionUID = -81203094124202901L;

  /**
   * 菜单按钮
   */
  @SerializedName("button")
  private List<WxMpSelfMenuButton> buttons;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public List<WxMpSelfMenuButton> getButtons() {
    return this.buttons;
  }

  public void setButtons(List<WxMpSelfMenuButton> buttons) {
    this.buttons = buttons;
  }

  public static class WxMpSelfMenuButton implements Serializable {
    private static final long serialVersionUID = -4426602953309048341L;

    /**
     * <pre>
     * 菜单的类型，公众平台官网上能够设置的菜单类型有view（跳转网页）、text（返回文本，下同）、img、photo、video、voice。
     * 使用API设置的则有8种，详见<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013&token=&lang=zh_CN">《自定义菜单创建接口》</a>
     * </pre>
     */
    @SerializedName("type")
    private String type;
    /**
     * 菜单名称
     */
    @SerializedName("name")
    private String name;
    /**
     * <pre>
     * 对于不同的菜单类型，value的值意义不同。
     * 官网上设置的自定义菜单：
     *  <li>Text:保存文字到value；
     *  <li>Img、voice：保存mediaID到value；
     *  <li>Video：保存视频下载链接到value；
     *  <li>News：保存图文消息到news_info，同时保存mediaID到value；
     *  <li>View：保存链接到url。</li>
     *
     * 使用API设置的自定义菜单：
     *  <li>click、scancode_push、scancode_waitmsg、pic_sysphoto、pic_photo_or_album、	pic_weixin、location_select：保存值到key；
     *  <li>view：保存链接到url
     *  </pre>
     */
    @SerializedName("key")
    private String key;
    /**
     * @see #key
     */
    @SerializedName("url")
    private String url;
    /**
     * @see #key
     */
    @SerializedName("value")
    private String value;
    /**
     * 子菜单信息
     */
    @SerializedName("sub_button")
    private SubButtons subButtons;
    /**
     * 图文消息的信息
     */
    @SerializedName("news_info")
    private NewsInfo newsInfo;

    @Override
    public String toString() {
      return ToStringUtils.toSimpleString(this);
    }

    public SubButtons getSubButtons() {
      return subButtons;
    }

    public void setSubButtons(SubButtons subButtons) {
      this.subButtons = subButtons;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getKey() {
      return key;
    }

    public void setKey(String key) {
      this.key = key;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }

    public NewsInfo getNewsInfo() {
      return newsInfo;
    }

    public void setNewsInfo(NewsInfo newsInfo) {
      this.newsInfo = newsInfo;
    }

    public static class SubButtons implements Serializable {
      private static final long serialVersionUID = 1763350658575521079L;

      @SerializedName("list")
      private List<WxMpSelfMenuButton> subButtons = new ArrayList<>();

      @Override
      public String toString() {
        return ToStringUtils.toSimpleString(this);
      }

      public List<WxMpSelfMenuButton> getSubButtons() {
        return subButtons;
      }

      public void setSubButtons(List<WxMpSelfMenuButton> subButtons) {
        this.subButtons = subButtons;
      }
    }

    public static class NewsInfo implements Serializable {
      private static final long serialVersionUID = 3449813746347818457L;

      @SerializedName("list")
      private List<NewsInButton> news = new ArrayList<>();

      @Override
      public String toString() {
        return ToStringUtils.toSimpleString(this);
      }

      public List<NewsInButton> getNews() {
        return news;
      }

      public void setNews(List<NewsInButton> news) {
        this.news = news;
      }

      public static class NewsInButton  implements Serializable {
        private static final long serialVersionUID = 8701455967664912972L;

        /**
         * 图文消息的标题
         */
        @SerializedName("title")
        private String title;
        /**
         * 摘要
         */
        @SerializedName("digest")
        private String digest;
        /**
         * 作者
         */
        @SerializedName("author")
        private String author;
        /**
         * show_cover
         * 是否显示封面，0为不显示，1为显示
         */
        @SerializedName("show_cover")
        private Integer showCover;
        /**
         * 封面图片的URL
         */
        @SerializedName("cover_url")
        private String coverUrl;
        /**
         * 正文的URL
         */
        @SerializedName("content_url")
        private String contentUrl;
        /**
         * 原文的URL，若置空则无查看原文入口
         */
        @SerializedName("source_url")
        private String sourceUrl;

        @Override
        public String toString() {
          return ToStringUtils.toSimpleString(this);
        }

        public String getTitle() {
          return title;
        }

        public void setTitle(String title) {
          this.title = title;
        }

        public String getDigest() {
          return digest;
        }

        public void setDigest(String digest) {
          this.digest = digest;
        }

        public String getAuthor() {
          return author;
        }

        public void setAuthor(String author) {
          this.author = author;
        }

        public Integer getShowCover() {
          return showCover;
        }

        public void setShowCover(Integer showCover) {
          this.showCover = showCover;
        }

        public String getCoverUrl() {
          return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
          this.coverUrl = coverUrl;
        }

        public String getContentUrl() {
          return contentUrl;
        }

        public void setContentUrl(String contentUrl) {
          this.contentUrl = contentUrl;
        }

        public String getSourceUrl() {
          return sourceUrl;
        }

        public void setSourceUrl(String sourceUrl) {
          this.sourceUrl = sourceUrl;
        }
      }
    }
  }
}
