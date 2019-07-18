package me.chanjar.weixin.mp.bean.card;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * 自定义会员信息类目.
 * @author yuanqixun
 * date:2018-08-25 00:34
 */
@Data
public class CustomCell1 implements Serializable {
  private static final long serialVersionUID = -6446192667149800447L;

  /**
   * 入口名称.
   */
  @SerializedName("name")
  private String name;

  /**
   * 入口右侧提示语,6个汉字内.
   */
  @SerializedName("tips")
  private String tips;

  /**
   * 入口跳转链接.
   */
  @SerializedName("url")
  private String url;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
