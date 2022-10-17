package me.chanjar.weixin.cp.bean.user;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 获取成员ID列表返回参数
 *
 * @author <a href="https://gitee.com/Wang_Wong/">Wang_Wong</a> created on  2022/08/09
 */
@Data
public class WxCpDeptUserResult extends WxCpBaseResp {
  private static final long serialVersionUID = 1420065684270213578L;

  @SerializedName("next_cursor")
  private String nextCursor;

  @SerializedName("dept_user")
  private List<DeptUserList> deptUser;

  /**
   * The type Dept user list.
   */
  @Getter
  @Setter
  public static class DeptUserList implements Serializable {
    private static final long serialVersionUID = 1420065684270213578L;

    @SerializedName("userid")
    private String userId;

    @SerializedName("department")
    private Long department;

    /**
     * From json dept user list.
     *
     * @param json the json
     * @return the dept user list
     */
    public static DeptUserList fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, DeptUserList.class);
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
   * From json wx cp dept user result.
   *
   * @param json the json
   * @return the wx cp dept user result
   */
  public static WxCpDeptUserResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDeptUserResult.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
