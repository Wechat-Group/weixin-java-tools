package me.chanjar.weixin.cp.bean.school.user;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 批量创建家长.
 *
 * @author Wang_Wong
 * @date 2022-07-11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpBatchCreateParentRequest implements Serializable {
  private static final long serialVersionUID = -4960239393895754138L;

  @SerializedName("parents")
  private List<Parent> parents;

  @Setter
  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Parent implements Serializable {

    @SerializedName("parent_userid")
    private String parentUserId;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("to_invite")
    private Boolean toInvite;

    @SerializedName("children")
    private List<Children> children;

    public static Parent fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Parent.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  @Setter
  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
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

  public static WxCpBatchCreateParentRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpBatchCreateParentRequest.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
