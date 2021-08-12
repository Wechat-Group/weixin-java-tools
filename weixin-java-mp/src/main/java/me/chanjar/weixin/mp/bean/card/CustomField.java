package me.chanjar.weixin.mp.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * 自定义会员信息类目.
 * @author yuanqixun
 * date:2018-08-25 00:34
 */
@Data
public class CustomField implements Serializable {
  private static final long serialVersionUID = -5364412812328198195L;

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

  /**
   * 参考https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1499332673_Unm7V卡券内跳转小程序参数说明：会员卡顶部的信息类目字段，包含以下两个字段
   */
  /**
   * 自定义信息类目小程序user_name，格式为原始id+@app
   */
  @SerializedName("app_brand_user_name")
  private String appBrandUserName;
  /**
   * 自定义信息类目小程序的页面路径
   */
  @SerializedName("app_brand_pass")
  private String appBrandPass;



  public String getNameType() {
    return nameType;
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
