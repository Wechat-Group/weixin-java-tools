package me.chanjar.weixin.cp.bean.school.user;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 创建家长请求.
 *
 * @author Wang_Wong
 * @date 2022-06-20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpCreateParentRequest implements Serializable {
  private static final long serialVersionUID = -4960239393895754138L;

  @SerializedName("parent_userid")
  private String parentUserId;

  @SerializedName("mobile")
  private String mobile;

  @SerializedName("to_invite")
  private Boolean toInvite;

  @SerializedName("children")
  private List<Children> children;

  @Setter
  @Getter
  public static class Children implements Serializable {

    @SerializedName("student_userid")
    private String studentUserId;

    @SerializedName("relation")
    private String relation;

    public static Children fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Children.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  public static WxCpCreateParentRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpCreateParentRequest.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
