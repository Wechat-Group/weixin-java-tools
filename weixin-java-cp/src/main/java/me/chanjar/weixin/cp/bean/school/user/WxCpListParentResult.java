package me.chanjar.weixin.cp.bean.school.user;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 获取部门家长详情返回结果.
 *
 * @author Wang_Wong  created on  2022-07-13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpListParentResult extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -4960239393895754138L;

  @SerializedName("parents")
  private List<Parent> parents;

  /**
   * The type Parent.
   */
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

    @SerializedName("external_userid")
    private String externalUserId;

    @SerializedName("is_subscribe")
    private Integer isSubscribe;

    @SerializedName("children")
    private List<Children> children;

    /**
     * From json parent.
     *
     * @param json the json
     * @return the parent
     */
    public static Parent fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Parent.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  /**
   * The type Children.
   */
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

    @SerializedName("name")
    private String name;

    /**
     * From json children.
     *
     * @param json the json
     * @return the children
     */
    public static Children fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Children.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  /**
   * From json wx cp list parent result.
   *
   * @param json the json
   * @return the wx cp list parent result
   */
  public static WxCpListParentResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpListParentResult.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
