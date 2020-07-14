package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 企业群发消息任务
 * <p>
 * Created by songfan on 2020/7/14.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpMsgTemplate implements Serializable {
  private static final long serialVersionUID = 3172331565173474358L;

  @SerializedName("chat_type")
  private String chatType;

  @SerializedName("external_userid")
  private List<String> externalUserid;

  private String sender;

  private Text text;

  private Image image;

  private Link link;

  private Miniprogram miniprogram;

  public static WxCpMsgTemplate fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpMsgTemplate.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  @Data
  public class Text {
    private String content;
  }

  @Data
  public class Image {

    @SerializedName("media_id")
    private String mediaId;

    @SerializedName("pic_url")
    private String picUrl;
  }

  @Data
  public class Link {
    private String title;
    @SerializedName("picurl")
    private String picUrl;
    private String desc;
    private String url;
  }

  @Data
  public class Miniprogram {
    private String title;
    @SerializedName("pic_media_id")
    private String picMediaId;
    private String appid;
    private String page;
  }
}
