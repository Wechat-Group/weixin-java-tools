package me.chanjar.weixin.cp.bean.oa.wedrive;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 重命名空间请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpSpaceRenameRequest implements Serializable {
  private static final long serialVersionUID = -4960239393895754138L;

  @SerializedName("userid")
  private String userId;

  @SerializedName("spaceid")
  private String spaceId;

  @SerializedName("space_name")
  private String spaceName;

  public static WxCpSpaceRenameRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpSpaceRenameRequest.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
