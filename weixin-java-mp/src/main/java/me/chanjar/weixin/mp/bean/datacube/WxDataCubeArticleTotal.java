package me.chanjar.weixin.mp.bean.datacube;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * 图文分析数据接口返回结果对象.
 * Created by Binary Wang on 2016/8/24.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxDataCubeArticleTotal extends WxDataCubeBaseResult {
  private static final long serialVersionUID = -7634365687303052699L;

  /**
   * msgid.
   * 请注意：这里的msgid实际上是由msgid（图文消息id，这也就是群发接口调用后返回的msg_data_id）和index（消息次序索引）组成， 例如12003_3， 其中12003是msgid，即一次群发的消息的id； 3为index，假设该次群发的图文消息共5个文章（因为可能为多图文），3表示5个中的第3个
   */
  @SerializedName("msgid")
  private String msgId;

  /**
   * 图文消息的标题
   */
  @SerializedName("title")
  private String title;

  /**
   * 文章地址
   */
  @SerializedName("url")
  private String url;

  /**
   * details.
   * 详细信息
   */
  @SerializedName("details")
  private List<WxDataCubeArticleTotalDetail> details;

  /**
   * user_source
   * 在获取图文统计数据、图文阅读分时数据时才有该字段，代表用户从哪里进入来阅读该图文。
   * 99999999.全部；0:会话;1.好友;2.朋友圈;3.腾讯微博;4.历史消息页;5.其他;6.看一看;7.搜一搜；
   */
  @SerializedName("user_source")
  private Integer userSource;

  public static List<WxDataCubeArticleTotal> fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(
      GsonParser.parse(json).get("list"),
      new TypeToken<List<WxDataCubeArticleTotal>>() {
      }.getType());
  }

}
