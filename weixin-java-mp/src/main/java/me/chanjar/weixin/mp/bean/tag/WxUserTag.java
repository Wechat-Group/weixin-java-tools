package me.chanjar.weixin.mp.bean.tag;

import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import me.chanjar.weixin.common.util.ToStringUtils;
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
   * id	标签id，由微信分配
   */
  private Long id;

  /**
   * name	标签名，UTF8编码
   */
  private String name;

  /**
   * count 此标签下粉丝数
   */
  private Integer count;

  public static WxUserTag fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(
      new JsonParser().parse(json).getAsJsonObject().get("tag"),
      WxUserTag.class);
  }

  public static List<WxUserTag> listFromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(
      new JsonParser().parse(json).getAsJsonObject().get("tags"),
      new TypeToken<List<WxUserTag>>() {
      }.getType());
  }

  public String toJson() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }
}
