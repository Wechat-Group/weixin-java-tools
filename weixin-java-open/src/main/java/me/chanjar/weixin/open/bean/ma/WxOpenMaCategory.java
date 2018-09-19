package me.chanjar.weixin.open.bean.ma;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 微信小程序分类目录
 *
 * @author yqx
 * @date 2018/9/13
 */
@Data
public class WxOpenMaCategory implements Serializable {

  @SerializedName("first_class")
  private String firstClass;

  @SerializedName("second_class")
  private String secondClass;

  @SerializedName("third_class")
  private String thirdClass;

  @SerializedName("first_id")
  private Integer firstId;

  @SerializedName("second_id")
  private Integer secondId;

  @SerializedName("third_id")
  private Integer thirdId;

  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
