package me.chanjar.weixin.cp.bean.school.user;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 更新家长请求.
 *
 * @author Wang_Wong  created on  2022-06-20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpUpdateParentRequest implements Serializable {
  private static final long serialVersionUID = -4960239393895754138L;

  @SerializedName("parent_userid")
  private String parentUserId;

  @SerializedName("mobile")
  private String mobile;

  @SerializedName("new_parent_userid")
  private String newParentUserId;

  @SerializedName("children")
  private List<Children> children;

  /**
   * The type Children.
   */
  @Setter
  @Getter
  public static class Children implements Serializable {

    @SerializedName("student_userid")
    private String studentUserId;

    @SerializedName("relation")
    private String relation;

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
   * From json wx cp update parent request.
   *
   * @param json the json
   * @return the wx cp update parent request
   */
  public static WxCpUpdateParentRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpUpdateParentRequest.class);
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
