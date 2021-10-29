package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 企业发表内容到客户的朋友圈 创建发表任务结果
 *
 * @author leiin
 * @date 2021-10-29
 */
@Data
public class WxCpAddMomentResult extends WxCpBaseResp {

  @SerializedName("jobid")
  private String jobId;

  public static WxCpAddMomentResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpAddMomentResult.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
