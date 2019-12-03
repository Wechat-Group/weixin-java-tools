package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.util.List;

/**
 * 查询当前设置的最低基础库版本及各版本用户占比
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaWeappSupportVersionResult extends WxOpenResult {


  private static final long serialVersionUID = -2955725249930665377L;

  @SerializedName("now_version")
  String nowVersion;

  @SerializedName("uv_info")
  UvInfoBean uvInfo;


  @Data
  public static class UvInfoBean {

    @SerializedName("items")
    List<VersionPercentageBean> items;

  }

  @Data
  public static class VersionPercentageBean {

    @SerializedName("percentage")
    private Integer percentage;

    @SerializedName("version")
    private String version;

  }

  @Override
  public String toString() {
    return WxOpenGsonBuilder.create().toJson(this);
  }



}
