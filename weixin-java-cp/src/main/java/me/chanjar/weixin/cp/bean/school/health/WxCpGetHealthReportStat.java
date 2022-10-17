package me.chanjar.weixin.cp.bean.school.health;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 获取健康上报使用统计.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpGetHealthReportStat extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("pv")
  private Integer pv;

  @SerializedName("uv")
  private Integer uv;

  /**
   * From json wx cp get health report stat.
   *
   * @param json the json
   * @return the wx cp get health report stat
   */
  public static WxCpGetHealthReportStat fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGetHealthReportStat.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
