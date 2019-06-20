package me.chanjar.weixin.open.bean.ma;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 三方平台提交小程序代码审核
 *
 * @author yqx
 * @date 2018/9/13
 */
@Data
public class WxOpenMaSubmitAudit implements Serializable {


  /**
   * 小程序的页面，可通过“获取小程序的第三方提交代码的页面配置”接口获得
   */
  @SerializedName("address")
  private String pagePath;

  /**
   * 小程序的标签，多个标签用空格分隔，标签不能多于10个，标签长度不超过20
   */
  @SerializedName("tag")
  private String tag;

  /**
   * 类目名称，可通过“获取授权小程序帐号的可选类目”接口获得
   */
  @SerializedName("first_class")
  private String firstClass;

  @SerializedName("second_class")
  private String secondClass;

  @SerializedName("third_class")
  private String thirdClass;

  /**
   * 类目的ID，可通过“获取授权小程序帐号的可选类目”接口获得
   */
  @SerializedName("first_id")
  private Integer firstId;

  @SerializedName("second_id")
  private Integer secondId;

  @SerializedName("third_id")
  private Integer thirdId;

  /**
   * 小程序页面的标题,标题长度不超过32
   */
  @SerializedName("title")
  private String title;

}
