package me.chanjar.weixin.mp.bean.tag;

import com.google.gson.reflect.TypeToken;
import lombok.Data;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *  用户标签对象
 *  Created by Binary Wang on 2016/9/2.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
public class WxUserTag implements Serializable {
  private static final long serialVersionUID = -7722428695667031252L;

  /**
   * 标签id，由微信分配.
   */
  private Long id;

  /**
   * 标签名，UTF8编码.
   */
  private String name;

  /**
   * 此标签下粉丝数.
   */
  private Integer count;

  public static WxUserTag fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(
      GsonParser.parse(json).get("tag"),
      WxUserTag.class);
  }

  public static List<WxUserTag> listFromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(
      GsonParser.parse(json).get("tags"),
      new TypeToken<List<WxUserTag>>() {
      }.getType());
  }

  public String toJson() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  @Override
  public String toString() {
    return this.toJson();
  }
}
