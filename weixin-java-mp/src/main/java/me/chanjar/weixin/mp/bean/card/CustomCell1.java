package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 自定义会员信息类目
 * author:yuanqixun
 * date:2018-08-25 00:34
 */
@Data
public class CustomCell1 implements Serializable {

  /**
   * 入口名称
   */
  @SerializedName("name")
  private String name;

  /**
   * 入口右侧提示语,6个汉字内。
   */
  @SerializedName("tips")
  private String tips;

  /**
   * 入口跳转链接。
   */
  @SerializedName("url")
  private String url;

  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
