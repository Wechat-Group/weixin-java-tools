package me.chanjar.weixin.qidian.bean.card;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

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

  /**
   * 参考https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1499332673_Unm7V卡券内跳转小程序参数说明：会员卡自定义入口，包含以下两个字段
   */
  /**
   * 自定义入口小程序user_name，格式为原始id+@app.
   */
  @SerializedName("app_brand_user_name")
  private String appBrandUserName;
  /**
   * 自定义入口小程序的页面路径.
   */
  @SerializedName("app_brand_pass")
  private String appBrandPass;


  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
