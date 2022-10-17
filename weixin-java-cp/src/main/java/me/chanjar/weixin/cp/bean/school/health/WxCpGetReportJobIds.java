package me.chanjar.weixin.cp.bean.school.health;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 获取健康上报使用统计.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpGetReportJobIds extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("ending")
  private Integer ending;

  @SerializedName("jobids")
  private List<String> jobIds;

  /**
   * From json wx cp get report job ids.
   *
   * @param json the json
   * @return the wx cp get report job ids
   */
  public static WxCpGetReportJobIds fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGetReportJobIds.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
