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
public class CustomField implements Serializable {

  /**
   * 半自定义名称,当开发者变更这类类目信息的value值时 可以选择触发系统模板消息通知用户。 FIELD_NAME_TYPE_LEVEL 等级 FIELD_NAME_TYPE_COUPON 优惠券 FIELD_NAME_TYPE_STAMP 印花 FIELD_NAME_TYPE_DISCOUNT 折扣 FIELD_NAME_TYPE_ACHIEVEMEN 成就 FIELD_NAME_TYPE_MILEAGE 里程 FIELD_NAME_TYPE_SET_POINTS 集点 FIELD_NAME_TYPE_TIMS 次数
   */
  @SerializedName("name_type")
  private String nameType;

  /**
   * 自定义名称,当开发者变更这类类目信息的value值时 不会触发系统模板消息通知用户
   */
  @SerializedName("name")
  private String name;

  /**
   * 点击类目跳转外链url
   */
  @SerializedName("url")
  private String url;

  public String getNameType() {
    return nameType;
  }

  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }
}
