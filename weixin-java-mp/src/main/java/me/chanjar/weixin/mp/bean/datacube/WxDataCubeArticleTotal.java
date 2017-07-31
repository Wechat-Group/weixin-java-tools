package me.chanjar.weixin.mp.bean.datacube;

import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.util.List;

/**
 * 图文分析数据接口返回结果对象
 *
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 *         Created by Binary Wang on 2016/8/24.
 */
public class WxDataCubeArticleTotal extends WxDataCubeBaseResult {

  private static final JsonParser JSON_PARSER = new JsonParser();

  /**
   * msgid
   * 请注意：这里的msgid实际上是由msgid（图文消息id，这也就是群发接口调用后返回的msg_data_id）和index（消息次序索引）组成， 例如12003_3， 其中12003是msgid，即一次群发的消息的id； 3为index，假设该次群发的图文消息共5个文章（因为可能为多图文），3表示5个中的第3个
   */
  @SerializedName("msgid")
  private String msgId;

  /**
   * title
   * 图文消息的标题
   */
  @SerializedName("title")
  private String title;

  /**
   * details
   * 详细信息
   */
  @SerializedName("details")
  private List<WxDataCubeArticleTotalDetail> details;

  public static List<WxDataCubeArticleTotal> fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(
      JSON_PARSER.parse(json).getAsJsonObject().get("list"),
      new TypeToken<List<WxDataCubeArticleTotal>>() {
      }.getType());
  }

  public String getMsgId() {
    return this.msgId;
  }

  public void setMsgId(String msgId) {
    this.msgId = msgId;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<WxDataCubeArticleTotalDetail> getDetails() {
    return this.details;
  }

  public void setDetails(List<WxDataCubeArticleTotalDetail> details) {
    this.details = details;
  }
}
