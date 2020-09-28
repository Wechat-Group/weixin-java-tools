package me.chanjar.weixin.cp.bean.oa.calendar;

import com.google.common.collect.ImmutableMap;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 日历.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-09-20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpOaCalendar implements Serializable {
  private static final long serialVersionUID = -817988838579546989L;

  /**
   * 变量名：organizer
   * 是否必须：是
   * 描述：指定的组织者userid。注意该字段指定后不可更新
   */
  @SerializedName("organizer")
  private String organizer;

  /**
   * 变量名：readonly
   * 是否必须：否
   * 描述：日历组织者对日历是否只读权限（即不可编辑日历，不可在日历上添加日程，仅可作为组织者删除日历）。0-否；1-是。默认为1，即只读
   */
  @SerializedName("readonly")
  private Integer readonly;

  /**
   * 变量名：set_as_default
   * 是否必须：否
   * 描述：是否将该日历设置为组织者的默认日历。0-否；1-是。默认为0，即不设为默认日历
   */
  @SerializedName("set_as_default")
  private Integer setAsDefault;

  /**
   * 变量名：summary
   * 是否必须：是
   * 描述：日历标题。1 ~ 128 字符
   */
  @SerializedName("summary")
  private String summary;

  /**
   * 变量名：color
   * 是否必须：是
   * 描述：日历在终端上显示的颜色，RGB颜色编码16进制表示，例如：”#0000FF” 表示纯蓝色
   */
  @SerializedName("color")
  private String color;

  /**
   * 变量名：description
   * 是否必须：否
   * 描述：日历描述。0 ~ 512 字符
   */
  @SerializedName("description")
  private String description;

  /**
   * 变量名：shares
   * 是否必须：否
   * 描述：日历共享成员列表。最多2000人
   */
  @SerializedName("shares")
  private List<ShareInfo> shares;

  @Data
  @AllArgsConstructor
  public static class ShareInfo implements Serializable {
    private static final long serialVersionUID = -4882781114860754679L;

    /**
     * 日历共享成员的id
     */
    private String userid;

    /**
     * 共享成员对日历是否只读权限（即不可编辑日历，不可在日历上添加日程，仅可以退出日历）。
     * 0-否；1-是。默认为1，即只读
     */
    private Integer readonly;
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(ImmutableMap.of("calendar",this));
  }
}
