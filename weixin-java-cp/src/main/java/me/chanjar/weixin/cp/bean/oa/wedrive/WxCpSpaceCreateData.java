package me.chanjar.weixin.cp.bean.oa.wedrive;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 新建空间信息.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpSpaceCreateData extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("spaceid")
  private String spaceId;

  public static WxCpSpaceCreateData fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpSpaceCreateData.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
