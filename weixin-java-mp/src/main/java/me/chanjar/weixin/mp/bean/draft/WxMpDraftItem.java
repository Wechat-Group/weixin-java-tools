package me.chanjar.weixin.mp.bean.draft;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 一条草稿
 *
 * @author dragon
 * @date 2021-10-22
 */
@Data
public class WxMpDraftItem implements Serializable {
  private static final long serialVersionUID = 214696458030935146L;

  /**
   * 图文消息的id
   */
  @SerializedName("media_id")
  private String mediaId;
  /**
   * 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS。
   */
  @SerializedName("content")
  private WxMpDraftInfo content;

  public static WxMpDraftItem fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpDraftItem.class);
  }
}
