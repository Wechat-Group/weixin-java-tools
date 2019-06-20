package me.chanjar.weixin.mp.bean.card;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * 封面摘要.
 * @author yuanqixun
 * date:2018-08-25 00:35
 */
@Data
public class Abstract implements Serializable {
  private static final long serialVersionUID = -2612656133201770573L;

  /**
   * 摘要.
   */
  @SerializedName("abstract")
  private String abstractInfo;

  /**
   * 封面图片列表.
   * 仅支持填入一 个封面图片链接， 上传图片接口 上传获取图片获得链接，填写 非CDN链接会报错，并在此填入。 建议图片尺寸像素850*350
   */
  @SerializedName("icon_url_list")
  private String iconUrlList;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
