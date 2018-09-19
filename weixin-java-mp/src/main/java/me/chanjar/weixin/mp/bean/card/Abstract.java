package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 封面摘要
 * author:yuanqixun
 * date:2018-08-25 00:35
 */
@Data
public class Abstract implements Serializable {

  /**
   * 摘要
   */
  @SerializedName("abstract")
  private String abstractInfo;

  /**
   * 封面图片列表,仅支持填入一 个封面图片链接， 上传图片接口 上传获取图片获得链接，填写 非CDN链接会报错，并在此填入。 建议图片尺寸像素850*350
   */
  @SerializedName("icon_url_list")
  private String iconUrlList;

  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
